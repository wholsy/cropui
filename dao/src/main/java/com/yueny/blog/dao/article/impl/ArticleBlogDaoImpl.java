package com.yueny.blog.dao.article.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yueny.blog.dao.article.IArticleBlogDao;
import com.yueny.blog.dao.article.IArticleBlogMapper;
import com.yueny.blog.dao.cd.ArticleBlogCd;
import com.yueny.blog.entry.article.ArticleBlogEntry;
import com.yueny.kapo.api.annnotation.DbSchemaType;
import com.yueny.kapo.core.condition.builder.DeleteBuilder;
import com.yueny.kapo.core.condition.builder.QueryBuilder;
import com.yueny.kapo.core.condition.column.operand.enums.FuzzySqlOperand;
import com.yueny.kapo.core.dao.SingleTableDao;
import com.yueny.rapid.lang.util.StringUtil;
import com.yueny.superclub.api.page.IPageable;

/**
 * 博客持久层操作
 *
 * @author 袁洋 2015年8月24日 下午1:46:10
 *
 */
@DbSchemaType("BLOG")
@Repository
public class ArticleBlogDaoImpl extends SingleTableDao<ArticleBlogEntry> implements IArticleBlogDao {
	@Autowired
	private IArticleBlogMapper articleBlogMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yueny.blog.dao.article.IArticleBlogDao#deleteByBlogId(java.lang.
	 * String)
	 */
	@Override
	public boolean deleteByBlogId(String articleBlogId) {
		final DeleteBuilder builder = DeleteBuilder.builder().where("ARTICLE_BLOG_ID", articleBlogId).build();
		return super.deleteByColumns(builder) > 0;
	}

	@Override
	public List<ArticleBlogEntry> findByOwenerTagId(final Long owenerTagId) {
		// TODO 如果OWENER_TAG_IDS为28,31, 查询owenerTagId=8,会出问题
		final QueryBuilder builder = QueryBuilder.builder().where("OWENER_TAG_IDS", FuzzySqlOperand.LIKE, owenerTagId)
				.build();

		return super.queryListByColumns(builder);
	}

	@Override
	public boolean plusReadTimes(final String articleBlogId, final int step) {
		return articleBlogMapper.plusReadTimesByArticleBlogId(articleBlogId, step) == 1;
	}

	@Override
	public Long queryAllCount(String articleTitle) {
		final QueryBuilder builder = QueryBuilder.builder()
				.where("ARTICLE_TITLE", FuzzySqlOperand.LIKE_RIGHT, articleTitle).build();

		return super.queryCountByColumns(builder);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.yueny.blog.dao.article.IArticleBlogDao#queryByBlogId(java.lang.
	 * String)
	 */
	@Override
	public ArticleBlogEntry queryByBlogId(final String articleBlogId) {
		final Map<String, Object> whereColumns = new HashMap<>();
		whereColumns.put("ARTICLE_BLOG_ID", articleBlogId);

		return super.queryByColumns(whereColumns);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.yueny.blog.dao.article.IArticleBlogDao#queryByCd(com.yueny.blog.dao.
	 * cd.ArticleBlogCd)
	 */
	@Override
	public List<ArticleBlogEntry> queryByCd(ArticleBlogCd cd) {
		final QueryBuilder.InnerBuilder builder = QueryBuilder.builder();
		if (StringUtil.isNotEmpty(cd.getArticleBlogId())) {
			builder.where("ARTICLE_BLOG_ID", cd.getArticleBlogId());
		}
		if (StringUtil.isNotEmpty(cd.getArticleTitle())) {
			builder.where("ARTICLE_TITLE", FuzzySqlOperand.LIKE_RIGHT, cd.getArticleTitle());
		}
		if (cd.getReadTimes() != null) {
			builder.where("READ_TIMES", FuzzySqlOperand.GREATER_THAN_OR_EQUALS, cd.getReadTimes());
		}

		if (cd.getPageable() != null) {
			return super.queryListByColumns(builder.build(), cd.getPageable());
		}
		return super.queryListByColumns(builder.build());
	}

	@Override
	public ArticleBlogEntry queryByPreviousBlogId(final String articlePreviousBlogId) {
		final QueryBuilder builder = QueryBuilder.builder().where("ARTICLE_PREVIOUS_BLOG_ID", articlePreviousBlogId)
				.build();

		return super.queryByColumns(builder);
	}

	@Override
	public List<ArticleBlogEntry> queryBySelType(final String selType) {
		final QueryBuilder builder = QueryBuilder.builder().where("SEL_TYPE", selType).build();

		return super.queryListByColumns(builder);
	}

	@Override
	public ArticleBlogEntry queryLatestBlog() {
		return articleBlogMapper.queryLatestBlog();
	}

	@Override
	public ArticleBlogEntry queryLatestBlog(final String uid) {
		return articleBlogMapper.queryLatestBlogByUid(uid);
	}

	@Override
	public String queryLatestBlogId(final String uid) {
		return articleBlogMapper.queryLatestBlogId(uid);
	}

	@Override
	public List<ArticleBlogEntry> queryList(final IPageable pageable) {
		final QueryBuilder builder = QueryBuilder.builder().orderBy("CREATE_TIME").build();

		return super.queryListByColumns(builder, pageable);
	}

}
