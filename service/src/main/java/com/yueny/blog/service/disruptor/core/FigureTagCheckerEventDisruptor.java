/**
*
*/
package com.yueny.blog.service.disruptor.core;

import org.springframework.stereotype.Service;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.yueny.blog.service.disruptor.event.FigureTagCheckerEvent;
import com.yueny.blog.service.disruptor.factory.FigureTagCheckerEventFactory;
import com.yueny.blog.service.disruptor.handler.FigureTagCheckerEventHandler;

/**
* 标签完整性检查事件
*
* @author yueny09 <deep_blue_yang@163.com>
*
* @DATE 2018年1月21日 上午12:08:16
*
*/
@Service
public class FigureTagCheckerEventDisruptor extends AbstractEventDisruptor<FigureTagCheckerEvent> {
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.yueny.blog.service.disruptor.core.AbstractEventDisruptor#getEventFactory(
	 * )
	 */
	@Override
	EventFactory<FigureTagCheckerEvent> getEventFactory() {
		return new FigureTagCheckerEventFactory();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.yueny.blog.service.disruptor.core.AbstractEventDisruptor#getEventHandler(
	 * )
	 */
	@Override
	EventHandler<FigureTagCheckerEvent> getEventHandler() {
		return new FigureTagCheckerEventHandler();
	}

}
