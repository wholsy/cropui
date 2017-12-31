package com.yueny.cropui.controller.categories;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yueny.blog.bo.tag.CategoriesTagBo;
import com.yueny.blog.service.categories.ICategoriesTagService;
import com.yueny.cropui.controller.BaseController;
import com.yueny.superclub.util.web.security.contanst.WebAttributes;

/**
 * 全站分类控制器
 *
 * @author 袁洋 2015年8月24日 上午10:22:23
 *
 */
@Controller
public class CategoriesController extends BaseController {
	@Autowired
	private ICategoriesTagService articleCategoriesService;

	/**
	 * 全站分类类目页面
	 */
	@RequestMapping(value = "/super/categories.html", method = { RequestMethod.GET })
	public String categoriesPage(final HttpServletResponse response) {
		setModelAttribute(WebAttributes.ACTION, "categories");
		setModelAttribute("title", "全站分类");

		try {
			final List<CategoriesTagBo> lists = articleCategoriesService.findArticleCategoriesTree();
			setModelAttribute("lists", lists);
		} catch (final Exception e) {
			logger.error("【全站分类类目页面】出现错误!", e);
			return redirectAction("/");
		}

		return "categories/categories";
	}

}
