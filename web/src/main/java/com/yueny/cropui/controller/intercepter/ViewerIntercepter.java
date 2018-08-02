package com.yueny.cropui.controller.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yueny.blog.service.listener.DefaultMsgPusher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yueny.rapid.lang.util.IpUtil;

/**
 * 访客拦截器
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年8月2日 下午12:47:27
 * @since
 */
public class ViewerIntercepter implements HandlerInterceptor {
	@Autowired
	private DefaultMsgPusher defaultMsgPusher;

	@Override
	public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
			final Object handler, final Exception ex) throws Exception {
		// .
	}

	@Override
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
			final ModelAndView modelAndView) throws Exception {
		// .
	}

	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
			throws Exception {
		final String requestUri = request.getRequestURI();
		final String ip = IpUtil.getClientIp(request);

		return true;
	}

}
