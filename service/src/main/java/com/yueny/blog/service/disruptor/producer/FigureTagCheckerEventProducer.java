package com.yueny.blog.service.disruptor.producer;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.dsl.Disruptor;
import com.yueny.blog.service.disruptor.event.FigureTagCheckerEvent;
import com.yueny.blog.service.disruptor.util.DisruptorHelper;

/**
* 标签完整性检查事件生产器
*
* @author yueny09 <deep_blue_yang@163.com>
*
* @DATE 2018年1月21日 上午12:06:57
*
*/
public class FigureTagCheckerEventProducer extends AbstractEventProducer<FigureTagCheckerEvent, FigureTagCheckerEvent> {
	private static final EventTranslatorOneArg<FigureTagCheckerEvent, FigureTagCheckerEvent> TRANSLATOR_ONE_ARG = new EventTranslatorOneArg<FigureTagCheckerEvent, FigureTagCheckerEvent>() {
		@Override
		public void translateTo(final FigureTagCheckerEvent logEvent, final long sequence,
				final FigureTagCheckerEvent eventData) {
			logEvent.setArticleBlogService(eventData.getArticleBlogService());
			logEvent.setOwenerTagService(eventData.getOwenerTagService());
			logEvent.setUid(eventData.getUid());
		}
	};

	public FigureTagCheckerEventProducer() {
		this(DisruptorHelper.getDisruptor(FigureTagCheckerEvent.class));
	}

	public FigureTagCheckerEventProducer(final Disruptor<FigureTagCheckerEvent> disruptor) {
		super(disruptor);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.yueny.blog.service.disruptor.producer.AbstractEventProducer#publishData(
	 * com.yueny.blog.service.disruptor.api.SyntonyHandlerFunction)
	 */
	@Override
	public void publishData(final FigureTagCheckerEvent eventData) {
		logger.info("Publishing " + eventData);

		// publish the message to disruptor
		getDisruptor().publishEvent(TRANSLATOR_ONE_ARG, eventData);
	}

}
