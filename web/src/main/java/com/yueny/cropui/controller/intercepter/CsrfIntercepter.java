package com.yueny.cropui.controller.intercepter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * mitigate the risk of Cross-Site Request Forgery (CSRF) attacks
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月21日 上午9:32:55
 *
 */
public class CsrfIntercepter implements HandlerInterceptor {
	public static final String CSRFNUMBER = "csrftoken";

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
		final String keyFromRequestParam = request.getParameter(CSRFNUMBER);
		String keyFromCookies = "";
		final boolean result = false;
		final Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (final Cookie cookie : cookies) {
				final String name = cookie.getName();
				if (CSRFNUMBER.equals(name)) {
					keyFromCookies = cookie.getValue();
				}
			}
		}

		// TODO
		// if ((keyFromRequestParam != null && keyFromRequestParam.length() > 0
		// && keyFromRequestParam.equals(keyFromCookies)
		// &&
		// keyFromRequestParam.equals(request.getSession().getAttribute(CSRFNUMBER))))
		// {
		// result = true;
		// } else {
		// request.getRequestDispatcher("/error").forward(request,
		// response);
		// }

		return true;
	}

}
