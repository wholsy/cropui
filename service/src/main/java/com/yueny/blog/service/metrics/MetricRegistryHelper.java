package com.yueny.blog.service.metrics;

import static com.codahale.metrics.MetricRegistry.name;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import com.codahale.metrics.Timer;

public class MetricRegistryHelper {
	public static class InnerCaller {
		private InnerCaller() {
			// .
		}

		/**
		 * Meter用来计算事件的速率<br>
		 * 事件总数，平均速率,包含1分钟，5分钟，15分钟的速率
		 */
		public Meter meter(final String name) {
			final String namer = requestName(name);
			if (REQUESTS_METER_CACHE.containsKey(namer)) {
				return REQUESTS_METER_CACHE.get(namer);
			}

			final Meter requests = metrics.meter(namer);
			REQUESTS_METER_CACHE.put(namer, requests);
			return requests;
		}

		/**
		 * metrics 计数一次
		 */
		public void meterMark(final String name) {
			REQUESTS_METER_CACHE.get(requestName(name)).mark();
		}

		private String requestName(final String namePrefix) {
			return name(namePrefix, "requests");
		}

		/**
		 * Timer用来测量一段代码被调用的速率和用时<br>
		 * 如统计QPS
		 */
		public Timer timer(final String name) {
			final String timeName = timerName(name);
			if (TIMER_CACHE.containsKey(timeName)) {
				return TIMER_CACHE.get(timeName);
			}

			final Timer timer = metrics.timer(timeName);
			TIMER_CACHE.put(timeName, timer);
			return timer;
		}

		private String timerName(final String namePrefix) {
			return name(namePrefix, "calculation-duration");
		}
	}

	private static InnerCaller call = null;

	/**
	 * 中心部件 MetricRegistry。 它是程序中所有度量metric的容器。
	 */
	private static MetricRegistry metrics = null;
	/**
	 * Meter是一种只能自增的计数器，通常用来度量一系列事件发生的比率。他提供了平均速率，以及指数平滑平均速率，以及采样后的1分钟，5分钟，
	 * 15分钟速率。 metrics:事件总数，平均速率,包含1分钟，5分钟，15分钟的速率
	 */
	private static final Map<String, Meter> REQUESTS_METER_CACHE = new ConcurrentHashMap<>();

	private static final Map<String, Timer> TIMER_CACHE = new ConcurrentHashMap<>();

	public static InnerCaller getInstance() {
		if (call != null) {
			return call;
		}

		call = new InnerCaller();

		metrics = new MetricRegistry();

		// 注册metrics,每个1秒打印metrics到控制台
		final ScheduledReporter reporter = ConsoleReporter.forRegistry(metrics).convertRatesTo(TimeUnit.SECONDS)
				.convertDurationsTo(TimeUnit.MILLISECONDS).build();
		reporter.start(1, TimeUnit.SECONDS);

		return call;
	}
}
