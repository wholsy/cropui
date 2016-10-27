package com.yueny.blog.service.metrics;

import java.util.Random;

import com.codahale.metrics.Timer;

/**
 * 根据时间来计算qps，可以用Timer
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年10月21日 下午2:25:40
 *
 */
public class TimerMain {
	public static void main(final String args[]) {
		final Timer timer = MetricRegistryHelper.getInstance().timer("time");

		final Random rn = new Random();
		while (true) {
			// 统计开始:计算中间的代码用时以及request的速率
			final Timer.Context context = timer.time();

			try {
				// etc
				final int sleepTime = rn.nextInt(2000);
				Thread.sleep(sleepTime);

				System.out.println("处理耗时:" + sleepTime);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			} finally {
				// 统计结束
				context.stop();
			}
		}

	}

}
