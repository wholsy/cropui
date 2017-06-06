package com.yueny.blog.service.article.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.yueny.blog.bo.article.ArticleBlogBo;
import com.yueny.blog.bo.article.ArticleSimpleBlogBo;
import com.yueny.blog.dao.article.IArticleBlogDao;
import com.yueny.blog.dao.cd.ArticleBlogCd;
import com.yueny.blog.entry.article.ArticleBlogEntry;
import com.yueny.blog.service.BaseBiz;
import com.yueny.blog.service.article.IArticleBlogService;
import com.yueny.blog.service.env.CacheDataHandler;
import com.yueny.blog.service.env.CacheListService;
import com.yueny.blog.service.env.CacheService;
import com.yueny.rapid.lang.date.DateFormatType;
import com.yueny.rapid.lang.date.DateUtil;
import com.yueny.rapid.lang.util.StringUtil;
import com.yueny.rapid.lang.util.collect.ArrayUtil;
import com.yueny.rapid.topic.profiler.ProfilerLog;
import com.yueny.superclub.api.page.IPageable;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月1日 下午5:33:53
 *
 */
@Service
public class ArticleBlogServiceImpl extends BaseBiz implements IArticleBlogService {
	@Autowired
	private IArticleBlogDao blogDao;
	@Autowired
	private CacheListService<ArticleBlogBo> cacheListService;
	@Autowired
	private CacheService<ArticleBlogBo> cacheService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yueny.blog.service.article.IArticleBlogService#deleteByBlogId(java.
	 * lang.String)
	 */
	@Override
	public boolean deleteByBlogId(String articleBlogId) {
		return blogDao.deleteByBlogId(articleBlogId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.yueny.blog.service.article.IArticleBlogService#findByBlogId(java.lang
	 * .String)
	 */
	@Override
	public ArticleBlogBo findByBlogId(final String articleBlogId) {
		return cacheService.cache(new CacheDataHandler<ArticleBlogBo>() {
			@Override
			public ArticleBlogBo caller() {
				final ArticleBlogEntry entry = blogDao.queryByBlogId(articleBlogId);
				if (entry == null) {
					return null;
				}
				return map(entry, ArticleBlogBo.class);
			}
		}, 5L, "findByBlogId", articleBlogId);
	}

	@Override
	public ArticleBlogBo findById(final Long primaryId) {
		return cacheService.cache(new CacheDataHandler<ArticleBlogBo>() {
			@Override
			public ArticleBlogBo caller() {
				final ArticleBlogEntry entry = blogDao.queryByID(primaryId);
				if (entry == null) {
					return null;
				}

				return map(entry, ArticleBlogBo.class);
			}
		}, "findById", primaryId);
	}

	@Override
	public List<ArticleBlogBo> findByOwenerTagId(final Long owenerTagId) {
		return cacheListService.cache(new CacheDataHandler<List<ArticleBlogBo>>() {
			@Override
			public List<ArticleBlogBo> caller() {
				final List<ArticleBlogEntry> entrys = blogDao.findByOwenerTagId(owenerTagId);
				if (CollectionUtils.isEmpty(entrys)) {
					return Collections.emptyList();
				}

				return map(entrys, ArticleBlogBo.class);
			}
		}, 5L, "findByOwenerTagId", owenerTagId);
	}

	@Override
	public ArticleBlogBo findByPreviousBlogId(final String articlePreviousBlogId) {
		return cacheService.cache(ArrayUtil.newArray("findByPreviousBlogId", articlePreviousBlogId),
				new CacheDataHandler<ArticleBlogBo>() {
					@Override
					public ArticleBlogBo caller() {
						final ArticleBlogEntry entry = blogDao.queryByPreviousBlogId(articlePreviousBlogId);
						if (entry == null) {
							return null;
						}

						return map(entry, ArticleBlogBo.class);
					}
				});
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.yueny.blog.service.article.IArticleBlogService#findLatestBlog()
	 */
	@Override
	public ArticleBlogBo findLatestBlog() {
		return cacheService.cache("findLatestBlog", new CacheDataHandler<ArticleBlogBo>() {
			@Override
			public ArticleBlogBo caller() {
				final ArticleBlogEntry entry = blogDao.queryLatestBlog();
				if (entry == null) {
					return null;
				}

				return map(entry, ArticleBlogBo.class);
			}
		}, 2L);
	}

	@Override
	public ArticleBlogBo findLatestBlog(final String uid) {
		return cacheService.cache(new CacheDataHandler<ArticleBlogBo>() {
			@Override
			public ArticleBlogBo caller() {
				final ArticleBlogEntry entry = blogDao.queryLatestBlog(uid);
				if (entry == null) {
					return null;
				}

				return map(entry, ArticleBlogBo.class);
			}
		}, 2L, "findLatestBlog", uid);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.yueny.blog.service.article.IArticleBlogService#findLatestBlogId(java.
	 * lang.String)
	 */
	@Override
	public String findLatestBlogId(final String uid) {
		return blogDao.queryLatestBlogId(uid);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.yueny.blog.service.article.IArticleBlogService#findPageList(com.yueny
	 * .superclub.api.page.IPageable)
	 */
	@Override
	public List<ArticleBlogBo> findPageList(final IPageable pageable) {
		return cacheListService.cache(new CacheDataHandler<List<ArticleBlogBo>>() {
			@Override
			public List<ArticleBlogBo> caller() {
				final List<ArticleBlogEntry> entrys = blogDao.queryList(pageable);
				if (CollectionUtils.isEmpty(entrys)) {
					return Collections.emptyList();
				}

				return map(entrys, ArticleBlogBo.class);
			}
		}, 5L, "findPageList", pageable.toString());
	}

	@Override
	public List<ArticleSimpleBlogBo> findPageListForSimple(IPageable pageable) {
		final List<ArticleBlogBo> lists = findPageList(pageable);

		final List<ArticleSimpleBlogBo> ls = Lists.newArrayList();
		lists.stream().forEach(blog -> {
			final ArticleSimpleBlogBo simpleBlog = new ArticleSimpleBlogBo();
			simpleBlog.setArticleBlogId(blog.getArticleBlogId());
			simpleBlog.setArticleTitle(blog.getArticleTitle());
			simpleBlog.setArticleAlias(blog.getArticleAlias());
			simpleBlog.setToday(DateUtil.format(blog.getCreateTime(), DateFormatType.TIME_LEFT_DIAGONAL));
			simpleBlog.setCreateTime(blog.getCreateTime());
			simpleBlog.setModifyUser(blog.getModifyUser());
			simpleBlog.setUpdateTime(blog.getUpdateTime());

			ls.add(simpleBlog);
		});

		return ls;
	}

	@Override
	public List<ArticleSimpleBlogBo> findPageListForSimpleWithTitle(IPageable pageable, String articleTitle) {
		final List<ArticleBlogEntry> entrys = blogDao
				.queryByCd(ArticleBlogCd.builder().pageable(pageable).articleTitle(articleTitle).build());
		if (CollectionUtils.isEmpty(entrys)) {
			return Collections.emptyList();
		}
		final List<ArticleBlogBo> lists = map(entrys, ArticleBlogBo.class);

		final List<ArticleSimpleBlogBo> ls = Lists.newArrayList();
		lists.stream().forEach(blog -> {
			final ArticleSimpleBlogBo simpleBlog = new ArticleSimpleBlogBo();
			simpleBlog.setArticleBlogId(blog.getArticleBlogId());
			simpleBlog.setArticleTitle(blog.getArticleTitle());
			simpleBlog.setArticleAlias(blog.getArticleAlias());
			simpleBlog.setToday(DateUtil.format(blog.getCreateTime(), DateFormatType.TIME_LEFT_DIAGONAL));
			simpleBlog.setCreateTime(blog.getCreateTime());
			simpleBlog.setModifyUser(blog.getModifyUser());
			simpleBlog.setUpdateTime(blog.getUpdateTime());

			ls.add(simpleBlog);
		});

		return ls;
	}

	@Override
	@ProfilerLog
	public Long insert(final ArticleBlogBo bo) {
		final ArticleBlogEntry entry = map(bo, ArticleBlogEntry.class);
		return blogDao.insert(entry);
	}

	@Override
	public boolean plusReadTimes(final String articleBlogId) {
		return blogDao.plusReadTimes(articleBlogId, 1);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.yueny.blog.service.article.IArticleBlogService#queryAllCount()
	 */
	@Override
	public Long queryAllCount(String articleTitle) {
		if (StringUtil.isEmpty(articleTitle)) {
			return blogDao.queryAllCount();
		}
		return blogDao.queryAllCount(articleTitle);
	}

	@Override
	public boolean update(final ArticleBlogBo bo) {
		cacheService.cacheDelete(ArrayUtil.newArray("findByBlogId", bo.getArticleBlogId()),
				ArrayUtil.newArray("findById", bo.getArticleId()),
				ArrayUtil.newArray("findByPreviousBlogId", bo.getArticleBlogId()), ArrayUtil.newArray("findLatestBlog"),
				ArrayUtil.newArray("findLatestBlog", bo.getUid()));

		final ArticleBlogEntry entry = map(bo, ArticleBlogEntry.class);
		return blogDao.update(entry);
	}

}
