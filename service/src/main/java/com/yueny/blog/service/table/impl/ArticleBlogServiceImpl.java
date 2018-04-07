package com.yueny.blog.service.table.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.yueny.blog.bo.article.ArticleBlogBo;
import com.yueny.blog.bo.article.ArticleSimpleBlogBo;
import com.yueny.blog.dao.article.IArticleBlogDao;
import com.yueny.blog.dao.cd.ArticleBlogCondition;
import com.yueny.blog.entry.article.ArticleBlogEntry;
import com.yueny.blog.service.BaseBiz;
import com.yueny.blog.service.comp.cache.CacheActionType;
import com.yueny.blog.service.comp.cache.CacheDataHandler;
import com.yueny.blog.service.comp.cache.core.CacheListService;
import com.yueny.blog.service.comp.cache.core.CacheService;
import com.yueny.blog.service.disruptor.api.SyntonyHandlerFunction;
import com.yueny.blog.service.disruptor.producer.ArticleCacheRemoveEventProducer;
import com.yueny.blog.service.table.IArticleBlogService;
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
	 * @see com.yueny.blog.service.table.IArticleBlogService#countBy(java.lang.
	 * String)
	 */
	@Override
	public Long countBy(final Long owenerTagId) {
		if (owenerTagId == null || owenerTagId.longValue() <= 0) {
			return 0L;
		}

		return blogDao.countBy(owenerTagId.toString());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.yueny.blog.service.article.IArticleBlogService#deleteByBlogId(java.
	 * lang.String)
	 */
	@Override
	@ProfilerLog
	public boolean deleteByBlogId(final String articleBlogId) {
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
	@ProfilerLog
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
		}, 5L, CacheActionType.QUERY_ONE, articleBlogId);
	}

	@Override
	@ProfilerLog
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
		}, CacheActionType.QUERY_ONE, primaryId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.yueny.blog.service.table.IArticleBlogService#findByOwenerTagIds(java.
	 * lang.String)
	 */
	@Override
	@ProfilerLog
	public List<ArticleBlogBo> findByOwenerTagIds(final Long owenerTagId) {
		return cacheListService.cache(new CacheDataHandler<List<ArticleBlogBo>>() {
			@Override
			public List<ArticleBlogBo> caller() {
				String tagId = null;
				if (owenerTagId != null) {
					tagId = owenerTagId.toString();
				}
				final List<ArticleBlogEntry> entrys = blogDao.findByOwenerTagId(tagId);
				if (CollectionUtils.isEmpty(entrys)) {
					return Collections.emptyList();
				}

				return map(entrys, ArticleBlogBo.class);
			}
		}, 5L, "findByOwenerTagId", owenerTagId);
	}

	@Override
	@ProfilerLog
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
	@ProfilerLog
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
	@ProfilerLog
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
	@ProfilerLog
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
	@ProfilerLog
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

	/*
	 * (non-Javadoc)
	 *
	 * @see com.yueny.blog.service.article.IArticleBlogService#findPageList(com.
	 * yueny. superclub.api.page.IPageable, java.lang.String)
	 */
	@Override
	public List<ArticleBlogBo> findPageList(final IPageable pageable, final String articleTitle) {
		final List<ArticleBlogEntry> entrys = blogDao
				.queryByCondition(ArticleBlogCondition.builder().pageable(pageable).articleTitle(articleTitle).build());
		if (CollectionUtils.isEmpty(entrys)) {
			return Collections.emptyList();
		}
		return map(entrys, ArticleBlogBo.class);
	}

	@Override
	@ProfilerLog
	public List<ArticleSimpleBlogBo> findPageListForSimple(final IPageable pageable) {
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
	@ProfilerLog
	public Long queryAllCount(final String articleTitle) {
		if (StringUtil.isEmpty(articleTitle)) {
			return blogDao.queryAllCount();
		}
		return blogDao.queryAllCount(articleTitle);
	}

	@Override
	@ProfilerLog
	public boolean update(final ArticleBlogBo bo) {
		// 获取要通过事件传递的业务数据；
		final SyntonyHandlerFunction syntonyExecute = new SyntonyHandlerFunction() {
			@Override
			public void execute() {
				logger.info("删除日志 {} 缓存信息！", bo.getArticleBlogId());
				cacheService.cacheDelete(ArrayUtil.newArray(CacheActionType.QUERY_ONE, bo.getArticleBlogId()),
						ArrayUtil.newArray("findById", bo.getArticleId()),
						ArrayUtil.newArray("findByPreviousBlogId", bo.getArticleBlogId()),
						ArrayUtil.newArray("findLatestBlog"), ArrayUtil.newArray("findLatestBlog", bo.getUid()));
			}
		};
		new ArticleCacheRemoveEventProducer().publishData(syntonyExecute);

		final ArticleBlogEntry entry = map(bo, ArticleBlogEntry.class);
		return blogDao.update(entry);
	}

}
