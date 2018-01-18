package com.yueny.cropui.controller.admin.tag;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yueny.blog.common.BlogConstant;
import com.yueny.blog.common.enums.CropuiToolkitsErrorType;
import com.yueny.blog.console.request.TagsForCategoriesModifyRequest;
import com.yueny.blog.console.vo.tags.TagsForCategorieBaseVo;
import com.yueny.blog.console.vo.tags.TagsForCategoriesViewsVo;
import com.yueny.blog.service.admin.manager.ICategoriesTagManagerService;
import com.yueny.cropui.controller.BaseController;
import com.yueny.rapid.data.resp.pojo.response.JsonNormalResponse;
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
	private ICategoriesTagManagerService categoriesTagManagerService;

	/**
	 * 获取具体的全站文章分类信息及所拥有的个人标签
	 */
	@RequestMapping(value = "/categories_tag/{categoriesTagCode}.html", method = RequestMethod.GET)
	public String selectCategoriesData(@PathVariable(required = false) final String categoriesTagCode,
			final HttpServletResponse response) {
		TagsForCategoriesViewsVo categoriesTag = new TagsForCategoriesViewsVo();

		if (StringUtils.isEmpty(categoriesTagCode)
				|| StringUtils.equals(categoriesTagCode, BlogConstant.CATEGORIES_TAG_CODE_FOR_ROOT)) {
			final List<TagsForCategorieBaseVo> tagsForUp = categoriesTagManagerService
					.findParentTagsByChildrenCodeForUp(BlogConstant.CATEGORIES_TAG_CODE_FOR_ROOT);
			setModelAttribute("tagsForUp", tagsForUp);
		} else {
			try {
				categoriesTag = categoriesTagManagerService.findByTagsForCode(categoriesTagCode);

				// 所归属的上级分类的所有的上级fen'le列表
				if (categoriesTag != null) {
					final List<TagsForCategorieBaseVo> tagsForUp = categoriesTagManagerService
							.findParentTagsByChildrenCodeForUp(categoriesTag.getCategoriesTagCode());
					setModelAttribute("tagsForUp", tagsForUp);
				}
			} catch (final Exception e) {
				logger.error("【全站文章分类信息】出现错误!", e);
			}
		}

		setModelAttribute("categoriesTag", categoriesTag);

		return "admin/tag/modal/categories_tag_edit_modal";
	}

	/**
	 * 全站文章分类管理页面
	 */
	@RequestMapping(value = "/categories_tag.html", method = { RequestMethod.GET })
	public String selectCategoriesPage(final HttpServletResponse response) {
		setModelAttribute(WebAttributes.ACTION, "categoriesTag");
		setModelAttribute("title", "全站文章分类管理");

		try {
			final List<TagsForCategoriesViewsVo> categoriesTags = categoriesTagManagerService.findAll();
			setModelAttribute("list", categoriesTags);
		} catch (final Exception e) {
			logger.error("【全站文章分类管理】出现错误!", e);
			return redirectAction("/");
		}

		return "admin/tag/categories_tag_list";
	}

	/**
	 * 提交修改信息
	 */
	@RequestMapping(value = "/categories_tag/update/", method = RequestMethod.PUT)
	@ResponseBody
	public JsonNormalResponse<Boolean> updateCategoriesTag(
			final @Valid TagsForCategoriesModifyRequest tagsForCategoriesModifyRequest,
			final HttpServletResponse response) {
		final JsonNormalResponse<Boolean> resp = new JsonNormalResponse<>();
		resp.setData(false);

		if (tagsForCategoriesModifyRequest == null) {
			resp.setCode(CropuiToolkitsErrorType.DATA_VERIFY_ERROR.getCode());
			resp.setMessage(CropuiToolkitsErrorType.DATA_VERIFY_ERROR.getMessage());
			return resp;
		}

		try {
			final boolean rs = categoriesTagManagerService.update(tagsForCategoriesModifyRequest);
			resp.setData(rs);
		} catch (final Exception e) {
			logger.error("【全站文章分类信息】出现错误!", e);

			resp.setCode(CropuiToolkitsErrorType.SYSTEM_BUSY.getCode());
			resp.setMessage(CropuiToolkitsErrorType.SYSTEM_BUSY.getMessage());
		}

		return resp;
	}

}
