package com.yueny.cropui.controller.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alibaba.fastjson.JSONObject;

/**
 * 全局异常处理
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月13日 下午8:22:09
 *
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	// ModelAndView
	@ExceptionHandler(value = Exception.class)
	public String defaultErrorHandler(final HttpServletRequest req, final HttpServletResponse response,
			final Exception e) throws Exception {
		// If the exception is annotated with @ResponseStatus rethrow it and let
		// the framework handle it - like the OrderNotFoundException example
		// at the start of this post.
		// AnnotationUtils is a Spring Framework utility class.
		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
			throw e;
		}

		// 添加自己的异常处理逻辑，如日志记录等
		// .

		// 如果是json格式的ajax请求
		if (req.getHeader("accept").indexOf("application/json") > -1 || (req.getHeader("X-Requested-With") != null
				&& req.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1)) {
			response.setStatus(500);
			rendJson(response, "code", e.getMessage());
			return null;
		}

		// 如果是普通请求
		// Otherwise setup and send the user to a default error-view.
		req.setAttribute("message", e);
		req.setAttribute("url", req.getRequestURL());
		return "redirect:/error";
	}

	private void rendJson(final HttpServletResponse response, final String code, final String message)
			throws IOException {
		final JSONObject json = new JSONObject();
		json.put("code", code);
		json.put("message", message);
		rendText(response, json.toString());
	}

	private void rendText(final HttpServletResponse response, final String content) throws IOException {
		response.setContentType("application/json;charset=utf-8");
		// response.setCharacterEncoding("UTF-8");

		try {
			final PrintWriter writer = response.getWriter();
			writer.write(content);
			writer.flush();
		} catch (final IOException e1) {
			e1.printStackTrace();
		}
	}
}
