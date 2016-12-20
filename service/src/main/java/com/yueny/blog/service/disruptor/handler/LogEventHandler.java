package com.yueny.blog.service.disruptor.handler;

import com.lmax.disruptor.EventHandler;
import com.yueny.blog.service.disruptor.event.LogEvent;

/**
 * 事件发生处理
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年12月20日 上午10:04:48
 *
 */
public class LogEventHandler implements EventHandler<LogEvent> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lmax.disruptor.EventHandler#onEvent(java.lang.Object, long,
	 * boolean)
	 */
	@Override
	public void onEvent(final LogEvent event, final long sequence, final boolean endOfBatch) {
		System.out.println("Event: " + event);
	}
}
