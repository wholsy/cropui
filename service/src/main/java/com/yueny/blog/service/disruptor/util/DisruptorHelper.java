/**
 *
 */
package com.yueny.blog.service.disruptor.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.lmax.disruptor.dsl.Disruptor;
import com.yueny.blog.service.disruptor.event.IEvent;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年1月11日 上午2:14:42
 *
 */
public class DisruptorHelper {
	private static final Map<Class<? extends IEvent>, Disruptor<? extends IEvent>> disruptorMaps = new ConcurrentHashMap<>();

	public static <T extends IEvent> Disruptor<T> getDisruptor(final Class<T> event) {
		return (Disruptor<T>) disruptorMaps.get(event);
	}

	public static <T extends IEvent> boolean remove(final Class<T> event) {
		if (disruptorMaps.containsKey(event)) {
			disruptorMaps.remove(event);
			return true;
		}
		return false;
	}

	public static <T extends IEvent> void setDisruptor(final Class<T> event, final Disruptor<T> disruptor) {
		if (disruptorMaps.containsKey(event)) {
			return;
		}

		disruptorMaps.put(event, disruptor);
	}

}
