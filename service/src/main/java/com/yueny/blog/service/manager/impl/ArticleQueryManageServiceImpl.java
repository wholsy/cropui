package com.yueny.blog.service.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yueny.blog.bo.article.ArticleBlogBo;
import com.yueny.blog.bo.article.ArticleBlogViewBo;
import com.yueny.blog.bo.article.ArticleSimpleBlogBo;
import com.yueny.blog.bo.model.document.ArchiveData;
import com.yueny.blog.bo.tag.CategoriesTagBo;
import com.yueny.blog.bo.tag.OwenerTagBo;
import com.yueny.blog.common.cache.ICacheUsableful;
import com.yueny.blog.service.BaseBiz;
import com.yueny.blog.service.IArticleBlogService;
import com.yueny.blog.service.ICategoriesTagService;
import com.yueny.blog.service.IOwenerTagService;
import com.yueny.blog.service.disruptor.api.SyntonyHandlerFunction;
import com.yueny.blog.service.listener.DefaultMsgPusher;
import com.yueny.blog.service.listener.MsgQuene;
import com.yueny.blog.service.manager.IArticleQueryManageService;
import com.yueny.rapid.lang.date.DateFormatType;
import com.yueny.rapid.lang.date.DateTimeUtil;
import com.yueny.rapid.lang.date.DateUtil;
import com.yueny.rapid.lang.enums.BaseErrorType;
import com.yueny.rapid.lang.exception.DataVerifyAnomalyException;
import com.yueny.rapid.topic.profiler.ProfilerLog;
import com.yueny.superclub.api.page.core.PageCond;

import lombok.SneakyThrows;

/**
 * 文章查询服务
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月10日 下午12:02:26
 *
 */
@Service
@CacheConfig(cacheNames = "content")
public class ArticleQueryManageServiceImpl extends BaseBiz implements IArticleQueryManageService, ICacheUsableful {

	@Autowired
	private IArticleBlogService articleBlogService;
	@Autowired
	private ICategoriesTagService categoriesTagService;
	// @Autowired
	// private MultiThreadSupport multiThreadSupport;
	@Autowired
	private DefaultMsgPusher defaultMsgPusher;
	@Autowired
	private IOwenerTagService owenerTagService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.yueny.blog.service.manage.IArticleQueryService#getArchiveList(com.
	 * yueny. superclub.api.page.core.PageCond)
	 */
	@Override
	@ProfilerLog
	@Cacheable(key = "T(String).valueOf(#pageable.currentPage).concat('-').concat(#pageable.pageSize)+ 'getArchiveList'")
	public List<ArchiveData> getArchiveList(final PageCond pageable) throws DataVerifyAnomalyException {
		final List<ArticleBlogBo> lists = articleBlogService.findPageList(pageable);

		final Map<Integer, List<ArticleSimpleBlogBo>> archiveMaps = Maps.newTreeMap();
		for (final ArticleBlogBo articleBlogBo : lists) {
			final ArticleSimpleBlogBo simpleBlog = new ArticleSimpleBlogBo();
			simpleBlog.setArticleBlogId(articleBlogBo.getArticleBlogId());
			simpleBlog.setArticleTitle(articleBlogBo.getArticleTitle());
			simpleBlog.setArticleAlias(articleBlogBo.getArticleAlias());
			simpleBlog.setToday(DateUtil.format(articleBlogBo.getCreateTime(), DateFormatType.TIME_LEFT_DIAGONAL));
			simpleBlog.setCreateTime(articleBlogBo.getCreateTime());
			simpleBlog.setModifyUser(articleBlogBo.getModifyUser());
			simpleBlog.setUpdateTime(articleBlogBo.getUpdateTime());

			final int year = DateTimeUtil.thisYear(articleBlogBo.getCreateTime());
			if (archiveMaps.containsKey(year)) {
				archiveMaps.get(year).add(simpleBlog);
			} else {
				archiveMaps.put(year, Lists.newArrayList(simpleBlog));
			}
		}

		final List<ArchiveData> archiveDataList = Lists.newArrayList();
		for (final Map.Entry<Integer, List<ArticleSimpleBlogBo>> entry : archiveMaps.entrySet()) {
			archiveDataList.add(new ArchiveData(entry.getValue(), entry.getKey()));
		}
		return archiveDataList;
	}

	@Override
	@ProfilerLog
	@SneakyThrows(value = { DataVerifyAnomalyException.class })
	public ArticleBlogViewBo getArticleInfo(final String articleBlogId) {
		// 增加一次阅读量,不关注成败
		final SyntonyHandlerFunction<?> functionInstance = new SyntonyHandlerFunction<Boolean>() {
			@Override
			public Boolean execute() {
				final boolean rs = false;
				try {
					logger.info("阅读量:{} 增加增加操作", articleBlogId);
					articleBlogService.plusReadTimes(articleBlogId);
				} catch (final Exception e) {
					logger.error("阅读量增加增加失败!", e);
				}

				return rs;
			}
		};
		defaultMsgPusher.push(MsgQuene.builder().syntonyExecuteInstance(functionInstance).build());

		// 获取文章的基本信息
		final ArticleBlogBo articleBlog = articleBlogService.findByBlogId(articleBlogId);
		if (articleBlog == null) {
			logger.error("文章：{} 不存在！", articleBlogId);
			throw new DataVerifyAnomalyException(BaseErrorType.RECORD_NOT_EXIT);
		}

		final ArticleBlogViewBo view = new ArticleBlogViewBo();
		view.setArticleBlog(articleBlog);

		// 获取文章的个人分类信息
		final List<OwenerTagBo> owenerTags = owenerTagService.queryByCode(articleBlog.getOwenerTagIds());
		view.setOwenerTags(owenerTags);

		// 该博文所归属的全站文章分类
		final List<CategoriesTagBo> categoriesTagList = categoriesTagService
				.findListByCode(articleBlog.getCategoryTagCodes());
		view.setCategoriesTagList(categoriesTagList);

		// 获取下一篇博文信息
		final ArticleBlogBo nextBlog = articleBlogService.findByPreviousBlogId(articleBlogId);
		if (nextBlog != null) {
			final ArticleSimpleBlogBo nextSimpleBlog = new ArticleSimpleBlogBo();
			nextSimpleBlog.setArticleBlogId(nextBlog.getArticleBlogId());
			nextSimpleBlog.setArticleTitle(nextBlog.getArticleTitle());
			nextSimpleBlog.setArticleAlias(nextBlog.getArticleAlias());

			view.setNextSimpleBlog(nextSimpleBlog);
		}

		// 获取上一篇文章信息
		final ArticleBlogBo previousBlog = articleBlogService.findByBlogId(articleBlog.getArticlePreviousBlogId());
		if (previousBlog != null) {
			final ArticleSimpleBlogBo previousSimpleBlog = new ArticleSimpleBlogBo();
			previousSimpleBlog.setArticleBlogId(previousBlog.getArticleBlogId());
			previousSimpleBlog.setArticleTitle(previousBlog.getArticleTitle());
			previousSimpleBlog.setArticleAlias(previousBlog.getArticleAlias());

			view.setPreviousSimpleBlog(previousSimpleBlog);
		}

		// 文章标签ID
		// private Set<Long> articleTagIds;

		return view;
	}

}
