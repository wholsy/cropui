package com.yueny.cropui.controller.admin.blog;

import java.util.Collections;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.yueny.blog.bo.model.condition.ArticlePublishedCondition;
import com.yueny.blog.service.manage.IArticleManageService;
import com.yueny.cropui.controller.BaseController;
import com.yueny.rapid.data.resp.pojo.response.ListResponse;
import com.yueny.rapid.data.resp.pojo.response.NormalResponse;
import com.yueny.rapid.lang.agent.UserAgentResource;
import com.yueny.rapid.lang.enums.BaseErrorType;
import com.yueny.rapid.lang.exception.DataVerifyAnomalyException;
import com.yueny.rapid.lang.util.StringUtil;
import com.yueny.superclub.util.web.security.contanst.WebAttributes;

/**
 * 博客发布控制器
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2017年6月3日 下午10:13:06
 */
@RestController
@RequestMapping(value = "/admin")
public class WBlogPublishedController extends BaseController {
	@Autowired
	private IArticleManageService articleManageService;

	/**
	 * 写/编辑文章时根据文章标题和文章内容动态获取'推荐标签'
	 */
	@RequestMapping(value = "/article/published.html", method = { RequestMethod.POST })
	@ResponseBody
	@Deprecated
	public ListResponse<String> getPublishedTag(final String gettag, final HttpServletResponse response) {
		setModelAttribute(WebAttributes.ACTION, "getPublishedTag");

		final ListResponse<String> resp = new ListResponse<>();
		if (StringUtil.isEmpty(gettag)) {
			resp.setData(Collections.emptyList());
			return resp;
		}

		try {
			// '推荐标签'直接返回推荐的标签名称,不尽兴name的封装
			resp.setData(Lists.newArrayList("Server"));
		} catch (final Exception e) {
			resp.setCode(BaseErrorType.SYSTEM_BUSY.getCode());
			resp.setMessage(BaseErrorType.SYSTEM_BUSY.getMessage());
		}

		return resp;
	}

	/**
	 * 提交发布文章操作
	 *
	 * @param articlePublishedRequest
	 *            文章发布请求实体
	 * @param response
	 *            HttpServletResponse
	 * @return url
	 */
	@RequestMapping(value = { "/article/postedit/" }, method = {
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public NormalResponse<String> publishedArticle(@Valid final ArticlePublishedCondition condition,
			final HttpServletResponse httpResponse) {
		logger.info("【发布文章】请求参数:{}.", condition);

		final NormalResponse<String> response = new NormalResponse<>();
		try {
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
		}

		return response;
	}

}
