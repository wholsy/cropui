package com.yueny.blog.service.admin.manager.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.yueny.blog.bo.article.ArticleBlogBo;
import com.yueny.blog.bo.enums.ArticleSelType;
import com.yueny.blog.bo.enums.BlogResultCodeType;
import com.yueny.blog.bo.model.condition.ArticlePublishedCondition;
import com.yueny.blog.bo.tag.CategoriesTagBo;
import com.yueny.blog.bo.tag.OwenerTagBo;
import com.yueny.blog.console.vo.article.ArticleBlogForCategoryTagModel;
import com.yueny.blog.console.vo.article.ArticleTagBlogVo;
import com.yueny.blog.service.BaseBiz;
import com.yueny.blog.service.IArticleBlogService;
import com.yueny.blog.service.ICategoriesTagService;
import com.yueny.blog.service.IOwenerTagService;
import com.yueny.blog.service.admin.manager.IArticleBlogManagerService;
import com.yueny.blog.service.comp.uid.ArticleBlogIdGeneraterService;
import com.yueny.rapid.lang.agent.UserAgentResource;
import com.yueny.rapid.lang.date.DateFormatType;
import com.yueny.rapid.lang.date.DateUtil;
import com.yueny.rapid.lang.enums.BaseErrorType;
import com.yueny.rapid.lang.exception.DataVerifyAnomalyException;
import com.yueny.rapid.lang.util.HtmlRegexpUtil;
import com.yueny.rapid.lang.util.StringUtil;
import com.yueny.rapid.lang.util.collect.CollectionUtil;
import com.yueny.rapid.topic.profiler.ProfilerLog;
import com.yueny.superclub.api.constant.Constants;
import com.yueny.superclub.api.page.IPageable;

import lombok.SneakyThrows;

/**
 * 文章管理服务(增删改查)
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月1日 下午5:33:53
 *
 */
