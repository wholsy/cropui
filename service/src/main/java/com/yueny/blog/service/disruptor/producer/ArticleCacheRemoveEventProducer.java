//package com.yueny.blog.service.disruptor.producer;
//
//import com.lmax.disruptor.EventTranslatorOneArg;
//import com.lmax.disruptor.dsl.Disruptor;
//import com.yueny.blog.service.disruptor.api.SyntonyHandlerFunction;
//import com.yueny.blog.service.disruptor.event.ArticleCacheRemoveEvent;
//import com.yueny.blog.service.disruptor.util.DisruptorHelper;
//
///**
// * 文章缓存删除时间生产器
// *
// * @author yueny09 <deep_blue_yang@163.com>
// *
// * @DATE 2018年1月11日 下午9:24:18
// *
// */
//@Deprecated
//public class ArticleCacheRemoveEventProducer
//		extends AbstractEventProducer<ArticleCacheRemoveEvent, SyntonyHandlerFunction> {
//	private static final EventTranslatorOneArg<ArticleCacheRemoveEvent, SyntonyHandlerFunction> TRANSLATOR_ONE_ARG = new EventTranslatorOneArg<ArticleCacheRemoveEvent, SyntonyHandlerFunction>() {
//		@Override
//		public void translateTo(final ArticleCacheRemoveEvent logEvent, final long sequence,
//				final SyntonyHandlerFunction syntonyExecute) {
//			logEvent.setSyntonyExecute(syntonyExecute);
//		}
//	};
//
//	public ArticleCacheRemoveEventProducer() {
//		this(DisruptorHelper.getDisruptor(ArticleCacheRemoveEvent.class));
//	}
//
//	public ArticleCacheRemoveEventProducer(final Disruptor<ArticleCacheRemoveEvent> disruptor) {
//		super(disruptor);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @see com.yueny.blog.service.disruptor.producer.AbstractEventProducer#
//	 * publishData( com.yueny.blog.service.disruptor.api.SyntonyHandlerFunction)
//	 */
//	@Override
//	@Deprecated
//	public void publishData(final SyntonyHandlerFunction syntonyExecute) {
//		logger.info("Publishing " + syntonyExecute);
//
//		// // 发布事件
//		// // Get the ring buffer from the Disruptor to be used for publishing.
//		// final RingBuffer<LogEvent> ringBuffer = disruptor.getRingBuffer();
//		// final long sequence = ringBuffer.next();// 请求下一个事件序号；
//		//
//		// try {
//		// // 获取该序号对应的事件对象；
//		// final LogEvent event = ringBuffer.get(sequence);
//		// event.setSyntonyExecute(syntonyExecute);
//		// } finally {
//		// ringBuffer.publish(sequence);// 发布事件；
//		// }
//
//		// or
//		// publish the message to disruptor
//		getDisruptor().publishEvent(TRANSLATOR_ONE_ARG, syntonyExecute);
//	}
//
//}
