package com.yueny.cropui.controller.admin.tag;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yueny.blog.common.BlogConstant;
import com.yueny.blog.service.admin.tags.ICategoriesTagRelManageService;
import com.yueny.blog.vo.console.tags.TagsForCategoriesViewsVo;
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
	private ICategoriesTagRelManageService categoriesTagRelManageService;

	/**
	 * 全站文章分类管理页面
	 */
	@RequestMapping(value = "/categories_tag.html", method = { RequestMethod.GET })
	public String categoriesPage(final HttpServletResponse response) {
		setModelAttribute(WebAttributes.ACTION, "categoriesTag");
		setModelAttribute("title", "全站文章分类管理");

		try {
			final List<TagsForCategoriesViewsVo> categoriesTags = categoriesTagRelManageService.findAll();
			setModelAttribute("list", categoriesTags);
		} catch (final Exception e) {
			logger.error("【全站文章分类管理】出现错误!", e);
			return redirectAction("/");
		}

		return "admin/tag/categories_tag_list";
	}

	/**
	 * 获取具体的全站文章分类信息及所拥有的个人标签
	 */
	@RequestMapping(value = "/categories_tag/{categoriesTagCode}", method = RequestMethod.GET)
	@ResponseBody
	public TagsForCategoriesViewsVo getCategoriesData(@PathVariable final String categoriesTagCode,
			final HttpServletResponse response) {
		setModelAttribute(WebAttributes.ACTION, "categoriesTag");
		setModelAttribute("title", "全站文章分类信息");

		if (StringUtils.isEmpty(categoriesTagCode)) {
			return null;
		}

		final TagsForCategoriesViewsVo tagsForCategoriesViewsVo = new TagsForCategoriesViewsVo();
		try {
			final TagsForCategoriesViewsVo categoriesTag = categoriesTagRelManageService
					.findByTagsForCategories(categoriesTagCode);

			if (categoriesTag != null) {
				setModelAttribute("categoriesTag", categoriesTag);
			}
		} catch (final Exception e) {
			logger.error("【全站文章分类信息】出现错误!", e);
		}

		return tagsForCategoriesViewsVo;
	}

}
