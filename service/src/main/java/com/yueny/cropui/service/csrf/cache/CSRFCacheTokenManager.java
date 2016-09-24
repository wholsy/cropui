package com.yueny.cropui.service.csrf.cache;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yueny.superclub.util.redis.cache.IRedisCacheClient;

/**
 * csrf机制的帮助类
 *
 * @author 袁洋 2015年8月7日 上午10:37:49
 *
 */
public final class CSRFCacheTokenManager {
	/**
	 * 日志记录器
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(CSRFCacheTokenManager.class);
	@Setter
	@Getter
	// TODO how to reg
	private static IRedisCacheClient redisCacheService;

	private final static byte[] sync = new byte[0];

	/**
	 * The token parameter name
	 */
	static final String CSRF_TOKEN_FOR_SESSION_ATTR_NAME = "CSRFToken";
	static final String MEM_KEY_NAME = "CSRFMemKey";

	/**
	 * 销毁缓存
	 *
	 * @param memcachedKey
	 *            缓存key
	 */
	public static void destroyTokenFormMemcached(final String memcachedKey) {
		try {
			redisCacheService.del(memcachedKey);
		} catch (final Exception e) {
			logger.error("[csrf]从Memcache删除缓存数据时发生异常：", e);
		}
	}

	/**
	 * 从request中拿到缓存的key
	 *
	 * @param request
	 *            HttpServletRequest
	 * @return 缓存的key
	 */
	public static String getMemcachedkeyFromRequest(
			final HttpServletRequest request) {
		return request.getParameter(MEM_KEY_NAME);
	}

	/**
	 * 根据缓存的key取token
	 *
	 * @param memcachedKey
	 *            缓存的key
	 * @return token
	 */
	static String getTokenForMemcached(final String memcachedKey) {
		String token = null;
		// I cannot allow more than one token on a session - in the case of two
		// requests trying to
		// init the token concurrently
		synchronized (sync) {
			try {
				token = redisCacheService.get(memcachedKey);
			} catch (final Exception e1) {
				logger.error("[[csrf]从Memcache中取缓存数据时发生异常：", e1);
			}
			if (null == token) {
				token = UUID.randomUUID().toString();
				try {
					redisCacheService.put(memcachedKey, token);
				} catch (final Exception e) {
					logger.error("[csrf]向Memcache缓存数据时发生异常：", e);
				}
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
		return request.getParameter(CSRF_TOKEN_FOR_SESSION_ATTR_NAME);
	}

	private CSRFCacheTokenManager() {
		// .
	}

}
