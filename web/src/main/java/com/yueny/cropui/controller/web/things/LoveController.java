package com.yueny.cropui.controller.web.things;

import com.yueny.blog.bo.things.LoveBo;
import com.yueny.blog.common.BlogConstant;
import com.yueny.blog.service.things.ILoveService;
import com.yueny.cropui.controller.BaseController;
import com.yueny.superclub.util.web.security.contanst.WebAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 *
 */
@Controller
public class LoveController extends BaseController {
	@Autowired
	private ILoveService loveService;
	/**
	 *
	 */
	@RequestMapping(value = "/love.html", method = { RequestMethod.GET })
	public String loveAction(final HttpServletResponse response) {
		setModelAttribute(WebAttributes.ACTION, "love");
		setModelAttribute("title", "表白");

		LoveBo love = loveService.queryInfo();

		setModelAttribute("love", love);
		setModelAttribute("fullYear", love.getFullYear());
		setModelAttribute("month", love.getMonth());
		setModelAttribute("day", love.getDay());
		setModelAttribute("hours", love.getHours());
		setModelAttribute("minutes", love.getMinutes());
		setModelAttribute("seconds", love.getSeconds());
		setModelAttribute("milliseconds", love.getMilliseconds());
		setModelAttribute("Mr", love.getMr());
		setModelAttribute("Mrs", love.getMrs());

		return BlogConstant.WEB_PAGE_URI_PREFIX + "things/love";
	}

}
