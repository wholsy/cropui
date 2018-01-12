package com.yueny.blog.service.disruptor.producer;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.dsl.Disruptor;
import com.yueny.blog.service.disruptor.DisruptorHelper;
import com.yueny.blog.service.disruptor.event.LogEvent;
import com.yueny.blog.service.disruptor.handler.SyntonyExecute;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年1月11日 下午9:24:18
 *
 */
@Slf4j
public class LogEventProducer {
	private static final EventTranslatorOneArg<LogEvent, SyntonyExecute> TRANSLATOR_ONE_ARG = new EventTranslatorOneArg<LogEvent, SyntonyExecute>() {
		@Override
		public void translateTo(final LogEvent logEvent, final long sequence, final SyntonyExecute syntonyExecute) {
			logEvent.setSyntonyExecute(syntonyExecute);
		}
	};

	private final Disruptor<LogEvent> disruptor;

	public LogEventProducer() {
		this(DisruptorHelper.getDisruptor(LogEvent.class));
	}

	public LogEventProducer(final Disruptor<LogEvent> disruptor) {
		this.disruptor = disruptor;
	}

	/**
	 * @param syntonyExecute
	 *            事件传递的业务数据
	 */
	public void publishData(final SyntonyExecute syntonyExecute) {
		log.info("Publishing " + syntonyExecute);

		// // 发布事件
		// // Get the ring buffer from the Disruptor to be used for publishing.
		// final RingBuffer<LogEvent> ringBuffer = disruptor.getRingBuffer();
		// final long sequence = ringBuffer.next();// 请求下一个事件序号；
		//
		// try {
		// // 获取该序号对应的事件对象；
		// final LogEvent event = ringBuffer.get(sequence);
		// event.setSyntonyExecute(syntonyExecute);
		// } finally {
		// ringBuffer.publish(sequence);// 发布事件；
		// }

		// or
		// publish the message to disruptor
		disruptor.publishEvent(TRANSLATOR_ONE_ARG, syntonyExecute);
	}

}
