package com.yueny.cropui.controller.admin.blog;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yueny.cropui.controller.BaseController;
import com.yueny.superclub.util.web.security.contanst.WebAttributes;

/**
 * 博客发布控制器
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2017年6月3日 下午4:27:56
 * @since
 */
@Controller
@RequestMapping(value = "/admin")
public class PublishedController extends BaseController {
	// @Autowired
	// private IArticleBlogService articleBlogService;

	//
	// @RequestMapping(value = "/do_show_list.json", method =
	// RequestMethod.POST)
	// @ResponseBody
	// public JsonListForPageResponse<ArticleSimpleBlogBo>
	// listBlogData(@RequestParam(value = "pageno") int pageno,
	// @Param("title_q") String title_q, final HttpServletResponse response) {
	// if (pageno <= 0) {
	// pageno = 1;
	// }
	//
	// final JsonListForPageResponse<ArticleSimpleBlogBo> res = new
	// JsonListForPageResponse<>();
	// try {
	// final PageCond pageable = new PageCond(pageno, 10);
	// final List<ArticleSimpleBlogBo> blogs =
	// articleBlogService.findPageListForSimpleWithTitle(pageable,
	// title_q);
	// res.setData(blogs);
	//
	// final String pages =
	// PageHtmlHelper.getPageHtmlByPage(pageable.getItems(), pageno,
	// pageable.getPageSize());
	// res.setPages(pages);
	// } catch (final Exception e) {
	// res.setCode(BaseErrorType.SYSTEM_BUSY.getCode());
	// res.setMessage(BaseErrorType.SYSTEM_BUSY.getMessage());
	// res.setData(Collections.emptyList());
	// }
	// return res;
	// }

	/**
	 * 博客发布页面<br>
	 * 此页面存在两种情况：<br>
	 * 写博客，此时页面只存在初始化加载数据<br>
	 * 编辑博客，此时页面初始化加载，并加载博文数据
	 */
	@RequestMapping(value = { "/wblog.html" }, method = { RequestMethod.GET })
	public String wblogPage(final HttpServletResponse response) {
		setModelAttribute(WebAttributes.ACTION, "WBLOG");

		return "admin/blog/wblog";
	}

}
