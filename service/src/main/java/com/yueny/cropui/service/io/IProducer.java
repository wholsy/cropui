package com.yueny.cropui.service.io;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import com.yueny.cropui.service.io.enums.TxtType;

/**
 * 生产者接口
 *
 * @author yueny(yueny09@163.com)
 * @param <R>
 *            生产者队列类型
 *
 * @date 2015年9月19日 下午1:54:31
 */
public interface IProducer<R> {
	/**
	 * @return 是否结束
	 */
	Boolean isDone();

	/**
	 * @return 支持的文本类型
	 */
	List<TxtType> getSupportedTxtTypes();

	/**
	 * 生产者数据队列
	 *
	 * @return 生产者数据队列
	 */
	BlockingQueue<R> getQueue();

}
