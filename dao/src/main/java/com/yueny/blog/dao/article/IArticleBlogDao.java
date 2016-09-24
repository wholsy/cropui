package com.yueny.blog.dao.article;

import java.util.List;

import com.yueny.blog.entry.article.ArticleBlogEntry;
import com.yueny.kapo.api.ISingleTableDao;
import com.yueny.kapo.api.IWholeTableQuery;
import com.yueny.superclub.api.page.IPageable;

/**
 * 文章分类类目持久层操作
 *
 * @author 袁洋 2015年8月24日 下午1:45:35
 *
 */
public interface IArticleBlogDao extends ISingleTableDao<ArticleBlogEntry>, IWholeTableQuery<ArticleBlogEntry> {
	/**
	 * 获取存在于用户个人分类的博文
	 */
	List<ArticleBlogEntry> findByOwenerTagId(Long owenerTagId);

	/**
	 * 文章已读次数加step
	 *
	 * @param articleBlogId
	 *            博文对外ID
	 * @param step
	 *            跨度
	 */
	boolean plusReadTimes(String articleBlogId, int step);

	/**
	 * 根据文章对外ID获取文章信息
	 *
	 * @param articleBlogId
	 *            文章对外ID
	 */
	ArticleBlogEntry queryByBlogId(String articleBlogId);

	/**
	 * 根据此文章的'上一篇文章对外ID'获取此文章信息
	 *
	 * @param articlePreviousBlogId
	 *            上一篇文章对外ID
	 */
	ArticleBlogEntry queryByPreviousBlogId(String articlePreviousBlogId);

	/**
	 * @param selType
	 *            文章标题类型
	 */
	List<ArticleBlogEntry> queryBySelType(String selType);

	/**
	 * 根据最近的一笔博文息
	 */
	ArticleBlogEntry queryLatestBlog();

	/**
	 * 获取指定用户的最近的一笔博文信息
	 *
	 * @param uid
	 *            用户uid
	 */
	ArticleBlogEntry queryLatestBlog(String uid);

	/**
	 * 根据指定用户的最近的一笔博文对外ID
	 *
	 * @param uid
	 *            用户uid
	 * @return 指定用户的最近的一笔博文对外ID
	 */
	String queryLatestBlogId(String uid);

	/**
	 * 分页查询
	 *
	 * @param whereColumns
	 *            查询条件, Map<String, Object> whereColumns
	 * @param pageable
	 *            分页信息
	 */
	List<ArticleBlogEntry> queryList(final IPageable pageable);
}
