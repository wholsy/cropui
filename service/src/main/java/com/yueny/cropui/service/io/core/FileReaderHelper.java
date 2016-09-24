/**
 *
 * @author <a href="mailto:yueny09@163.com"> 袁洋
 *
 * @date 2015年9月14日 下午3:08:24
 */
package com.yueny.cropui.service.io.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;
import com.yueny.cropui.service.io.exec.FileProcessorCallable;
import com.yueny.cropui.service.io.exec.TxtFileReaderRunner;
import com.yueny.cropui.service.io.quick.FileNioQuickReader;
import com.yueny.cropui.service.io.strategy.IDataRenderHandler;

/**
 * 文件读取和解析<br>
 *
 * <pre>
 * 背景：
 * 按行读取文件，将读取到的行数据进行解析处理，得到解析后的结果（解析数据和错误数据坐标）。
 * </pre>
 *
 * <pre>
 * 处理规则：
 * 将启动3个线程池：
 * 一个读取文件行得到行(serviceName:readExecutorService)；
 * 一个处理非正常数据(serviceName:abnormalExecutorService)；
 * 一个解析文件产生的行(serviceName:processorExecutorService)。
 * 由于整个过程中瓶颈在数据解析上，所以读取文件线程池目前只启动一个线程；非正常数据处理线程池固定线程数目为1，目前未启用；解析数据线程池根据文件大小来做拆分。
 * </pre>
 *
 * @author <a href="mailto:yueny09@163.com"> 袁洋
 *
 * @date 2015年9月14日 下午3:08:24
 */
public final class FileReaderHelper {
	/**
	 * 文件非正常数据处理线程池
	 */
	private static ExecutorService abnormalExecutorService;
	/**
	 * 大小文件判断阀值,5MB
	 */
	private static final int FILE_LIMIT_SIZE = 1024 * 5;
	/**
	 * 日志记录器
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(FileReaderHelper.class);
	/**
	 * 最大线程数
	 */
	@Getter
	@Setter
	private static Integer maxPoolSize = 10;
	/**
	 * 数据解析线程池
	 */
	private static ExecutorService processorExecutorService;
	/**
	 * 文件读取线程池
	 */
	private static ExecutorService readExecutorService;
	/**
	 * 定时器
	 */
	private static Timer timer;

	static {
		init();
	}

