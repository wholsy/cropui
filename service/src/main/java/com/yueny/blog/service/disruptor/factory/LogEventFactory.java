package com.yueny.blog.service.disruptor.factory;

import com.lmax.disruptor.EventFactory;
import com.yueny.blog.service.disruptor.event.LogEvent;

/**
 * 日志事件工厂<br>
 * 事件工厂(Event Factory)定义了如何实例化前面第1步中定义的事件(Event)，需要实现接口
 * com.lmax.disruptor.EventFactory<T>。 Disruptor 通过 EventFactory 在 RingBuffer
 * 中预创建 Event 的实例。 一个 Event 实例实际上被用作一个“数据槽”，发布者发布前，先从 RingBuffer 获得一个 Event
 * 的实例，然后往 Event 实例中填充数据，之后再发布到 RingBuffer 中，之后由 Consumer 获得该 Event 实例并从中读取数据。
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年1月11日 上午1:21:48
 *
 */
public class LogEventFactory implements EventFactory<LogEvent> {

	@Override
	public LogEvent newInstance() {
		return new LogEvent();
	}

}
