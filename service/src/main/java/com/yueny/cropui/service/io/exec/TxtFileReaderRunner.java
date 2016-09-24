package com.yueny.cropui.service.io.exec;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;
import com.yueny.cropui.service.io.IProducer;
import com.yueny.cropui.service.io.enums.TxtType;

/**
 * 文件读取线程
 *
 * @author yueny(yueny09@163.com)
 *
 * @date 2015年9月19日 上午11:51:02
 */
public class TxtFileReaderRunner implements Runnable, IProducer<String> {
	/**
	 * 字符集
	 */
	private static final Charset charset = Charset.forName("UTF-8");
	/**
	 * 日志记录器
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(TxtFileReaderRunner.class);
	/**
	 * 阻塞队列数
	 */
	private static final int queueCapacity = 100;
	/**
	 * 文件路径名
	 */
	private final String filePath;
	/**
	 * 是否结束
	 */
	private Boolean isDone = false;
	private BufferedReader reader;
	/**
	 * 读到的行数据队列
	 */
	private final BlockingQueue<String> readLinesQueue;

	/**
	 * @param filePath
	 *            文件路径名
	 */
	public TxtFileReaderRunner(final String filePath) {
		this.filePath = filePath;
		readLinesQueue = new LinkedBlockingQueue<String>(queueCapacity);

		init();
	}

	/**
	 *
	 */
	public void done() {
		isDone = true;
	}

	@Override
	public BlockingQueue<String> getQueue() {
		return this.readLinesQueue;
	}

	@Override
	public List<TxtType> getSupportedTxtTypes() {
		return Arrays.asList(TxtType.TXT);
	}

	@Override
	public Boolean isDone() {
		return isDone;
	}

	@Override
	public void run() {
		if (reader == null) {
			return;
		}

		try {
			String line = null;
			while ((line = reader.readLine()) != null) {
				readLinesQueue.put(line);
			}
		} catch (final IOException e) {
			logger.warn("【读文件】读文件出现异常:{}.", Throwables.getStackTraceAsString(e));
		} catch (final InterruptedException e) {
			logger.warn("【读文件】读文件出现异常:{}.", Throwables.getStackTraceAsString(e));
		} finally {
			try {
				reader.close();
			} catch (final IOException e) {
				logger.warn("【读文件】关闭流，出现异常:{}.",
						Throwables.getStackTraceAsString(e));
			}
			done();
		}
	}

	private void init() {
		try {
			reader = Files.newBufferedReader(Paths.get(filePath), charset);
		} catch (final IOException e) {
			logger.warn("【读文件】BufferedReader初始化失败:{}.",
					Throwables.getStackTraceAsString(e));
		}
	}

}