@Service
public class ArticleBlogManagerServiceImpl extends BaseBiz implements IArticleBlogManagerService {
	@Autowired
	private IArticleBlogService articleBlogService;
	@Autowired
	private ArticleBlogIdGeneraterService blogIdGenerate;
	@Autowired
	private ICategoriesTagService categoriesTagService;
	@Autowired
	private IOwenerTagService owenerTagService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.yueny.blog.service.manage.IArticleManageService#addBlog(java.lang.
	 * String, com.yueny.blog.bo.model.condition.ArticlePublishedCondition,
	 * com.yueny.rapid.lang.agent.UserAgentResource)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@ProfilerLog
	@SneakyThrows(value = { DataVerifyAnomalyException.class })
	public String addBlog(final String uid, final ArticlePublishedCondition condition, final UserAgentResource agent) {
		// 第一篇文章不存在上一篇,故为-1
		String articlePreviousBlogId = "-1";

		// 得到此文章发布前最新的一笔博文
		final String articleBlogIdTmp = articleBlogService.findLatestBlogId(uid);
		if (StringUtils.isNotEmpty(articleBlogIdTmp)) {
			articlePreviousBlogId = articleBlogIdTmp;
		}

		final ArticleBlogBo articleBlogBo = new ArticleBlogBo();
		articleBlogBo.setArticleBlogId(blogIdGenerate.getOps());
		articleBlogBo.setArticlePreviousBlogId(articlePreviousBlogId);
		articleBlogBo.setUid(uid);
		articleBlogBo.setArticleAlias(HtmlRegexpUtil.filterHtmlTag(condition.getArticleAlias()));
		articleBlogBo.setArticleTitle(HtmlRegexpUtil.filterHtmlTag(condition.getArticleTitle()));
		articleBlogBo.setArticleContext(condition.getArticleContext());
		articleBlogBo.setArticleContextForMd(condition.getArticleContextForMd());
		articleBlogBo.setArticleMore(condition.getArticleMore());
		articleBlogBo.setSelTypeCode(ArticleSelType.getTypeByValue(condition.getSelTypeCode()));
		articleBlogBo.setReadTimes(0);

		// 如果'摘要'为空,则取文章标题
		if (StringUtils.isEmpty(condition.getArticleDigest())) {
			// 过滤html
//			final String articleDigest = HtmlRegexpUtil.filterHtmlTag(articleBlogBo.getArticleContextForMd(), true);
			final String articleDigest = articleBlogBo.getArticleContextForMd();
			articleBlogBo.setArticleDigest(StringUtils.trim(articleDigest));
		} else {
			// 过滤html和空格
//			articleBlogBo
//					.setArticleDigest(StringUtils.trim(HtmlRegexpUtil.filterHtmlTag(condition.getArticleDigest())));
			articleBlogBo
					.setArticleDigest(StringUtils.trim(condition.getArticleDigest()));
		}

		// 个人分类,多个分类之间用“,”分隔,eg: '1,3,6,8,love'
		final Map<String, OwenerTagBo> owenerTags = operationOwenerTagIdsBySave(uid, condition.getOwenerTag());
		articleBlogBo.setOwenerTagIds(owenerTags.keySet());

		// 全站文章分类（到分类首页）， 如果发布文章时未选择，则使用‘个人分类’的默认全站分类
		final Set<String> categoryTagCodes = Sets.newHashSet();
		if (StringUtil.isEmpty(condition.getCategoryTagCode())) {
			for (final Map.Entry<String, OwenerTagBo> entry : owenerTags.entrySet()) {
				categoryTagCodes.add(entry.getValue().getCategoriesTagCode());
			}
		} else {
			categoryTagCodes.add(condition.getCategoryTagCode());
		}
		articleBlogBo.setCategoryTagCodes(categoryTagCodes);

		// TODO 保存浏览器信息
		System.out.println("浏览器信息: " + agent);

		articleBlogService.insert(articleBlogBo);
		return articleBlogBo.getArticleBlogId();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.yueny.blog.service.manage.IArticleManageService#delArticleBlog(java.
	 * lang. String)
	 */
	@Override
	public boolean delArticleBlog(final String articleBlogId) throws DataVerifyAnomalyException {
		// 获取文章信息
		final ArticleBlogBo blog = articleBlogService.findByBlogId(articleBlogId);

		if (blog == null) {
			throw new DataVerifyAnomalyException(BlogResultCodeType.BLOG_RECORD_NOT_EXIT);
		}

		// 该文章分类信息下的使用次数首先减一
		for (final Object owenerTagId : blog.getOwenerTagIds()) {
			if (!owenerTagService.plusCorrelaArticle(Long.parseLong(owenerTagId.toString()), -1)) {
				throw new DataVerifyAnomalyException(BaseErrorType.SYSTEM_ERROR);
			}
		}

		// 删除blog
		return articleBlogService.deleteByBlogId(articleBlogId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.yueny.blog.service.manage.IArticleManageService#editBlog(java.lang.
	 * String, com.yueny.blog.bo.model.condition.ArticlePublishedCondition,
	 * com.yueny.rapid.lang.agent.UserAgentResource)
	 */
	@Override
	@Transactional
	@ProfilerLog
	@SneakyThrows(value = { DataVerifyAnomalyException.class })
	public String editBlog(final String uid, final ArticlePublishedCondition condition, final UserAgentResource agent)
			throws DataVerifyAnomalyException {
		final String articleBlogId = condition.getArticleBlogId();

		// 查看是否存在博文
		final ArticleBlogBo articleBlogBo = articleBlogService.findByBlogId(articleBlogId);
		if (articleBlogBo == null) {
			logger.error("文章：{} 不存在！", articleBlogId);
			// String msg =
			// String.format(BlogResultCodeType.RECORD_NOT_EXIT_PLACE.getMessage(),
			// articleBlogId);
			// throw new
			// DataVerifyAnomalyException(BlogResultCodeType.RECORD_NOT_EXIT_PLACE.getCode(),msg);
			throw new DataVerifyAnomalyException(BaseErrorType.RECORD_NOT_EXIT);
		}
		if (!StringUtils.equals(articleBlogBo.getUid(), uid)) {
			logger.error("文章：{} 拥有者:{}与当前操作者:{}信息不一致！", articleBlogId, articleBlogBo.getUid(), uid);
			throw new DataVerifyAnomalyException(BlogResultCodeType.ILLEGAL_REQUEST);
		}

		// 组装博文修改对象
		// 文章对外ID,阅读次数 不可被修改
		articleBlogBo.setArticleAlias(HtmlRegexpUtil.filterHtmlTag(condition.getArticleAlias()));
		articleBlogBo.setArticleTitle(HtmlRegexpUtil.filterHtmlTag(condition.getArticleTitle()));
		// TODO 后续协商为前端传递的均为MD
		articleBlogBo.setArticleContext(condition.getArticleContext());
		articleBlogBo.setArticleContextForMd(condition.getArticleContextForMd());
		articleBlogBo.setArticleMore(condition.getArticleMore());
		articleBlogBo.setSelTypeCode(ArticleSelType.getTypeByValue(condition.getSelTypeCode()));

		// 如果'摘要'为空,则取文章标题
		if (StringUtils.isEmpty(condition.getArticleDigest())) {
			// 过滤html
//			final String articleDigest = HtmlRegexpUtil.filterHtmlTag(articleBlogBo.getArticleContextForMd(), true);
			final String articleDigest = articleBlogBo.getArticleContextForMd();
			articleBlogBo.setArticleDigest(StringUtils.trim(articleDigest));
		} else {
			// 过滤html和空格
//			articleBlogBo
//					.setArticleDigest(StringUtils.trim(HtmlRegexpUtil.filterHtmlTag(condition.getArticleDigest())));
			articleBlogBo.setArticleDigest(StringUtils.trim(condition.getArticleDigest()));
		}

		/* 个人分类,多个分类之间用“,”分隔,eg: '1,3,6,8,love' */
		// 原分类信息下的使用次数首先全部减一
		for (final Object owenerTagId : articleBlogBo.getOwenerTagIds()) {
			if (!owenerTagService.plusCorrelaArticle(Long.parseLong(owenerTagId.toString()), -1)) {
				throw new DataVerifyAnomalyException(BaseErrorType.SYSTEM_ERROR);
			}
		}
		// 重新维护次数关系
		final Map<String, OwenerTagBo> owenerTags = operationOwenerTagIdsBySave(uid, condition.getOwenerTag());
		articleBlogBo.setOwenerTagIds(owenerTags.keySet());

		// 全站文章分类（到分类首页）， 如果发布文章时未选择，则使用‘个人分类’的默认全站分类
		final Set<String> categoryTagCodes = Sets.newHashSet();
		if (StringUtil.isEmpty(condition.getCategoryTagCode())) {
			for (final Map.Entry<String, OwenerTagBo> entry : owenerTags.entrySet()) {
				categoryTagCodes.add(entry.getValue().getCategoriesTagCode());
			}
		} else {
			categoryTagCodes.add(condition.getCategoryTagCode());
		}
		articleBlogBo.setCategoryTagCodes(categoryTagCodes);

		// TODO 保存浏览器信息
		System.out.println("浏览器信息: " + agent);

		if (!articleBlogService.update(articleBlogBo)) {
			throw new DataVerifyAnomalyException(BaseErrorType.SYSTEM_ERROR);
		}

		return articleBlogId;
	}

	@Override
	@ProfilerLog
	public List<ArticleTagBlogVo> findPageListForSimpleWithTitle(final IPageable pageable, final String articleTitle) {
		final List<ArticleBlogBo> lists = articleBlogService.findPageList(pageable, articleTitle);

		final List<ArticleTagBlogVo> ls = Lists.newArrayList();
		lists.stream().forEach(blog -> {
			final ArticleTagBlogVo simpleBlog = new ArticleTagBlogVo();
			simpleBlog.setArticleBlogId(blog.getArticleBlogId());
			simpleBlog.setArticleTitle(blog.getArticleTitle());
			simpleBlog.setArticleAlias(blog.getArticleAlias());
			simpleBlog.setToday(DateUtil.format(blog.getCreateTime(), DateFormatType.TIME_LEFT_DIAGONAL));
			simpleBlog.setCreateTime(blog.getCreateTime());
			simpleBlog.setModifyUser(blog.getModifyUser());
			simpleBlog.setUpdateTime(blog.getUpdateTime());

			// 处理全站文章标签列表
			final List<CategoriesTagBo> ct = categoriesTagService.findListByCode(blog.getCategoryTagCodes());
			if (CollectionUtil.isEmpty(ct)) {
				simpleBlog.setCategoryTagsForBlog(Collections.emptySet());
			} else {
				final Set<ArticleBlogForCategoryTagModel> ctb = Sets.newHashSet();
				ct.stream().forEach(c -> {
					final ArticleBlogForCategoryTagModel bo = new ArticleBlogForCategoryTagModel();
					bo.setCategoryTagCode(c.getCategoriesTagCode());
					bo.setCategoryTagName(c.getCategoriesName());
					ctb.add(bo);
				});
				simpleBlog.setCategoryTagsForBlog(ctb);
			}

			// 处理个人分类列表
			// owenerTagService.

			ls.add(simpleBlog);
		});

		return ls;
	}

	/**
	 * 新增和修改时的个人分类部分操作
	 *
	 * @param uid
	 *            UID
	 * @param owenerTagIds
	 *            个人分类,多个分类之间用“,”分隔,包含已存在分类的主键,和新增分类,eg: '1,3,6,8,love'
	 * @return
	 * @throws DataVerifyAnomalyException
	 */
	private Map<String, OwenerTagBo> operationOwenerTagIdsBySave(final String uid, final String owenerTagIds)
			throws DataVerifyAnomalyException {
		final Map<String, OwenerTagBo> owenerTags = Maps.newHashMap();
		for (final String owenerTagId : Splitter.on(Constants.COMMA).split(owenerTagIds)) {
			// 已经存在的,eg: '1,3,6,8'
			if (owenerTagId == null) {
				continue;
			}

			final OwenerTagBo tag = owenerTagService.queryById(Long.parseLong(owenerTagId));
			if (tag == null) {
				throw new DataVerifyAnomalyException(BaseErrorType.RECORD_NOT_EXIT);
			}

			if (!owenerTagService.plusCorrelaArticle(tag.getOwenerTagId(), 1)) {
				throw new DataVerifyAnomalyException(BaseErrorType.SYSTEM_ERROR);
			}

			owenerTags.put(owenerTagId, tag);
		}
		return owenerTags;
	}

}
