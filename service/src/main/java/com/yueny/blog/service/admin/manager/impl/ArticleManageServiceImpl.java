package com.yueny.blog.service.admin.manager.impl;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.yueny.blog.bo.article.ArticleBlogBo;
import com.yueny.blog.bo.enums.ArticleSelType;
import com.yueny.blog.bo.enums.BlogResultCodeType;
import com.yueny.blog.bo.model.condition.ArticlePublishedCondition;
import com.yueny.blog.bo.tag.OwenerTagBo;
import com.yueny.blog.service.BaseBiz;
import com.yueny.blog.service.admin.manager.IArticleManageService;
import com.yueny.blog.service.article.IArticleBlogService;
import com.yueny.blog.service.table.IOwenerTagService;
import com.yueny.blog.service.uid.ArticleBlogIdGenerate;
import com.yueny.rapid.lang.agent.UserAgentResource;
import com.yueny.rapid.lang.enums.BaseErrorType;
import com.yueny.rapid.lang.exception.DataVerifyAnomalyException;
import com.yueny.rapid.lang.util.HtmlRegexpUtil;
import com.yueny.rapid.lang.util.NumberUtil;
import com.yueny.rapid.lang.util.StringUtil;
import com.yueny.rapid.topic.profiler.ProfilerLog;
import com.yueny.superclub.api.annnotation.et.UnUsed;
import com.yueny.superclub.api.constant.Constants;

import lombok.SneakyThrows;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月1日 下午5:33:53
 *
 */
@Service
public class ArticleManageServiceImpl extends BaseBiz implements IArticleManageService {
	@Autowired
	private IArticleBlogService articleBlogService;
	@Autowired
	private ArticleBlogIdGenerate blogIdGenerate;
	@Autowired
	private IOwenerTagService owenerTagService;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.yueny.blog.service.manage.IArticleManageService#addBlog(java.lang.
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
		// TODO 后续协商为前端传递的均为MD
		articleBlogBo.setArticleContext(condition.getArticleContext());
		articleBlogBo.setArticleContextForMd(condition.getArticleContextForMd());
		articleBlogBo.setArticleMore(condition.getArticleMore());
		articleBlogBo.setSelTypeCode(ArticleSelType.getTypeByValue(condition.getSelTypeCode()));
		articleBlogBo.setReadTimes(0);

		// 如果'摘要'为空,则取文章标题
		if (StringUtils.isEmpty(condition.getArticleDigest())) {
			// 过滤html
			final String articleDigest = HtmlRegexpUtil.filterHtmlTag(articleBlogBo.getArticleTitle(), true);
			articleBlogBo.setArticleDigest(StringUtils.trim(articleDigest));
		} else {
			// 过滤html和空格
			articleBlogBo
					.setArticleDigest(StringUtils.trim(HtmlRegexpUtil.filterHtmlTag(condition.getArticleDigest())));
		}

		// 文章标签,最多添加5个标签，多个标签之间用“,”分隔,eg: Java,自由分类
		final Set<Long> articleTagIds = operationArticleTagIdsBySave(uid, condition.getArticleTag());
		articleBlogBo.setArticleTagIds(articleTagIds);

		// 个人分类,多个分类之间用“,”分隔,eg: '1,3,6,8,love'
		final Map<Long, OwenerTagBo> owenerTags = operationOwenerTagIdsBySave(uid, condition.getOwenerTag());
		articleBlogBo.setOwenerTagIds(owenerTags.keySet());

