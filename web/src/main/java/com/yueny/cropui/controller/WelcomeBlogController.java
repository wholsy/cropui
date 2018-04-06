package com.yueny.cropui.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yueny.blog.bo.article.ArticleBlogBo;
import com.yueny.blog.service.table.IArticleBlogService;
import com.yueny.superclub.api.page.core.PageCond;
import com.yueny.superclub.util.web.security.contanst.WebAttributes;
import com.yueny.superclub.util.web.security.context.VisitContextHolder;

/**
 * 首页控制器
 *
 * @author 袁洋 2015年8月6日 下午2:05:09
 *
 */
@Controller
public class WelcomeBlogController extends BaseController {
	// @Autowired
	// private PassportQueryClient passportQueryClient;
	@Autowired
	private IArticleBlogService articleBlogService;

	/**
	 * 博客首页
	 *
	 * @param response
	 *            HttpServletResponse
	 * @return url page
	 */
	// TODO 该接口需要分页
	@RequestMapping(value = { "/", "/welcome", "/postlist" }, method = { RequestMethod.GET })
	public String welcome(Integer currentPage, Integer pageSize, final HttpServletResponse response) {
		setModelAttribute(WebAttributes.ACTION, "welcome");
		setModelAttribute("title", "正值少年-云少");

		System.out.println(VisitContextHolder.isLogin());

		if (currentPage == null) {
			currentPage = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		final PageCond pageable = new PageCond(currentPage, pageSize);
		final List<ArticleBlogBo> articleBlogs = articleBlogService.findPageList(pageable);
		setModelAttribute("articleBlogs", articleBlogs);

		return "home/welcome";
	}

}
