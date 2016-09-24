package com.yueny.cropui.controller.document;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yueny.blog.bo.model.document.OwenerTagsData;
import com.yueny.blog.bo.tag.CategoriesTagBo;
import com.yueny.blog.service.manage.IArticleQueryManageService;
import com.yueny.cropui.controller.BaseController;
import com.yueny.rapid.lang.exception.DataVerifyAnomalyException;
import com.yueny.superclub.util.web.security.contanst.WebAttributes;

/**
 * 标签树控制器
 *
 * @author 袁洋 2015年8月24日 上午10:22:23
 *
 */
@Controller
public class TagController extends BaseController {
	@Autowired
	private IArticleQueryManageService articleQueryService;

	/**
	 * 用户标签信息
	 */
	@RequestMapping(value = "/owenerTags.html", method = { RequestMethod.GET })
	public String getOwenerTag(final HttpServletResponse response) {
		setModelAttribute(WebAttributes.ACTION, "categories");
		setModelAttribute("title", "我的分类信息");

		try {
			// 从session中获取uid
			final String uid = "yuanyang";
			final OwenerTagsData owenerTagsData = articleQueryService.getOwenerTag(uid);

			// 用户所拥有的owenerTags
			setModelAttribute("owenerTags", owenerTagsData.getOwenerTags());
			setModelAttribute("tagSimpleBlogs", owenerTagsData.getTagSimpleBlogs());
		} catch (final DataVerifyAnomalyException e) {
			logger.error("【获得分类信息】出现错误!", e);
			// 文章出错,回首页
			return redirectAction("/");
		}

		return "user/owener_tags";
	}

	/**
	 * 标签树,与用户无关
	 */
	@RequestMapping(value = "/tags.html", method = { RequestMethod.GET })
	public String getTagTrees(final HttpServletResponse response) {
		setModelAttribute(WebAttributes.ACTION, "tags");
		setModelAttribute("title", "标签树");

		// try {
		// //
		// } catch (final DataVerifyAnomalyException e) {
		// logger.error("【标签树】出现错误!", e);
		// // 文章出错,回首页
		// return redirectAction("/");
		// }

		return "document/tags_tree";
	}

	/**
	 * 标记所属文章
	 *
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param dataMap
	 *            返回的数据
	 * @return url
	 */
	@RequestMapping(value = { "/tag/{data}.json" })
	@ResponseBody
	@Deprecated
	public List<CategoriesTagBo> queryTagData(final HttpServletRequest request, final HttpServletResponse response,
			final Map<String, Object> dataMap) {

		return null;
	}

}
