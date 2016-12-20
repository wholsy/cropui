package com.yueny.blog.service.disruptor.factory;

import com.lmax.disruptor.EventFactory;
import com.yueny.blog.service.disruptor.event.LogEvent;

public class LogEventFactory implements EventFactory<LogEvent> {

	@Override
	public LogEvent newInstance() {
		return new LogEvent();
	}

}
