package com.yueny.cropui.controller.admin.tag;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yueny.blog.bo.tag.CategoriesTagBo;
import com.yueny.blog.common.BlogConstant;
import com.yueny.blog.service.categories.ICategoriesTagService;
import com.yueny.cropui.controller.BaseController;
import com.yueny.superclub.util.web.security.contanst.WebAttributes;

/**
 * 全站分类管理控制器
 *
 * @author 袁洋 2015年8月24日 上午10:22:23
 *
 */
@Controller
@RequestMapping(value = BlogConstant.ADMIN_URL_PREFIX)
public class CategoriesTagForAdminController extends BaseController {
	@Autowired
	private ICategoriesTagService articleCategoriesService;

	/**
	 * 全站文章分类管理页面
	 */
	@RequestMapping(value = "categories_tag.html", method = { RequestMethod.GET })
	public String categoriesPage(final HttpServletResponse response) {
		setModelAttribute(WebAttributes.ACTION, "categories");
		setModelAttribute("title", "全站文章分类管理");

		try {
			final List<CategoriesTagBo> categoriesTags = articleCategoriesService.findArticleCategoriesTree();
			setModelAttribute("list", categoriesTags);
		} catch (final Exception e) {
			logger.error("【全站文章分类管理】出现错误!", e);
			return redirectAction("/");
		}

		return "admin/tag/categories_tag_list";
	}

}