	/**
	 * 文件批量读取
	 *
	 * @param filePath
	 *            文件路径名
	 * @return 文件行集合
	 */
	public static List<String> readBatch(final String filePath) {
		final File file = new File(filePath);
		if (!file.exists()) {
			logger.warn("【读文件】文件:{}不存在！", filePath);
			return Collections.emptyList();
		}

		final Long currentTime = System.currentTimeMillis();
		logger.info("【读文件】开始读文件:{},当前时间：{}.", filePath, currentTime);

		/* 获取文件类型 */
		String contentType;
		try {
			contentType = Files.probeContentType(Paths.get(filePath));
			logger.info("【读文件】文件:{} 类型：{}.", filePath, contentType);
		} catch (final IOException e) {
			Throwables.getStackTraceAsString(e);
		}

		/* 文件读取线程 */
		final TxtFileReaderRunner producer = new TxtFileReaderRunner(filePath);
		excuteReadJob(producer);

		/* 数据解析线程 */
		// 根据文件大小决定启用的解析线程
		final int ca = 9;
		final Set<FutureTask<List<String>>> processorJobSet = new HashSet<>();
		for (int i = 0; i < ca; i++) {
			final FileProcessorCallable callable = new FileProcessorCallable(
					producer);
			// 使用FutrueTask 包装Callable对象
			final FutureTask<List<String>> processorJob = new FutureTask<List<String>>(
					callable);
			processorJobSet.add(processorJob);
		}

		for (final FutureTask<List<String>> future : processorJobSet) {
			excuteProcessorJob(future);
		}

		/* 定时器 */
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("线程信息监控进程!");
			}
		}, 500, 1000);

		/* 获取数据 */
		final List<String> lines = new ArrayList<>();
		for (final FutureTask<List<String>> processorJob : processorJobSet) {
			try {
				final List<String> data = processorJob.get();
				if (CollectionUtils.isEmpty(data)) {
					continue;
				}
				lines.addAll(data);
			} catch (InterruptedException | ExecutionException e) {
				Throwables.getStackTraceAsString(e);
			}
		}
		logger.info("【读文件】读取并解析文件结束，得到数目:{},历时：{}.", lines.size(),
				(System.currentTimeMillis() - currentTime));
		return lines;
	}

	/**
	 * 文件批量读取
	 *
	 * @param filePath
	 *            文件路径名
	 * @param dataRender
	 *            文件读取结束后的自定义策略操作
	 */
	@Deprecated
	public static void readBatch(final String filePath,
			final IDataRenderHandler<List<String>> dataRender) {
		final List<String> lines = readBatch(filePath);
		System.out.println(lines);
	}

	/**
	 * 文件读取，一次性得到行结果
	 *
	 * @param filePath
	 *            文件路径名
	 * @return 文件行集合
	 */
	public static List<String> readLump(final String filePath) {
		final File file = new File(filePath);
		if (!file.exists()) {
			logger.warn("【读文件】文件:{}不存在！", filePath);
			return Collections.emptyList();
		}

		final Long currentTime = System.currentTimeMillis();
		logger.info("【读文件】开始读文件:{},当前时间：{}.", filePath, currentTime);

		/* 小文件快速读取 */
		final Long size = file.length();
		if (size < FILE_LIMIT_SIZE) {
			final List<String> lines = FileNioQuickReader.quickReader(filePath);

			logger.info("【读文件】读文件，得到数目:{}，总计历时(毫秒)：{}.", lines.size(),
					System.currentTimeMillis() - currentTime);
			return lines;
		}

		BufferedReader reader = null;
		final List<String> lines = new ArrayList<>();
		try {
			final Charset charset = Charset.defaultCharset();
			// reader = new BufferedReader(new InputStreamReader(
			// new FileInputStream(file), charset));
			reader = Files.newBufferedReader(Paths.get(filePath), charset);

			String line = null;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
		} catch (final FileNotFoundException e) {
			logger.warn("【读文件】读文件出现异常:{}.", Throwables.getStackTraceAsString(e));
		} catch (final IOException e) {
			logger.warn("【读文件】读文件出现异常:{}.", Throwables.getStackTraceAsString(e));
		} finally {
			try {
				reader.close();
			} catch (final IOException e) {
				logger.warn("【读文件】关闭流，出现异常:{}.",
						Throwables.getStackTraceAsString(e));
			}
		}
		logger.info("【读文件】读文件，得到数目:{}，总计历时(毫秒)：{}.", lines.size(),
				System.currentTimeMillis() - currentTime);
		return lines;
	}

	/**
	 * Create the BlockingQueue to use for the ThreadPoolExecutor.
	 * <p>
	 * A LinkedBlockingQueue instance will be created for a positive capacity
	 * value; a SynchronousQueue else.
	 *
	 * @param queueCapacity
	 *            the specified queue capacity
	 * @return the BlockingQueue instance
	 * @see java.util.concurrent.LinkedBlockingQueue
	 * @see java.util.concurrent.SynchronousQueue
	 */
	private static BlockingQueue<Runnable> createQueue(final int queueCapacity) {
		if (queueCapacity > 0) {
			return new LinkedBlockingQueue<Runnable>(queueCapacity);
		}
		return new SynchronousQueue<Runnable>();
	}

	/**
	 * 加入数据解析线程池
	 *
	 * @param runnable
	 *            线程
	 */
	private static void excuteProcessorJob(final Runnable runnable) {
		try {
			processorExecutorService.submit(runnable);
		} catch (final Exception e) {
			if (e instanceof RejectedExecutionException) {
				// while (isSleep(executorService, maxPoolSize)) {
				try {
					TimeUnit.SECONDS.sleep(5L);
				} catch (final InterruptedException e1) {
					logger.warn("【数据解析】加入数据解析线程池：{}堵塞，等待时出现异常:{}.",
							readExecutorService,
							Throwables.getStackTraceAsString(e1));
				}
				// }
				excuteReadJob(runnable);
			}
		}
	}

	/**
	 * 加入读线程池
	 *
	 * @param runnable
	 *            线程
	 */
	private static void excuteReadJob(final Runnable runnable) {
		try {
			readExecutorService.submit(runnable);
		} catch (final Exception e) {
			if (e instanceof RejectedExecutionException) {
				// while (isSleep(executorService, maxPoolSize)) {
				try {
					TimeUnit.SECONDS.sleep(5L);
				} catch (final InterruptedException e1) {
					logger.warn("【读文件】加入线程池：{}堵塞，等待时出现异常:{}.",
							readExecutorService,
							Throwables.getStackTraceAsString(e1));
				}
				// }
				excuteReadJob(runnable);
			}
		}
	}

	private static void init() {
		final long keepAliveTime = 60L;
		// 核心线程数
		final Integer corePoolSize = 5;

		// 阻塞队列数 为2
		readExecutorService = new ThreadPoolExecutor(corePoolSize, maxPoolSize,
				keepAliveTime, TimeUnit.SECONDS, createQueue(2),
				Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.AbortPolicy());
		abnormalExecutorService = Executors.newFixedThreadPool(1);
		processorExecutorService = Executors.newFixedThreadPool(12);

		timer = new Timer();
	}

}
