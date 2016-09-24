package com.yueny.blog.dao.article;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.yueny.blog.entry.article.ArticleBlogEntry;
import com.yueny.kapo.api.biz.ISqlMapper;

/**
 * 博客持久层操作
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月5日 上午10:01:48
 *
 */
@Repository
public interface IArticleBlogMapper extends ISqlMapper {
	// @Delete("delete from where mobile = #{0} and date is null")
	// int deleteRecord(String mobile);

	/**
	 * 文章已读次数加step
	 *
	 * @param articleBlogId
	 *            博文对外ID
	 * @param step
	 *            增加数目,eg: 1,-1
	 */
	@Update("UPDATE ARTICLE_BLOG SET READ_TIMES=READ_TIMES+#{step} WHERE ARTICLE_BLOG_ID=#{articleBlogId}")
	int plusReadTimesByArticleBlogId(@Param("articleBlogId") String articleBlogId, @Param("step") int step);

	/**
	 * 获取最近的一笔全博文信息
	 */
	@Select("SELECT * FROM ARTICLE_BLOG ORDER BY CREATE_TIME DESC LIMIT 1")
	@Results({ @Result(column = "ARTICLE_BLOG_ID", property = "articleBlogId"),
			@Result(column = "ARTICLE_ID", property = "articleId"),
			@Result(column = "ARTICLE_PREVIOUS_BLOG_ID", property = "articlePreviousBlogId"),
			@Result(column = "ARTICLE_TITLE", property = "articleTitle"),
			@Result(column = "SEL_TYPE_CODE", property = "selTypeCode"),
			@Result(column = "ARTICLE_CONTEXT", property = "articleContext"),
			@Result(column = "ARTICLE_DIGEST", property = "articleDigest"),
			@Result(column = "ARTICLE_TAG_IDS", property = "articleTagIds"),
			@Result(column = "OWENER_TAG_IDS", property = "owenerTagIds"),
			@Result(column = "CATEGORY_TAG_CODES", property = "categoryTagCodes"),
			@Result(column = "ARTICLE_MORE", property = "articleMore"), @Result(column = "UID", property = "uid"),
			@Result(column = "ARTICLE_ALIAS", property = "articleAlias"),
			@Result(column = "MODIFY_USER", property = "modifyUser"), @Result(column = "VERSION", property = "version"),
			@Result(column = "UPDATE_TIME", property = "updateTime"),
			@Result(column = "CREATE_TIME", property = "createTime") })
	ArticleBlogEntry queryLatestBlog();

	/**
	 * 获取指定用户的最近的一笔博文信息
	 *
	 * @param uid
	 *            用户uid
	 */
	@Select("SELECT * FROM ARTICLE_BLOG WHERE UID=#{uid} ORDER BY CREATE_TIME DESC LIMIT 1")
	@ResultMap("resultMap")
	ArticleBlogEntry queryLatestBlogByUid(@Param("uid") String uid);

	/**
	 * 根据指定用户的最近的一笔博文对外ID
	 *
	 * @param uid
	 *            用户uid
	 * @return 指定用户的最近的一笔博文对外ID
	 */
	@Select("SELECT ARTICLE_BLOG_ID FROM ARTICLE_BLOG WHERE UID=#{uid} ORDER BY CREATE_TIME DESC LIMIT 1")
	String queryLatestBlogId(@Param("uid") String uid);

}
