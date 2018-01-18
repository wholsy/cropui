/**
 *
 */
package com.yueny.blog.common.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年1月17日 下午7:20:11
 *
 */
public class AjaxUtils {
	private static final Logger logger = LoggerFactory.getLogger(AjaxUtils.class);
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * 验证是否是ajax请求
	 *
	 * @param webRequest
	 * @return
	 */
	public static boolean isAjaxRequest(final WebRequest webRequest) {
		final String requestedWith = webRequest.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}

	public static boolean isAjaxUploadRequest(final WebRequest webRequest) {
		return webRequest.getParameter("ajaxUpload") != null;
	}

	public static void writeJson(final Object value, final HttpServletResponse response) {
		JsonGenerator jsonGenerator = null;
		try {
			jsonGenerator = mapper.getFactory().createGenerator(response.getOutputStream(), JsonEncoding.UTF8);
			if (jsonGenerator != null) {
				jsonGenerator.writeObject(value);
			}
		} catch (final IOException e) {
			logger.error("", e);
		}

	}

	private AjaxUtils() {
	}

}
