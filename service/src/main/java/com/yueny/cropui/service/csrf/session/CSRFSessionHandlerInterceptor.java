package com.yueny.cropui.service.csrf.session;

import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 只针对post请求做检查，本机制认为只有post请求为改变数据状态<br>
 * 比较request中的CSRFtoken值和memcached中的token值如果相同则允许操作否则到错误页面<br>
 * 对于post请求进行拦截，检测csrfToken是否匹配
 *
 * @author 袁洋 2015年8月7日 上午10:43:51
 *
 */
public class CSRFSessionHandlerInterceptor extends HandlerInterceptorAdapter {
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
	private final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 登录url
	 */
	@Getter
	@Setter
	private String loginUrl;

	@Override
	public boolean preHandle(final HttpServletRequest request,
			final HttpServletResponse response, final Object handler)
			throws Exception {
		if (!"POST".equalsIgnoreCase(request.getMethod())
				|| DIRECT_URLS.contains(request.getRequestURI())) {
			// Not a POST - allow the request
			return true;
		}

		logger.debug("【CSRF验证】需要进行验证，提交方式{}，提交URL:{},", request.getMethod(),
				request.getRequestURI());
		// This is a POST request - need to check the CSRF token
		final String sessionToken = CSRFTokenSessionManager
				.createTokenForSession(request.getSession());
		final String requestToken = CSRFTokenSessionManager
				.getTokenFromRequest(request);
		if (sessionToken.equals(requestToken)) {
			return true;
		}

		logger.warn("【CSRF验证】CSRF验证不通过，请求token:{},服务器token:{}，准备重定向到：{}.",
				requestToken, sessionToken, loginUrl);
		final String reLoginUrl = loginUrl + "?backurl="
				+ URLEncoder.encode(getCurrentUrl(request), "utf-8");

		response.sendRedirect(reLoginUrl);
		// response.sendError(HttpServletResponse.SC_FORBIDDEN,
		// "Bad or missing CSRF value");
		return false;
	}

	private String getCurrentUrl(final HttpServletRequest request) {
		String currentUrl = request.getRequestURL().toString();
		if (!StringUtils.isEmpty(request.getQueryString())) {
			currentUrl += "?" + request.getQueryString();
		}

		return currentUrl;
	}

}
