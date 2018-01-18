package com.yueny.cropui.controller.article;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yueny.blog.bo.tag.CategoriesTagBo;
import com.yueny.blog.bo.tag.OwenerTagBo;
import com.yueny.blog.service.table.ICategoriesTagService;
import com.yueny.blog.service.table.IOwenerTagService;
import com.yueny.cropui.controller.BaseController;
import com.yueny.superclub.util.web.security.contanst.WebAttributes;

/**
 * 我的文章标签类目控制器
 *
 * @author 袁洋 2015年8月24日 上午10:22:23
 *
 */
@Controller
public class ArticleOwenerTagController extends BaseController {
	@Autowired
	private ICategoriesTagService articleCategoriesService;
	@Autowired
	private IOwenerTagService owenerTagService;

	// /**
	// * 获取当前文章分类类目详细信息
	// *
	// * @param response
	// * HttpServletResponse
	// * @return url
	// */
	// @RequestMapping(value = {
	// "/article/categories/detail/{categoriesId}.json" })
	// @ResponseBody
	// @Deprecated
	// public CategoriesTagBo queryArticleCategoriesDetail(final
	// HttpServletResponse response) {
	// final CategoriesTagBo articleCategoriesBo = new CategoriesTagBo();
	// logger.info("【获取当前文章分类类目详细信息】获取当前文章分类类目详细信息:{}.", articleCategoriesBo);
	//
	// return articleCategoriesBo;
	// }

	/**
	 * 用户分类管理页面
	 *
	 * @param response
	 *            HttpServletResponse
	 * @return url
	 */
	@RequestMapping(value = "owenerCategories.html")
	public String owenerCategories(final HttpServletResponse response) {
		setModelAttribute(WebAttributes.ACTION, "owenerCategories");
		setModelAttribute("title", "用户分类管理");

		try {
			final List<CategoriesTagBo> categoriesTagTrees = articleCategoriesService.findRootArticleCategories();
			logger.info("【用户分类管理】获取全站分类类目树:{}.", categoriesTagTrees);
			setModelAttribute("categoriesTagTrees", categoriesTagTrees);

			// 从session中获取uid
			final String uid = "yuanyang";
			final List<OwenerTagBo> owenerTagBos = owenerTagService.queryAllByUid(uid);
			setModelAttribute("owenerTagBos", owenerTagBos);
		} catch (final Exception e) {
			logger.error("【发布文章】出现错误!", e);
			// 回首页
			return redirectAction("/");
		}

		return "user/owener_tags_manage";
	}

}
