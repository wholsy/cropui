package com.yueny.cropui.service.csrf.session;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.RequestDataValueProcessor;

/**
 * 与页面中的<@form.form>配合使用，生成token的隐藏域(自动创建hidden的csrfToken域)
 *
 * @author 袁洋 2015年8月7日 上午10:39:32
 *
 */
// @Component("requestDataValueProcessor")
public class CSRFSessionRequestDataValueProcessor implements
RequestDataValueProcessor {
	/**
	 * 日志记录器
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Map<String, String> getExtraHiddenFields(
			final HttpServletRequest request) {
		// 此处是当使用spring的taglib标签<form:form>创建表单时候，增加的隐藏域参数
		final Map<String, String> hiddenFields = new HashMap<String, String>();
		hiddenFields.put(CSRFTokenSessionManager.CSRF_PARAM_NAME,
				CSRFTokenSessionManager.createTokenForSession(request
						.getSession()));

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
