package com.yueny.cropui.service.csrf.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.RequestDataValueProcessor;

/**
 * 与页面中的<@form.form>配合使用，生成token的隐藏域
 *
 * @author 袁洋 2015年8月7日 上午10:39:32
 *
 */
public class CSRFCacheRequestDataValueProcessor implements
RequestDataValueProcessor {
	/**
	 * 日志记录器
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Map<String, String> getExtraHiddenFields(
			final HttpServletRequest request) {
		final Map<String, String> hiddenFields = new HashMap<String, String>();

		String memKey = CSRFCacheTokenManager
				.getMemcachedkeyFromRequest(request);
		if (StringUtils.isEmpty(memKey)) {
			memKey = UUID.randomUUID().toString();
		}
		hiddenFields.put(CSRFCacheTokenManager.MEM_KEY_NAME, memKey);
		hiddenFields.put(
				CSRFCacheTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME,
				CSRFCacheTokenManager.getTokenForMemcached(memKey));

		return hiddenFields;
	}

	@Override
	public String processAction(final HttpServletRequest request,
			final String action, final String httpMethod) {
		return action;
	}

	@Override
	public String processFormFieldValue(final HttpServletRequest request,
			final String name, final String value, final String type) {
		return value;
	}

	@Override
	public String processUrl(final HttpServletRequest request, final String url) {
		return url;
	}

}
