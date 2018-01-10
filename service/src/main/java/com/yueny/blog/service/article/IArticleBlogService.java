package com.yueny.blog.service.article;

import java.util.List;

import com.yueny.blog.bo.article.ArticleBlogBo;
import com.yueny.blog.bo.article.ArticleSimpleBlogBo;
import com.yueny.blog.vo.article.admin.tags.ArticleTagBlogVo;
import com.yueny.superclub.api.page.IPageable;

/**
 * 博客
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月1日 下午5:33:12
 *
 */
public interface IArticleBlogService {
	/**
	 * 根据文章对外ID删除博文<br>
	 */
	boolean deleteByBlogId(String articleBlogId);

	/**
	 * 根据文章对外ID获取博文信息<br>
	 * 缓存5秒
	 *
	 * @param articleBlogId
	 *            文章对外ID
	 * @return 博文信息
	 */
	ArticleBlogBo findByBlogId(String articleBlogId);

	/**
	 * 根据博文主键获取博文信息
	 *
	 * @param primaryArticleBlogId
	 *            博文主键
	 */
	ArticleBlogBo findById(Long primaryId);

	/**
	 * 获取存在于用户个人分类的博文
	 */
	List<ArticleBlogBo> findByOwenerTagId(Long owenerTagId);

	/**
	 * 根据此文章的'上一篇文章对外ID'获取此文章信息
	 *
	 * @param articlePreviousBlogId
	 *            上一篇文章对外ID
	 */
	ArticleBlogBo findByPreviousBlogId(String articlePreviousBlogId);

	/**
	 * 获取最近的一笔全博文信息
	 */
	ArticleBlogBo findLatestBlog();

	/**
	 * 获取指定用户的最近的一笔博文信息
	 *
	 * @param uid
	 *            用户uid
	 */
	ArticleBlogBo findLatestBlog(String uid);

	/**
	 * 根据指定用户的最近的一笔博文对外ID
	 *
	 * @param uid
	 *            用户uid
	 * @return 指定用户的最近的一笔博文对外ID
	 */
	String findLatestBlogId(String uid);

	/**
	 * 博文分页查询
	 *
	 * @param pageable
	 *            分页项
	 */
	List<ArticleBlogBo> findPageList(IPageable pageable);

	/**
	 * 简要博文分页查询
	 *
	 * @param pageable
	 *            分页项
	 */
	List<ArticleSimpleBlogBo> findPageListForSimple(IPageable pageable);

	/**
	 * 根据title进行的简要博文分页模糊查询
	 *
	 * @param pageable
	 *            分页项
	 * @param articleTitle
	 *            文章标题
	 */
	List<ArticleTagBlogVo> findPageListForSimpleWithTitle(IPageable pageable, String articleTitle);

	/**
	 * 新增
	 *
	 * @return 新增主键
	 */
	Long insert(final ArticleBlogBo bo);

	/**
	 * 文章已读次数加step
	 *
	 * @param articleBlogId
	 *            博文对外ID
	 */
	boolean plusReadTimes(String articleBlogId);

	/**
	 * 查询文章数据数量
	 *
	 * @param articleTitle
	 *            文章标题关键字
	 * @return 总数
	 */
	Long queryAllCount(String articleTitle);

	/**
	 * 根据主键修改
	 *
	 * @return 修改结果
	 */
	boolean update(final ArticleBlogBo bo);

}
