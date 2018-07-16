package com.yueny.blog.service.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.yueny.blog.bo.article.ArticleBlogBo;
import com.yueny.blog.bo.article.ArticleSimpleBlogBo;
import com.yueny.blog.dao.article.IArticleBlogDao;
import com.yueny.blog.dao.cd.ArticleBlogCondition;
import com.yueny.blog.entry.article.ArticleBlogEntry;
import com.yueny.blog.service.BaseBiz;
import com.yueny.blog.service.IArticleBlogService;
import com.yueny.rapid.lang.date.DateFormatType;
import com.yueny.rapid.lang.date.DateUtil;
import com.yueny.rapid.lang.util.StringUtil;
import com.yueny.rapid.topic.profiler.ProfilerLog;
import com.yueny.superclub.api.page.IPageable;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月1日 下午5:33:53
 *
 */
// 代表这个Controller下需要缓存的方法对应redis中的缓存名为 content
@CacheConfig(cacheNames = "content")
@Service
public class ArticleBlogServiceImpl extends BaseBiz implements IArticleBlogService {
	@Autowired
	private IArticleBlogDao blogDao;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.yueny.blog.service.IArticleBlogService#countBy(java.lang.
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
	/**
	 * <pre>
	 * &#64;Cacheable(value="cacheName", key"T(String).valueOf(#name).concat('-').concat(#password))
		public ResultDTO method(int name, String password);

		&#64;Cacheable(value="cacheName", key"#user.id)
		public ResultDTO method(User user);
	
		&#64;Cacheable(value="gomeo2oCache", keyGenerator = "keyGenerator")
		public ResultDTO method(User user);
	
		&#64;Cacheable(value="andCache",key="#userId + 'findById'")

		 //将缓存保存进andCache，并当参数userId的长度小于32时才保存进缓存，默认使用参数值及类型作为缓存的key
	    &#64;Cacheable(value="andCache",condition="#userId.length < 32")
	 * </pre>
	 *
	 */
	@Cacheable(value = "content", key = "#articleBlogId + 'findByBlogId'")
	public ArticleBlogBo findByBlogId(final String articleBlogId) {
		// final String redisKey =
		// CacheKeyConstant.assmebledKeys(CacheActionType.QUERY_ONE,
		// articleBlogId);
		//
		// cacheService.get(redisKey);
		// return cacheService.cache(new CacheDataHandler<ArticleBlogBo>() {
		// @Override
		// public ArticleBlogBo caller() {
		// final ArticleBlogEntry entry = blogDao.queryByBlogId(articleBlogId);
		// if (entry == null) {
		// return null;
		// }
		// return map(entry, ArticleBlogBo.class);
		// }
		// }, 5L, CacheActionType.QUERY_ONE, articleBlogId);

		final ArticleBlogEntry entry = blogDao.queryByBlogId(articleBlogId);
		if (entry == null) {
			return null;
		}
		return map(entry, ArticleBlogBo.class);
	}