		// 全站文章分类（到分类首页）， 如果发布文章时未选择，则使用‘个人分类’的默认全站分类
		final Set<String> categoryTagCodes = Sets.newHashSet();
		if (StringUtil.isEmpty(condition.getCategoryTagCode())) {
			for (final Map.Entry<Long, OwenerTagBo> entry : owenerTags.entrySet()) {
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
	 * @see com.yueny.blog.service.manage.IArticleManageService#delArticleBlog(java.
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
	 * @see com.yueny.blog.service.manage.IArticleManageService#editBlog(java.lang.
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
			final String articleDigest = HtmlRegexpUtil.filterHtmlTag(articleBlogBo.getArticleTitle(), true);
			articleBlogBo.setArticleDigest(StringUtils.trim(articleDigest));
		} else {
			// 过滤html和空格
			articleBlogBo
					.setArticleDigest(StringUtils.trim(HtmlRegexpUtil.filterHtmlTag(condition.getArticleDigest())));
		}

		// 文章标签,最多添加5个标签，多个标签之间用“,”分隔,eg: Java,自由分类
		final Set<Long> articleTagIds = operationArticleTagIdsBySave(uid, condition.getArticleTag());
		articleBlogBo.setArticleTagIds(articleTagIds);

		/* 个人分类,多个分类之间用“,”分隔,eg: '1,3,6,8,love' */
		// 原分类信息下的使用次数首先全部减一
		for (final Object owenerTagId : articleBlogBo.getOwenerTagIds()) {
			if (!owenerTagService.plusCorrelaArticle(Long.parseLong(owenerTagId.toString()), -1)) {
				throw new DataVerifyAnomalyException(BaseErrorType.SYSTEM_ERROR);
			}
		}
		// 重新维护次数关系
		final Map<Long, OwenerTagBo> owenerTags = operationOwenerTagIdsBySave(uid, condition.getOwenerTag());
		articleBlogBo.setOwenerTagIds(owenerTags.keySet());

		// 全站文章分类（到分类首页）， 如果发布文章时未选择，则使用‘个人分类’的默认全站分类
		final Set<String> categoryTagCodes = Sets.newHashSet();
		if (StringUtil.isEmpty(condition.getCategoryTagCode())) {
			for (final Map.Entry<Long, OwenerTagBo> entry : owenerTags.entrySet()) {
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

	/**
	 * 新增和修改时的文章标签部分操作
	 *
	 * @param uid
	 *            UID
	 * @param articleTagData
	 *            文章标签,最多添加5个标签，多个标签之间用“,”分隔,eg: Java,31231
	 * @throws DataVerifyAnomalyException
	 */
	@UnUsed
	private Set<Long> operationArticleTagIdsBySave(final String uid, final String articleTagData)
			throws DataVerifyAnomalyException {
		final Set<Long> articleTagIds = Sets.newHashSet();
		for (final String articleTag : Splitter.on(Constants.COMMA).split(articleTagData)) {
			// TODO 根据文章标签名称获取文章标签信息
			final String articleTagName = articleTag;
			articleTagIds.add(99L);
		}
		return articleTagIds;
	}

	/**
	 * 新增和修改时的个人分类部分操作
	 *
	 * @param uid
	 *            UID
	 * @param owenerTagData
	 *            个人分类,多个分类之间用“,”分隔,包含已存在分类的主键,和新增分类,eg: '1,3,6,8,love'
	 * @return
	 * @throws DataVerifyAnomalyException
	 */
	private Map<Long, OwenerTagBo> operationOwenerTagIdsBySave(final String uid, final String owenerTagData)
			throws DataVerifyAnomalyException {
		final Map<Long, OwenerTagBo> owenerTags = Maps.newHashMap();
		for (final String owenerTag : Splitter.on(Constants.COMMA).split(owenerTagData)) {
			final Long owenerTagId = NumberUtil.toLong(owenerTag, null);
			// 已经存在的,eg: '1,3,6,8'
			if (owenerTagId == null) {
				continue;
			}

			final OwenerTagBo tag = owenerTagService.queryById(owenerTagId);
			if (tag == null) {
				throw new DataVerifyAnomalyException(BaseErrorType.RECORD_NOT_EXIT);
			}

			if (!owenerTagService.plusCorrelaArticle(owenerTagId, 1)) {
				throw new DataVerifyAnomalyException(BaseErrorType.SYSTEM_ERROR);
			}

			owenerTags.put(owenerTagId, tag);
		}
		return owenerTags;
	}

}