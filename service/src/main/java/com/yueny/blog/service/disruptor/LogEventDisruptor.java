package com.yueny.blog.service.disruptor;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.yueny.blog.service.disruptor.event.LogEvent;
import com.yueny.blog.service.disruptor.factory.LogEventFactory;
import com.yueny.blog.service.disruptor.handler.LogEventHandler;
import com.yueny.blog.service.disruptor.handler.SyntonyExecute;
import com.yueny.rapid.lang.thread.factory.NamedThreadFactory;

@Service
public class LogEventDisruptor implements InitializingBean {
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		final Disruptor<LogEvent> disruptor = getDisruptor();
		DisruptorHelper.setDisruptor(LogEvent.class, disruptor);
	}

	private Disruptor<LogEvent> getDisruptor() {
		// // Executor that will be used to construct new threads for consumers
		// // 定义用于事件处理的线程池.Disruptor 通过 java.util.concurrent.ExecutorService 提供的线程来触发
		// // Consumer 的事件处理
		// final ExecutorService executor = Executors
		// .newCachedThreadPool(new NamedThreadFactory("MinaClientWorker", true));

		// The factory for the event
		final LogEventFactory factory = new LogEventFactory();

		// 指定等待策略
		/*
		 * Disruptor 定义了 com.lmax.disruptor.WaitStrategy 接口用于抽象 Consumer
		 * 如何等待新事件，这是策略模式的应用。 Disruptor 提供了多个 WaitStrategy 的实现，每种策略都具有不同性能和优缺点，根据实际运行环境的
		 * CPU 的硬件特点选择恰当的策略，并配合特定的 JVM 的配置参数，能够实现不同的性能提升。
		 * 例如，BlockingWaitStrategy、SleepingWaitStrategy、YieldingWaitStrategy 等，其中，
		 * BlockingWaitStrategy 是最低效的策略，但其对CPU的消耗最小并且在各种不同部署环境中能提供更加一致的性能表现；
		 * SleepingWaitStrategy 的性能表现跟 BlockingWaitStrategy 差不多，对 CPU
		 * 的消耗也类似，但其对生产者线程的影响最小，适合用于异步日志类似的场景； YieldingWaitStrategy
		 * 的性能是最好的，适合用于低延迟的系统。在要求极高性能且事件处理线数小于 CPU 逻辑核心数的场景中，推荐使用此策略；例如，CPU开启超线程的特性。
		 */
		final WaitStrategy waitStrategy = new YieldingWaitStrategy();

		// Specify the size of the ring buffer, must be power of 2.
		//// RingBuffer 大小，必须是 2 的 N 次方；
		final int ringBufferSize = 1024 * 1024;

		// Construct the Disruptor
		final Disruptor<LogEvent> disruptor = new Disruptor<>(factory, ringBufferSize,
				new NamedThreadFactory("MinaClientWorker", true),
				// Single producer
				ProducerType.SINGLE, waitStrategy);

		// Connect the handler
		disruptor.handleEventsWith(new LogEventHandler());

		// Start the Disruptor, starts all threads running
		disruptor.start();

		return disruptor;
	}

	/**
	 * @param syntonyExecute
	 *            事件传递的业务数据
	 */
	public void onData(final SyntonyExecute syntonyExecute) {
		final Disruptor<LogEvent> disruptor = DisruptorHelper.getDisruptor(LogEvent.class);

		// 发布事件
		// Get the ring buffer from the Disruptor to be used for publishing.
		final RingBuffer<LogEvent> ringBuffer = disruptor.getRingBuffer();
		final long sequence = ringBuffer.next();// 请求下一个事件序号；

		try {
			// 获取该序号对应的事件对象；
			final LogEvent event = ringBuffer.get(sequence);
			event.setSyntonyExecute(syntonyExecute);
		} finally {
			ringBuffer.publish(sequence);// 发布事件；
		}
	}

}
