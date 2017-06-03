package com.yueny.cropui.controller.article;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.yueny.blog.bo.article.ArticleBlogBo;
import com.yueny.blog.bo.article.ArticleBlogViewBo;
import com.yueny.blog.bo.enums.ArticleSelType;
import com.yueny.blog.bo.model.condition.ArticlePublishedCondition;
import com.yueny.blog.bo.tag.CategoriesTagBo;
import com.yueny.blog.bo.tag.OwenerTagBo;
import com.yueny.blog.service.article.IArticleBlogService;
import com.yueny.blog.service.manage.IArticleManageService;
import com.yueny.blog.service.manage.IArticleQueryManageService;
import com.yueny.blog.service.tag.ICategoriesTagService;
import com.yueny.blog.service.tag.IOwenerTagService;
import com.yueny.cropui.controller.BaseController;
import com.yueny.rapid.data.resp.pojo.response.ListResponse;
import com.yueny.rapid.data.resp.pojo.response.NormalResponse;
import com.yueny.rapid.lang.agent.UserAgentResource;
import com.yueny.rapid.lang.enums.BaseErrorType;
import com.yueny.rapid.lang.exception.DataVerifyAnomalyException;
import com.yueny.rapid.lang.util.StringUtil;
import com.yueny.superclub.util.web.security.contanst.WebAttributes;

/**
 * 文章操作控制器
 *
 * @author 袁洋 2015年8月25日 上午10:18:37
 *
 */
@Controller
public class ArticleActionController extends BaseController {
	@Autowired
	private IArticleBlogService articleBlogService;
	@Autowired
	private IArticleManageService articleManageService;
	@Autowired
	private IArticleQueryManageService articleQueryService;
	@Autowired
	private ICategoriesTagService categoriesTagService;
	@Autowired
	private IOwenerTagService owenerTagService;

	/**
	 * 查看文章详细页面
	 *
	 * @param articleBlogId
	 *            文章扩展ID
	 */
	@RequestMapping(value = "/article/{articleBlogId}.html", method = { RequestMethod.GET })
	public String getArticleInfoPage(@PathVariable final String articleBlogId, final HttpServletResponse response) {
		setModelAttribute(WebAttributes.ACTION, "articleInfo");

		try {
			// 从session中获取uid
			// final String uid = "yuanyang";
			final ArticleBlogViewBo articleBlogView = articleQueryService.getArticleInfo(articleBlogId);
			// 当前博文
			final ArticleBlogBo articleBlog = articleBlogView.getArticleBlog();

			setModelAttribute("title", articleBlog.getArticleTitle());
			setModelAttribute("articleBlog", articleBlog);
			// 文章的个人分类信息
			setModelAttribute("owenerTags", articleBlogView.getOwenerTags());
			// 该博文所归属的全站文章分类
			setModelAttribute("categoriesTagList", articleBlogView.getCategoriesTagList());
			// 上一篇博文
			setModelAttribute("previousSimpleBlog", articleBlogView.getPreviousSimpleBlog());
			// 下一篇博文
			setModelAttribute("nextSimpleBlog", articleBlogView.getNextSimpleBlog());

			// 文章标签articleTagIds

		} catch (final DataVerifyAnomalyException e) {
			logger.error("【查看文章详细】出现错误!", e);
			// 文章出错,回首页
			return redirectAction("/");
		}

		return "article/article_blog_detail";
	}

	/**
	 * 新建/修改文章页面
	 *
	 * @param articleBlogId
	 *            将要修改的文章扩展ID
	 */
	@RequestMapping(value = "/article/published.html", method = { RequestMethod.GET })
	public String getPublishedPage(@RequestParam(value = "articleBlogId") final String articleBlogId,
			final HttpServletResponse response) {
		setModelAttribute(WebAttributes.ACTION, "articleWrite");

		/* 存在文章则获取文章数据 */
		// mode模式: 0新增, 1修改
		setModelAttribute("title", "创建我的新文章");
		setModelAttribute("mode", 0);
		if (StringUtil.isNotEmpty(articleBlogId)) {
			try {
				// 获取文章的基本信息
				final ArticleBlogBo articleBlog = articleBlogService.findByBlogId(articleBlogId);
				if (articleBlog != null) {
					setModelAttribute("mode", 1);
					setModelAttribute("title", "编辑文章:" + articleBlog.getArticleTitle());

					setModelAttribute("articleBlog", articleBlog);
				} else {
					// 文章不存在,回首页
					return redirectAction("/");
				}
			} catch (final Exception e) {
				logger.error("【发布文章】出现错误!", e);
				// 回首页
				return redirectAction("/");
			}
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

		return "user/article_write";
	}

	/**
	 * 写/编辑文章时根据文章标题和文章内容动态获取'推荐标签'
	 */
	@RequestMapping(value = "/article/published.html", method = { RequestMethod.POST })
	@ResponseBody
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
	 * 发布文章操作
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
