package com.yueny.cropui.controller.admin.tag;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yueny.blog.common.BlogConstant;
import com.yueny.cropui.controller.BaseController;
import com.yueny.superclub.util.web.security.contanst.WebAttributes;

/**
 * 标签树管理控制器
 *
 * @author 袁洋 2015年8月24日 上午10:22:23
 *
 */
@Controller
@RequestMapping(value = BlogConstant.ADMIN_URL_PREFIX)
public class TagForAdminController extends BaseController {
	/**
	 * 用户标签列表
	 */
	@RequestMapping(value = "/owenerTags.html", method = RequestMethod.GET)
	public String getOwenerTag(final HttpServletResponse response) {
		setModelAttribute(WebAttributes.ACTION, "owenerTags");
		setModelAttribute("title", "用户标签列表");

		try {
			// 从session中获取uid
			final String uid = "yuanyang";
			// final OwenerTagsData owenerTagsData =
			// articleQueryService.getOwenerTag(uid);

			// 用户所拥有的owenerTags
			// setModelAttribute("owenerTags", owenerTagsData.getOwenerTags());
		} catch (final Exception e) {
			logger.error("【用户标签列表】出现错误!", e);
			// 文章出错,回首页
			return redirectAction("/");
		}

		return "admin/tag/listtag";
	}

}
