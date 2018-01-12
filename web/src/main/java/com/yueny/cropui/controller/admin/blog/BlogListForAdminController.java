package com.yueny.cropui.controller.admin.blog;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yueny.blog.bo.resp.JqGridDataJsonListForPageResponse;
import com.yueny.blog.common.BlogConstant;
import com.yueny.blog.service.article.IArticleBlogService;
import com.yueny.blog.service.manage.IArticleManageService;
import com.yueny.blog.vo.console.article.ArticleTagBlogVo;
import com.yueny.cropui.controller.BaseController;
import com.yueny.rapid.data.resp.pojo.response.JsonNormalResponse;
import com.yueny.rapid.lang.enums.BaseErrorType;
import com.yueny.rapid.lang.exception.DataVerifyAnomalyException;
import com.yueny.superclub.api.page.core.PageCond;
import com.yueny.superclub.util.web.security.contanst.WebAttributes;

/**
 * 后台博客控制器
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2017年6月3日 下午2:12:19
 * @since
 */
@Controller
@RequestMapping(value = BlogConstant.ADMIN_URL_PREFIX)
public class BlogListForAdminController extends BaseController {
	@Autowired
	private IArticleBlogService articleBlogService;
	@Autowired
	private IArticleManageService articleManageService;

	/**
	 * 博客删除
	 *
	 * @param articleBlogId
	 *            文章对外ID
	 */
	@RequestMapping(value = "/del.json", method = RequestMethod.DELETE)
	@ResponseBody
	public JsonNormalResponse<Boolean> dele(@RequestParam(value = "articleBlogId") final String articleBlogId,
			final HttpServletResponse response) {
		final JsonNormalResponse<Boolean> res = new JsonNormalResponse<>();
		res.setData(false);

		try {
			final boolean rs = articleManageService.delArticleBlog(articleBlogId);
			res.setData(rs);
		} catch (final DataVerifyAnomalyException e) {
			res.setCode(e.getErrorCode());
			res.setMessage(e.getErrorMsg());
		} catch (final Exception e) {
			res.setCode(BaseErrorType.SYSTEM_BUSY.getCode());
			res.setMessage(BaseErrorType.SYSTEM_BUSY.getMessage());
		}
		return res;
	}

	/**
	 * 博客列表查询
	 *
	 * @param pageno
	 *            当前分页
	 * @param title_q
	 *            标题模糊查询
	 * @return
	 */
	@RequestMapping(value = "/service/do_show_list.json", method = RequestMethod.POST)
	@ResponseBody
	public JqGridDataJsonListForPageResponse<ArticleTagBlogVo> listBlogData(
			@RequestParam(value = "pageno", required = false, defaultValue = "1") int pageno,
			@RequestParam(value = "title_q", required = false, defaultValue = "") final String title_q,
			final HttpServletResponse response) {
		if (pageno <= 0) {
			pageno = 1;
		}

		final JqGridDataJsonListForPageResponse<ArticleTagBlogVo> res = new JqGridDataJsonListForPageResponse<>();
		try {
			final PageCond pageable = new PageCond(pageno, 10);
			final List<ArticleTagBlogVo> blogs = articleBlogService.findPageListForSimpleWithTitle(pageable, title_q);
			res.setData(blogs);
			res.setCurrentpage(pageable.getCurrentPage());
			res.setRows(pageable.getPageSize());
			res.setTotal(pageable.getPages());
			res.setRecords(pageable.getItems());
		} catch (final Exception e) {
			res.setCode(BaseErrorType.SYSTEM_BUSY.getCode());
			res.setMessage(BaseErrorType.SYSTEM_BUSY.getMessage());
			res.setData(Collections.emptyList());
		}

		logger.info("res: {}.", res);
		return res;
	}

	/**
	 * 博客列表页面
	 */
	@RequestMapping(value = "/list_blog.html", method = RequestMethod.GET)
	public String listBlogPage(final HttpServletResponse response) {
		setModelAttribute(WebAttributes.ACTION, "LIST_BLOG");
		setModelAttribute("title", "博客列表");

		// 博文总数
		setModelAttribute("blogTotal", 12);
		// 上月新增博文数
		setModelAttribute("incrForLastMonth", 2);

		return "admin/blog/listblog";
	}

}
