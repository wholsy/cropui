package com.yueny.blog.service.intercepter;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yueny.blog.service.BaseBizTest;

public class MetricIntercepterTest extends BaseBizTest {
	@Autowired
	private MetricIntercepter metricIntercepter;

	@Test
	public void testGetChartData() {
		System.out.println(metricIntercepter);

		try {
			for (int i = 0; i < 8; i++) {
				metricIntercepter.preHandle(null, null, null);
			}
		} catch (final Exception e) {
		}

		try {
			Thread.sleep(3 * 1000);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		} finally {
		}

		try {
			for (int i = 0; i < 8; i++) {
				metricIntercepter.preHandle(null, null, null);
			}
		} catch (final Exception e) {
		}

		try {
			Thread.sleep(3 * 1000);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		} finally {
		}
	}
}
