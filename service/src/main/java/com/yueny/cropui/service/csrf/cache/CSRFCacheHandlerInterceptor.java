package com.yueny.cropui.service.csrf.cache;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 只针对post请求做检查，本机制认为只有post请求为改变数据状态
 * 比较request中的CSRFtoken值和memcached中的token值如果相同则允许操作否则到错误页面
 *
 * @author 袁洋 2015年8月7日 上午10:43:51
 *
 */
public class CSRFCacheHandlerInterceptor extends HandlerInterceptorAdapter {
	/**
	 * 不拦截的请求
	 */
	private final static Set<String> DIRECT_URLS = new HashSet<String>();
	static {
		DIRECT_URLS.add("/cropui/quicklogin//"); // 快速登录
	}

	/**
	 * 日志记录器
	 */
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public boolean preHandle(final HttpServletRequest request,
			final HttpServletResponse response, final Object handler)
			throws Exception {
		if (!request.getMethod().equalsIgnoreCase("POST")
				|| DIRECT_URLS.contains(request.getRequestURI())) {
			// Not a POST - allow the request
			return true;
		}

		logger.debug("【CSRF验证】需要进行验证，提交方式{}，提交URL:{},", request.getMethod(),
				request.getRequestURI());
		// This is a POST request - need to check the CSRF token
		final String memcachedKey = CSRFCacheTokenManager
				.getMemcachedkeyFromRequest(request);

		if (StringUtils.isEmpty(memcachedKey)) {
			// response.sendError(HttpServletResponse.SC_FORBIDDEN,
			// "Bad or missing CSRF value");
			// return false;
			throw new RuntimeException("请使用正常方式提交，不要进行重复提交操作!");
		}
		final String memcachedToken = CSRFCacheTokenManager
				.getTokenForMemcached(memcachedKey);
		final String requestToken = CSRFCacheTokenManager
				.getTokenFromRequest(request);

		if (memcachedToken.equals(requestToken)) {
			CSRFCacheTokenManager.destroyTokenFormMemcached(memcachedKey);
			return true;
		}
		// response.sendError(HttpServletResponse.SC_FORBIDDEN,
		// "Bad or missing CSRF value");
		// return false;
		throw new RuntimeException("请使用正常方式提交，不要进行重复提交操作!");
	}

}
