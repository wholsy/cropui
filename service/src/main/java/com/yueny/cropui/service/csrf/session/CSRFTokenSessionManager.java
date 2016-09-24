package com.yueny.cropui.service.csrf.session;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * csrf机制的帮助类
 *
 * @author 袁洋 2015年8月7日 上午10:37:49
 *
 */
public final class CSRFTokenSessionManager {
	/**
	 * session中csrfToken参数名称
	 */
	private static final String CSRF_TOKEN_FOR_SESSION_ATTR_NAME = CSRFTokenSessionManager.class
			.getName() + ".tokenval";
	/**
	 * 日志记录器
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(CSRFTokenSessionManager.class);
	/**
	 * 隐藏域参数名称
	 */
	static final String CSRF_PARAM_NAME = "csrfmiddlewaretoken";

	/**
	 * 在session中创建csrfToken
	 *
	 * @param session
	 *            HttpSession
	 * @return token
	 */
	public static String createTokenForSession(final HttpSession session) {
		String token = null;
		// I cannot allow more than one token on a session - in the case of two
		// requests trying to
		// init the token concurrently.
		// Notice: in real life I wouldn't synchronize on the session instance.
		// This should be done on an attribute on the session. But for the
		// blog demo this is fine
		synchronized (session) {
			token = (String) session
					.getAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME);
			if (null == token) {
				token = UUID.randomUUID().toString();
				session.setAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME, token);
			}
		}
		return token;
	}

	/**
	 * Extracts the token value from the session
	 *
	 * @param request
	 *            HttpServletRequest
	 * @return token
	 */
	static String getTokenFromRequest(final HttpServletRequest request) {
		return request.getParameter(CSRF_PARAM_NAME);
	}

	private CSRFTokenSessionManager() {
		// .
	}

}
