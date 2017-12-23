//package com.yueny.blog.service.common.initdata;
//
//import java.util.Map;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.ScheduledFuture;
//import java.util.concurrent.TimeUnit;
//
//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Value;
//
//import com.yueny.blog.service.BaseBiz;
//import com.yueny.rapid.lang.util.collect.CollectionUtil;
//import com.yueny.superclub.service.rest.context.initializing.ServiceContextInitDataHolder;
//
//import lombok.Getter;
//
///**
// * 数据初始化加载
// *
// * @author yueny09 <deep_blue_yang@163.com>
// *
// * @DATE 2017年1月24日 上午10:13:36
// * @since
// */
//public abstract class AbstractDataInitService extends BaseBiz implements InitializingBean, DisposableBean {
//	/**
//	 * 默认延迟轮询时间。默认1小时。 1 hour = 60s * 60min
//	 */
//	private static Long retryPeriodSeconds = 3600L;
//	/* 起一个定时线程，去轮询常量信息放入 ServiceContextInitDataHolder */
//	// 定时任务执行器
//	private final ScheduledExecutorService retryExecutor = Executors.newScheduledThreadPool(1,
//			new NamedThreadFactory(getClass().getName() + "QueryTimer", true));
//	// 失败重试定时器，定时检查是否有请求失败，如有，无限次重试
//	private ScheduledFuture<?> retryFuture;
//
//	/** 系统编号 */
//	@Value("${app.system.code}")
//	@Getter
//	private String systemCode;
//
//	@Override
//	public void afterPropertiesSet() throws Exception {
//		final Long effectRetryPeriodSeconds = retryPeriod();
//
//		// 延迟5秒进行
//		this.retryFuture = retryExecutor.scheduleWithFixedDelay(new Runnable() {
//			@Override
//			public void run() {
//				// 加载
//				try {
//					reload();
//				} catch (final Throwable t) { // 防御性容错
//					logger.error("Unexpected error occur at failed retry, cause: " + t.getMessage(), t);
//				}
//			}
//		}, 1500L, effectRetryPeriodSeconds * 1000L, TimeUnit.MILLISECONDS);
//	}
//
//	private void clear() {
//		ServiceContextInitDataHolder.clear(groupName());
//	}
//
//	@Override
//	public void destroy() throws Exception {
//		try {
//			retryFuture.cancel(true);
//		} catch (final Throwable t) {
//			logger.warn(t.getMessage(), t);
//		}
//	}
//
//	/**
//	 * 组名
//	 */
//	protected String groupName() {
//		return getClass().getName();
//	}
//
//	private void push(Map<String, Object> data) {
//		for (final Map.Entry<String, Object> entry : data.entrySet()) {
//			ServiceContextInitDataHolder.push(groupName(), entry.getKey(), entry.getValue());
//		}
//	}
//
//	private void reload() {
//		try {
//			clear();
//
//			Map<String, Object> data = selectData();
//			int retry = 10;
//			while (CollectionUtil.isEmpty(data)) {
//				retry--;
//				logger.warn("数据重新加载...");
//				Thread.sleep(600L);
//
//				data = selectData();
//
//				if (retry < 0) {
//					break;
//				}
//			}
//
//			push(data);
//		} catch (final Exception e) {
//			logger.error("数据初始化异常！", e);
//		}
//
//		try {
//			logger.info("数据加载完毕，保存形式：{}！", ServiceContextInitDataHolder.get(getClass().getName()));
//		} catch (final Exception e) {
//			// .
//		}
//	}
//
//	/**
//	 * 延迟轮询时间
//	 */
//	protected Long retryPeriod() {
//		return retryPeriodSeconds;
//	}
//
//	abstract Map<String, Object> selectData();
//}
