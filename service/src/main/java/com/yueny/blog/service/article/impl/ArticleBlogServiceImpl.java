package com.yueny.blog.service.article.impl;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yueny.blog.bo.article.ArticleBlogBo;
import com.yueny.blog.dao.article.IArticleBlogDao;
import com.yueny.blog.entry.article.ArticleBlogEntry;
import com.yueny.blog.service.CacheBaseBiz;
import com.yueny.blog.service.article.IArticleBlogService;
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
public class ArticleBlogServiceImpl extends CacheBaseBiz<ArticleBlogBo> implements IArticleBlogService {
	@Autowired
	private IArticleBlogDao blogDao;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.yueny.blog.service.article.IArticleBlogService#findByBlogId(java.lang
	 * .String)
	 */
	@Override
	public ArticleBlogBo findByBlogId(final String articleBlogId) {
		return this.cache(ArrayUtil.newArray("findByBlogId", articleBlogId), new ICacheExecutor<ArticleBlogBo>() {
			@Override
			public ArticleBlogBo execute() {
				final ArticleBlogEntry entry = blogDao.queryByBlogId(articleBlogId);
				if (entry == null) {
					return null;
				}
				return map(entry, ArticleBlogBo.class);
			}
		}, 5, TimeUnit.SECONDS);
	}

	@Override
	public ArticleBlogBo findById(final Long primaryId) {
		return this.cache(ArrayUtil.newArray("findById", primaryId), new ICacheExecutor<ArticleBlogBo>() {
			@Override
			public ArticleBlogBo execute() {
				final ArticleBlogEntry entry = blogDao.queryByID(primaryId);
				if (entry == null) {
					return null;
				}

				return map(entry, ArticleBlogBo.class);
			}
		});
	}

	@Override
	public List<ArticleBlogBo> findByOwenerTagId(final Long owenerTagId) {
		return this.cacheList(ArrayUtil.newArray("findByOwenerTagId", owenerTagId),
				new ICacheExecutor<List<ArticleBlogBo>>() {
					@Override
					public List<ArticleBlogBo> execute() {
						final List<ArticleBlogEntry> entrys = blogDao.findByOwenerTagId(owenerTagId);
						if (CollectionUtils.isEmpty(entrys)) {
							return Collections.emptyList();
						}

						return map(entrys, ArticleBlogBo.class);
					}
				}, 5, TimeUnit.SECONDS);
	}

	@Override
	public ArticleBlogBo findByPreviousBlogId(final String articlePreviousBlogId) {
		return this.cache(ArrayUtil.newArray("findByPreviousBlogId", articlePreviousBlogId),
				new ICacheExecutor<ArticleBlogBo>() {
					@Override
					public ArticleBlogBo execute() {
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
		return this.cache(ArrayUtil.newArray("findLatestBlog"), new ICacheExecutor<ArticleBlogBo>() {
			@Override
			public ArticleBlogBo execute() {
				final ArticleBlogEntry entry = blogDao.queryLatestBlog();
				if (entry == null) {
					return null;
				}

				return map(entry, ArticleBlogBo.class);
			}
		}, 2, TimeUnit.SECONDS);
	}

	@Override
	public ArticleBlogBo findLatestBlog(final String uid) {
		return this.cache(ArrayUtil.newArray("findLatestBlog", uid), new ICacheExecutor<ArticleBlogBo>() {
			@Override
			public ArticleBlogBo execute() {
				final ArticleBlogEntry entry = blogDao.queryLatestBlog(uid);
				if (entry == null) {
					return null;
				}

				return map(entry, ArticleBlogBo.class);
			}
		}, 2, TimeUnit.SECONDS);
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

	@Override
	public List<ArticleBlogBo> findPageList(final IPageable pageable) {
		return this.cacheList(ArrayUtil.newArray("findPageList", pageable.toString()),
				new ICacheExecutor<List<ArticleBlogBo>>() {
					@Override
					public List<ArticleBlogBo> execute() {
						final List<ArticleBlogEntry> entrys = blogDao.queryList(pageable);
						if (CollectionUtils.isEmpty(entrys)) {
							return Collections.emptyList();
						}

						return map(entrys, ArticleBlogBo.class);
					}
				}, 5, TimeUnit.SECONDS);
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

	@Override
	public boolean update(final ArticleBlogBo bo) {
		cacheDelete(ArrayUtil.newArray("findByBlogId", bo.getArticleBlogId()),
				ArrayUtil.newArray("findById", bo.getArticleId()),
				ArrayUtil.newArray("findByPreviousBlogId", bo.getArticleBlogId()), ArrayUtil.newArray("findLatestBlog"),
				ArrayUtil.newArray("findLatestBlog", bo.getUid()));

		final ArticleBlogEntry entry = map(bo, ArticleBlogEntry.class);
		return blogDao.update(entry);
	}

}
