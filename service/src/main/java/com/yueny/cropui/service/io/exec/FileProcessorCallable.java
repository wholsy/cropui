package com.yueny.cropui.service.io.exec;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;
import com.yueny.cropui.service.io.IProducer;

/**
 * 文件解析线程
 *
 * @author <a href="mailto:yueny09@163.com"> 袁洋
 *
 * @date 2015年9月15日 下午5:42:19
 */
public class FileProcessorCallable implements Callable<List<String>> {
	/**
	 * 日志记录器
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(FileProcessorCallable.class);
	/**
	 * 是否结束
	 */
	private Boolean isDone = false;
	/**
	 * 生产者
	 */
	private final IProducer<String> producer;

	/**
	 * @param producer
	 *            生产者
	 */
	public FileProcessorCallable(final IProducer<String> producer) {
		this.producer = producer;
	}

	@Override
	public List<String> call() throws Exception {
		logger.info(Thread.currentThread().getName() + " 正在解析数据！");

		final List<String> data = new ArrayList<>();
		try {
			while (true) {
				// 已停止生产 并且 队列为空，则结束
				if (producer.isDone() && producer.getQueue().size() <= 0) {
					break;
				}

				final String line = producer.getQueue().take();
				// 解析数据
				TimeUnit.MILLISECONDS.sleep(20L);
				data.add(line);
			}
		} catch (final InterruptedException e) {
			logger.warn("【读文件】读文件出现异常:{}.", Throwables.getStackTraceAsString(e));
		} finally {
			done();
		}
		return data;
	}

	/**
	 *
	 */
	public void done() {
		isDone = true;
	}

	/**
	 * @return 是否结束
	 */
	public Boolean isDone() {
		return isDone;
	}

}
