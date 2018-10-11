package com.yueny.cropui.controller.admin.blog;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Lists;
import com.yueny.blog.bo.article.ArticleBlogBo;
import com.yueny.blog.bo.enums.ArticleSelType;
import com.yueny.blog.bo.tag.CategoriesTagBo;
import com.yueny.blog.bo.tag.OwenerTagBo;
import com.yueny.blog.common.BlogConstant;
import com.yueny.blog.service.IArticleBlogService;
import com.yueny.blog.service.ICategoriesTagService;
import com.yueny.blog.service.IOwenerTagService;
import com.yueny.cropui.controller.BaseController;
import com.yueny.rapid.lang.util.StringUtil;
import com.yueny.superclub.util.web.security.contanst.WebAttributes;

/**
 * 博客发布页面控制器
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2017年6月3日 下午4:27:56
 * @since
 */
@Controller
@RequestMapping(value = BlogConstant.ADMIN_URL_PREFIX)
public class WBlogPageController extends BaseController {
	@Autowired
	private IArticleBlogService articleBlogService;
	@Autowired
	private ICategoriesTagService categoriesTagService;
	@Autowired
	private IOwenerTagService owenerTagService;

	private boolean assemblyMdeditorContext(String articleBlogId) {
		setModelAttribute(WebAttributes.ACTION, "WBLOG");

		if (StringUtil.isNotEmpty(articleBlogId)) {
			try {
				// 获取文章的基本信息
				final ArticleBlogBo articleBlog = articleBlogService.findByBlogId(articleBlogId);
				if (articleBlog != null) {
					setModelAttribute("mode", 1);
					setModelAttribute("title", "编辑文章:" + articleBlog.getArticleTitle());

					setModelAttribute("item", articleBlog);
				} else {
					return false;
				}
			} catch (final Exception e) {
				logger.error("【发布文章】出现错误!", e);
				return false;
			}
		} else {
			setModelAttribute("mode", 0);
			setModelAttribute("title", "我的文章");
			setModelAttribute("item", null);
		}

		/* 获取固定数据字典 */
		// 获取 '文章标题类型' 列表
		final List<ArticleSelType> articleSelTypes = Lists.newArrayList(ArticleSelType.values());
		setModelAttribute("articleSelTypes", articleSelTypes);

		// 获取 文章标签-'常用标签'
		// TODO

		// 获取 '个人分类' 列表
		// 从session中获取uid
		final String uid = "yuanyang";
		final List<OwenerTagBo> owenerTags = owenerTagService.queryByUid(uid);
		setModelAttribute("owenerTags", owenerTags);

		// 获取'文章分类' 列表 delete
		final List<CategoriesTagBo> categoryTags = categoriesTagService.findArticleCategoriesTree();
		setModelAttribute("categoryTags", categoryTags);

		return true;
	}

	/**
	 * 博客HTML发布页面<br>
	 * 此页面存在两种情况：<br>
	 * 写博客，此时页面只存在初始化加载数据<br>
	 * 编辑博客，此时页面初始化加载，并加载博文数据
	 */
	@RequestMapping(value = "/wblog.html", method = RequestMethod.GET)
	public String wblogPage(@RequestParam(value = "articleBlogId", defaultValue = "") final String articleBlogId,
			final HttpServletResponse response) {
		/*
		 * 存在文章则获取文章数据
		 *
		 * mode模式: 0新增, 1修改
		 */
		// 是否是草稿
		setModelAttribute("isdraft", false);
		setModelAttribute("html", true);

		final boolean rs = assemblyMdeditorContext(articleBlogId);
		if (!rs) {
			// 出现异常，回首页
			return redirectAction("/");
		}

		return BlogConstant.ADMIN_PAGE_URI_PREFIX + "blog/wblog_md";
	}

}
