//package com.yueny.blog.service.common;
//
//import java.lang.reflect.Method;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.aop.AfterReturningAdvice;
//import org.springframework.aop.MethodBeforeAdvice;
//
///**
// * 性能监测，必须为多例。
// *
// * @author yueny09 <deep_blue_yang@163.com>
// *
// * @DATE 2016年6月26日 下午5:40:43
// *
// */
//public class PerformanceMonitoringHelper implements MethodBeforeAdvice, AfterReturningAdvice {
//	private static Logger logger = LoggerFactory.getLogger(PerformanceMonitoringHelper.class);
//
//	private Long startTime = 0l;
//
//	@Override
//	public void afterReturning(final Object returnValue, final Method method, final Object[] args, final Object target)
//			throws Throwable {
//		final String m = method.getDeclaringClass().getCanonicalName() + "." + method.getName();
//
//		final String time = (System.currentTimeMillis() - startTime) + "ms";
//
//		logger.info("方法 {}, 参数 {}，返回值{}\n耗费时间 {}", m, args, returnValue, time);
//		// log.debug("耗费时间 {}",time);
//	}
//
//	@Override
//	public void before(final Method method, final Object[] args, final Object target) throws Throwable {
//		startTime = System.currentTimeMillis();
//	}
//
//}
