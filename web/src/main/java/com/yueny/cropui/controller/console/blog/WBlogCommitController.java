package com.yueny.cropui.controller.console.blog;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yueny.blog.bo.enums.BlogResultCodeType;
import com.yueny.blog.bo.model.condition.ArticlePublishedCondition;
import com.yueny.blog.common.BlogConstant;
import com.yueny.blog.service.admin.manager.IArticleBlogManagerService;
import com.yueny.cropui.controller.BaseController;
import com.yueny.rapid.data.resp.pojo.response.NormalResponse;
import com.yueny.rapid.lang.agent.UserAgentResource;
import com.yueny.rapid.lang.exception.DataVerifyAnomalyException;
import com.yueny.rapid.lang.exception.invalid.InvalidException;
import com.yueny.rapid.lang.util.StringUtil;

/**
 * 博客发布、提交控制器
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2017年6月3日 下午10:13:06
 */
@RestController
@RequestMapping(value = BlogConstant.ADMIN_URL_PREFIX)
public class WBlogCommitController extends BaseController {
	@Autowired
	private IArticleBlogManagerService articleManageService;

	/**
	 * 提交发布文章操作
	 *
	 * @return url
	 */
	@RequestMapping(value = "/article/postedit/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public NormalResponse<String> publishedArticle(@Valid final ArticlePublishedCondition condition,
			@RequestParam(value = "isHtml", defaultValue = "true") final Boolean isHtml,
			final HttpServletResponse httpResponse) {
		logger.info("【发布文章】请求参数:{}/{}.", isHtml, condition);

		final NormalResponse<String> response = new NormalResponse<>();
		try {
			if (StringUtils.isEmpty(condition.getOwenerTag())) {
				throw new InvalidException(BlogResultCodeType.INVALID_IS_NULL_FOR_OWENER_TAG);
			}
			if (StringUtils.isEmpty(condition.getCategoryTagCode())) {
				throw new InvalidException(BlogResultCodeType.INVALID_IS_NULL_FOR_CATEGORY_TAG_CODE);
			}

			final UserAgentResource agent = getCurrentUserAgent();

			// 从session中获取uid
			final String uid = "yuanyang";

			String articleBlogId;
			if (StringUtil.isNotEmpty(condition.getArticleBlogId())) {
				// 修改
				articleBlogId = articleManageService.editBlog(uid, condition, agent);
			} else {
				// 新增
				articleBlogId = articleManageService.addBlog(uid, condition, agent);
			}

			response.setData(articleBlogId);
		} catch (final DataVerifyAnomalyException e) {
			logger.error("【发布文章】出现错误!", e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getErrorMsg());
		} catch (final InvalidException e) {
			logger.error("【发布文章】出现错误!", e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getErrorMsg());
		}

		return response;
	}

}
