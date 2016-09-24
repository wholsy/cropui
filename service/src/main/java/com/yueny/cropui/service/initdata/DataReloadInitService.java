package com.yueny.cropui.service.initdata;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yueny.blog.service.BaseBiz;
import com.yueny.superclub.service.rest.context.initializing.ServiceContextInitData;

/**
 * 数据初始化
 *
 * @author yueny09
 *
 */
@Service
public final class DataReloadInitService extends BaseBiz implements
InitializingBean, DisposableBean {
	/* 起一个定时线程，去轮询常量信息放入 ServiceContextInitData */
	// 定时任务执行器
	private final ScheduledExecutorService retryExecutor = Executors
			.newScheduledThreadPool(1, new NamedThreadFactory(
					"DataReloadQueryTimer", true));

	// 失败重试定时器，定时检查是否有请求失败，如有，无限次重试
	private ScheduledFuture<?> retryFuture;
	/**
	 * 延迟轮询时间
	 */
	@Value("${app.system.retry.period.seconds}")
	private String retryPeriod;

	/**
	 * 默认延迟轮询时间。半小时为1800秒
	 */
	private Long retryPeriodSeconds = 1800L;

	/** 系统编号 */
	@Value("${app.system.code}")
	private String systemCode;

	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			retryPeriodSeconds = Long.parseLong(retryPeriod);
		} catch (final Exception e) {
			logger.error("延迟轮询时间获取失败！", e);
		}

		this.retryFuture = retryExecutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				// 加载
				try {
					reload();
				} catch (final Throwable t) { // 防御性容错
					logger.error(
							"Unexpected error occur at failed retry, cause: "
									+ t.getMessage(), t);
				}
			}
		}, 0, retryPeriodSeconds * 1000L, TimeUnit.MILLISECONDS);
	}

	@Override
	public void destroy() {
		try {
			retryFuture.cancel(true);
		} catch (final Throwable t) {
			logger.warn(t.getMessage(), t);
		}
	}

	private void reload() {
		try {
			// logger.debug("获取所有子系统配置信息！");
			// final List<SubSystemBo> allSubSystem =
			// subSystemService.queryAll();
			// logger.debug("所有子系统配置信息：{}！", allSubSystem);
			//
			// /* 功能开放状态 */
			// final List<FunctionOpenBo> functionOpens = functionOpenService
			// .queryAll(systemCode);
			// logger.debug("功能开放状态配置信息：{}！", functionOpens);
			//
			// ServiceContextInitData.clear();
			//
			// for (final SubSystemBo subSystemBo : allSubSystem) {
			// ServiceContextInitData.push(subSystemBo.getSystemCode(),
			// subSystemBo.getDomainUrl());
			// }
			//
			// for (final FunctionOpenBo functionOpenBo : functionOpens) {
			// ServiceContextInitData.push(functionOpenBo.getFunctionCode(),
			// functionOpenBo.getStatus().enable());
			// }
		} catch (final Exception e) {
			logger.error("数据初始化异常！", e);
		}

		logger.info("数据加载完毕，保存形式：{}！", ServiceContextInitData.get());
	}

}
