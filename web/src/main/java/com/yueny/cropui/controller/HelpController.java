package com.yueny.cropui.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yueny.blog.bo.help.AboutUsInfoBo;
import com.yueny.blog.service.IAboutUsInfoService;
import com.yueny.blog.service.IHelpService;
import com.yueny.superclub.util.web.security.contanst.WebAttributes;

/**
 * 帮助控制器
 *
 * @author 袁洋 2015年8月8日 下午2:07:03
 *
 */
@Controller
public class HelpController extends BaseController {
	@Autowired
	private IAboutUsInfoService aboutUsInfoService;
	@Autowired
	private IHelpService helpService;

	/**
	 * 关于我们
	 */
	@RequestMapping(value = "/about.html", method = { RequestMethod.GET })
	public String aboutusAction(final HttpServletResponse response) {
		setModelAttribute(WebAttributes.ACTION, "about");
		setModelAttribute("title", "关于我");

		final List<AboutUsInfoBo> us = aboutUsInfoService.queryAll();
		setModelAttribute("us", us);

		return "help/aboutus";
	}

	// /**
	// * 帮助中心
	// *
	// * @param request
	// * HttpServletRequest
	// * @param response
	// * HttpServletResponse
	// * @param dataMap
	// * 返回页面附带的信息
	// * @return 帮助中心
	// */
	// @RequestMapping(value = "/helpme/", method = { RequestMethod.GET },
	// produces = MediaType.APPLICATION_JSON_VALUE)
	// public String helpmeAction( final
	// HttpServletResponse response,
	// final Map<String, Object> dataMap) {
	// dataMap.put("title", "帮助中心");
	//
	// return "help/helpme";
	// }
	//
	// /**
	// * 我们的历史，开发历史报告，统计到天
	// *
	// * @param request
	// * HttpServletRequest
	// * @param response
	// * HttpServletResponse
	// * @param dataMap
	// * 返回页面附带的信息
	// * @return 我们的历史
	// */
	// @RequestMapping(value = "/ourdayhistory/", method = {
	// RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE)
	// public String ourDayHistoryAction(final HttpServletRequest request, final
	// HttpServletResponse response,
	// final Map<String, Object> dataMap) {
	// dataMap.put("title", "我们的历史-日度报告");
	//
	// // 获取用户信息
	// final String userCode = VisitContextHolder.getUid();
	// System.out.println(userCode);
	//
	// dataMap.put("hisDevReports", helpService.queryHisDevReportsDay());
	// return "help/ourdayhistory";
	// }
	//
	// /**
	// * 我们的历史，统计到月
	// *
	// * @param request
	// * HttpServletRequest
	// * @param response
	// * HttpServletResponse
	// * @param dataMap
	// * 返回页面附带的信息
	// * @return 我们的历史
	// */
	// @RequestMapping(value = "/ourhistory/", method = { RequestMethod.GET },
	// produces = MediaType.APPLICATION_JSON_VALUE)
	// public String ourHistoryAction(final HttpServletRequest request, final
	// HttpServletResponse response,
	// final Map<String, Object> dataMap) {
	// dataMap.put("title", "我们的历史-月度报告");
	//
	// dataMap.put("hisDevReports", helpService.queryHisDevReportsMonth());
	// return "help/ourhistory";
	// }
}
