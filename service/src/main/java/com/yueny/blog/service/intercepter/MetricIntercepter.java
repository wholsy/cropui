package com.yueny.blog.service.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.codahale.metrics.Meter;
import com.yueny.blog.service.metrics.MetricRegistryHelper;

/**
 * 统计
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年10月21日 下午1:37:39
 *
 */
@Service
public class MetricIntercepter implements HandlerInterceptor, InitializingBean {
	private static Meter requests = null;
	static {
		requests = MetricRegistryHelper.getInstance().meter("request");
	}

	@Override
	public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
			final Object handler, final Exception ex) throws Exception {
		// .
	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	@Override
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
			final ModelAndView modelAndView) throws Exception {
		// .
	}

	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
			throws Exception {
		// 计数一次
		requests.mark();
		// etc

		return true;
	}

}
