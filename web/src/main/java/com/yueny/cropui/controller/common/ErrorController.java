package com.yueny.cropui.controller.common;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yueny.cropui.controller.BaseController;

/**
 * 错误控制器
 *
 * @author 袁洋 2015年8月6日 下午2:05:09
 *
 */
@Controller
public class ErrorController extends BaseController {
	/**
	 * 403
	 *
	 * @param response
	 *            HttpServletResponse
	 * @return url page
	 */
	@RequestMapping(value = "/403")
	public String to403(final HttpServletResponse response) {

		return "110/404";
	}

	/**
	 * 404
	 *
	 * @param response
	 *            HttpServletResponse
	 * @return url page
	 */
	@RequestMapping(value = "/404")
	public String to404(final HttpServletResponse response) {

		return "110/404";
	}

	/**
	 * error
	 *
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return url page
	 */
	@RequestMapping(value = "/error")
	public String toError(final HttpServletResponse response) {
		return "110/502";
	}

}
