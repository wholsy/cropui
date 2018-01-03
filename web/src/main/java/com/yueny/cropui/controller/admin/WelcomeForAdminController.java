package com.yueny.cropui.controller.admin;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yueny.blog.common.BlogConstant;
import com.yueny.cropui.controller.BaseController;
import com.yueny.superclub.util.web.security.contanst.WebAttributes;

/**
 * 后台首页控制器
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2017年6月3日 下午1:24:09
 * @since
 */
@Controller
@RequestMapping(value = BlogConstant.ADMIN_URL_PREFIX)
public class WelcomeForAdminController extends BaseController {
	/**
	 * 后台首页
	 */
	@RequestMapping(value = { "/", "/welcome.html" }, method = RequestMethod.GET)
	public String adminIndex(final HttpServletResponse response) {
		setModelAttribute(WebAttributes.ACTION, "INDEX");
		setModelAttribute("title", "正值少年");

		return "admin/index";
	}

}
