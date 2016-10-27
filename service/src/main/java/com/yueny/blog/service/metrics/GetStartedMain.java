package com.yueny.blog.service.metrics;

import com.codahale.metrics.Meter;

/**
 * 统计调用频率,计数型的统计，比如计算失败次数，每次+1,则可以用Meter
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年10月21日 下午2:01:39
 *
 */
public class GetStartedMain {
	public static void main(final String args[]) {
		final Meter requests = MetricRegistryHelper.getInstance().meter("request");

		// 计数一次
		requests.mark();
		wait5Seconds();
	}

	static void wait5Seconds() {
		try {
			Thread.sleep(5 * 1000);
		} catch (final InterruptedException e) {
		}
	}
}
