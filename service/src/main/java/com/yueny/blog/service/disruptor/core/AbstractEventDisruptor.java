/**
*
*/
package com.yueny.blog.service.disruptor.core;

import java.lang.reflect.ParameterizedType;

import com.lmax.disruptor.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.yueny.blog.service.disruptor.event.IEvent;
import com.yueny.blog.service.disruptor.util.DisruptorHelper;
import com.yueny.rapid.lang.thread.factory.NamedThreadFactory;

/**
* @author yueny09 <deep_blue_yang@163.com>
*
* @DATE 2018年1月21日 上午12:11:15
*
*/
public abstract class AbstractEventDisruptor<T extends IEvent> implements InitializingBean, DisposableBean {
	/** 日志 */
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private final Class<T> entityClass = extracted();

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public final void afterPropertiesSet() throws Exception {
		System.out.println("entityClass is : " + entityClass);

		final Disruptor<T> disruptor = getDisruptor();

		DisruptorHelper.setDisruptor(entityClass, disruptor);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.beans.factory.DisposableBean#destroy()
	 */
	@Override
	public final void destroy() throws Exception {
		final Disruptor<T> disruptor = DisruptorHelper.getDisruptor(entityClass);

		if (disruptor != null) {
			disruptor.shutdown();

			DisruptorHelper.remove(entityClass);
		}
	}

	/**
	 * @return 获取第一个泛型T的实体类信息
	 */
	@SuppressWarnings("unchecked")
	private Class<T> extracted() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	private Disruptor<T> getDisruptor() {
		// Specify the size of the ring buffer, must be power of 2.
		//// RingBuffer 大小，必须是 2 的 N 次方；
		final int ringBufferSize = 2 * 1024;

		// Construct the Disruptor
		final Disruptor<T> disruptor = new Disruptor<>(getEventFactory(), ringBufferSize,
				new NamedThreadFactory("MinaClientWorker", true),
				// Single producer
				ProducerType.SINGLE, waitStrategy());

		// Connect the handler
		disruptor.handleEventsWith(getEventHandler());

		// Start the Disruptor, starts all threads running
		disruptor.start();

		return disruptor;
	}

	/**
	 * @return The factory for the event
	 */
	abstract EventFactory<T> getEventFactory();

	/**
	 * @return 事件执行器
	 */
	abstract EventHandler<T> getEventHandler();

	/**
	 * @return 指定等待策略
	 */
	public WaitStrategy waitStrategy() {
		return new BlockingWaitStrategy();
	}

}