	@Override
	@ProfilerLog
	@Cacheable(key = "#primaryId + 'findById'")
	public ArticleBlogBo findById(final Long primaryId) {
		// return cacheService.cache(new CacheDataHandler<ArticleBlogBo>() {
		// @Override
		// public ArticleBlogBo caller() {
		// final ArticleBlogEntry entry = blogDao.queryByID(primaryId);
		// if (entry == null) {
		// return null;
		// }
		//
		// return map(entry, ArticleBlogBo.class);
		// }
		// }, CacheActionType.QUERY_ONE, primaryId);

		final ArticleBlogEntry entry = blogDao.queryByID(primaryId);
		if (entry == null) {
			return null;
		}

		return map(entry, ArticleBlogBo.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.yueny.blog.service.IArticleBlogService#findByOwenerTagIds(java.
	 * lang.String)
	 */
	@Override
	@ProfilerLog
	@Cacheable(key = "#owenerTagId + 'findByOwenerTagIds'")
	public List<ArticleBlogBo> findByOwenerTagIds(final Long owenerTagId) {
		// return cacheListService.cache(new
		// CacheDataHandler<List<ArticleBlogBo>>() {
		// @Override
		// public List<ArticleBlogBo> caller() {
		// String tagId = null;
		// if (owenerTagId != null) {
		// tagId = owenerTagId.toString();
		// }
		// final List<ArticleBlogEntry> entrys =
		// blogDao.findByOwenerTagId(tagId);
		// if (CollectionUtils.isEmpty(entrys)) {
		// return Collections.emptyList();
		// }
		//
		// return map(entrys, ArticleBlogBo.class);
		// }
		// }, 5L, "findByOwenerTagId", owenerTagId);

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

	@Override
	@ProfilerLog
	@Cacheable(key = "#articlePreviousBlogId + 'findByPreviousBlogId'")
	public ArticleBlogBo findByPreviousBlogId(final String articlePreviousBlogId) {
		// return cacheService.cache(ArrayUtil.newArray("findByPreviousBlogId",
		// articlePreviousBlogId),
		// new CacheDataHandler<ArticleBlogBo>() {
		// @Override
		// public ArticleBlogBo caller() {
		// final ArticleBlogEntry entry =
		// blogDao.queryByPreviousBlogId(articlePreviousBlogId);
		// if (entry == null) {
		// return null;
		// }
		//
		// return map(entry, ArticleBlogBo.class);
		// }
		// });

		final ArticleBlogEntry entry = blogDao.queryByPreviousBlogId(articlePreviousBlogId);
		if (entry == null) {
			return null;
		}

		return map(entry, ArticleBlogBo.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.yueny.blog.service.article.IArticleBlogService#findLatestBlog()
	 */
	@Override
	@ProfilerLog
	@Cacheable(key = "findLatestBlog")
	public ArticleBlogBo findLatestBlog() {
		// return cacheService.cache("findLatestBlog", new
		// CacheDataHandler<ArticleBlogBo>() {
		// @Override
		// public ArticleBlogBo caller() {
		// final ArticleBlogEntry entry = blogDao.queryLatestBlog();
		// if (entry == null) {
		// return null;
		// }
		//
		// return map(entry, ArticleBlogBo.class);
		// }
		// }, 2L);

		final ArticleBlogEntry entry = blogDao.queryLatestBlog();
		if (entry == null) {
			return null;
		}

		return map(entry, ArticleBlogBo.class);
	}

	@Override
	@ProfilerLog
	@Cacheable(key = "#uid + 'findLatestBlog'")
	public ArticleBlogBo findLatestBlog(final String uid) {
		// return cacheService.cache(new CacheDataHandler<ArticleBlogBo>() {
		// @Override
		// public ArticleBlogBo caller() {
		// final ArticleBlogEntry entry = blogDao.queryLatestBlog(uid);
		// if (entry == null) {
		// return null;
		// }
		//
		// return map(entry, ArticleBlogBo.class);
		// }
		// }, 2L, "findLatestBlog", uid);

		final ArticleBlogEntry entry = blogDao.queryLatestBlog(uid);
		if (entry == null) {
			return null;
		}

		return map(entry, ArticleBlogBo.class);
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
	@Cacheable(key = "T(String).valueOf(##pageable.currentPage).concat('-').concat(#pageable.pageSize)+ 'findPageList'")
	public List<ArticleBlogBo> findPageList(final IPageable pageable) {
		// return cacheListService.cache(new
		// CacheDataHandler<List<ArticleBlogBo>>() {
		// @Override
		// public List<ArticleBlogBo> caller() {
		// final List<ArticleBlogEntry> entrys = blogDao.queryList(pageable);
		// if (CollectionUtils.isEmpty(entrys)) {
		// return Collections.emptyList();
		// }
		//
		// return map(entrys, ArticleBlogBo.class);
		// }
		// }, 5L, "findPageList", pageable.toString());

		final List<ArticleBlogEntry> entrys = blogDao.queryList(pageable);
		if (CollectionUtils.isEmpty(entrys)) {
			return Collections.emptyList();
		}

		return map(entrys, ArticleBlogBo.class);
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
	@CacheEvict(allEntries = true)
	public boolean update(final ArticleBlogBo bo) {
		// // 获取要通过事件传递的业务数据；
		// final SyntonyHandlerFunction syntonyExecute = new
		// SyntonyHandlerFunction() {
		// @Override
		// public void execute() {
		// logger.info("删除日志 {} 缓存信息！", bo.getArticleBlogId());
		// cacheService.cacheDelete(ArrayUtil.newArray(CacheActionType.QUERY_ONE,
		// bo.getArticleBlogId()),
		// ArrayUtil.newArray("findById", bo.getArticleId()),
		// ArrayUtil.newArray("findByPreviousBlogId", bo.getArticleBlogId()),
		// ArrayUtil.newArray("findLatestBlog"),
		// ArrayUtil.newArray("findLatestBlog", bo.getUid()));
		// }
		// };
		// new ArticleCacheRemoveEventProducer().publishData(syntonyExecute);

		final ArticleBlogEntry entry = map(bo, ArticleBlogEntry.class);
		return blogDao.update(entry);
	}

}
