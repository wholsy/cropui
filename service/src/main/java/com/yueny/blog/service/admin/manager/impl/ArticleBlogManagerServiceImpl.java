/**
 *
 */
package com.yueny.blog.service.admin.manager.impl;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.yueny.blog.bo.article.ArticleBlogBo;
import com.yueny.blog.bo.tag.CategoriesTagBo;
import com.yueny.blog.console.vo.article.ArticleBlogForCategoryTagModel;
import com.yueny.blog.console.vo.article.ArticleTagBlogVo;
import com.yueny.blog.service.BaseBiz;
import com.yueny.blog.service.admin.manager.IArticleBlogManagerService;
import com.yueny.blog.service.table.IArticleBlogService;
import com.yueny.blog.service.table.ICategoriesTagService;
import com.yueny.rapid.lang.date.DateFormatType;
import com.yueny.rapid.lang.date.DateUtil;
import com.yueny.rapid.lang.util.collect.CollectionUtil;
import com.yueny.rapid.topic.profiler.ProfilerLog;
import com.yueny.superclub.api.page.IPageable;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年1月18日 下午5:45:26
 *
 */
@Service
public class ArticleBlogManagerServiceImpl extends BaseBiz implements IArticleBlogManagerService {
	@Autowired
	private ICategoriesTagService categoriesTagService;
	@Autowired
	private IArticleBlogService articleBlogService;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.yueny.blog.service.manager.IArticleBlogManagerService#
	 * findPageListForSimpleWithTitle(com.yueny.superclub.api.page.IPageable,
	 * java.lang.String)
	 */
	@Override
	@ProfilerLog
	public List<ArticleTagBlogVo> findPageListForSimpleWithTitle(final IPageable pageable, final String articleTitle) {
		final List<ArticleBlogBo> lists = articleBlogService.findPageList(pageable, articleTitle);

		final List<ArticleTagBlogVo> ls = Lists.newArrayList();
		lists.stream().forEach(blog -> {
			final ArticleTagBlogVo simpleBlog = new ArticleTagBlogVo();
			simpleBlog.setArticleBlogId(blog.getArticleBlogId());
			simpleBlog.setArticleTitle(blog.getArticleTitle());
			simpleBlog.setArticleAlias(blog.getArticleAlias());
			simpleBlog.setToday(DateUtil.format(blog.getCreateTime(), DateFormatType.TIME_LEFT_DIAGONAL));
			simpleBlog.setCreateTime(blog.getCreateTime());
			simpleBlog.setModifyUser(blog.getModifyUser());
			simpleBlog.setUpdateTime(blog.getUpdateTime());

			// 处理全站文章标签列表
			final List<CategoriesTagBo> ct = categoriesTagService.findByCode(blog.getCategoryTagCodes());
			if (CollectionUtil.isEmpty(ct)) {
				simpleBlog.setCategoryTagsForBlog(Collections.emptySet());
			} else {
				final Set<ArticleBlogForCategoryTagModel> ctb = Sets.newHashSet();
				ct.stream().forEach(c -> {
					final ArticleBlogForCategoryTagModel bo = new ArticleBlogForCategoryTagModel();
					bo.setCategoryTagCode(c.getCategoriesTagCode());
					bo.setCategoryTagName(c.getCategoriesName());
					ctb.add(bo);
				});
				simpleBlog.setCategoryTagsForBlog(ctb);
			}

			// 处理个人分类列表
			// owenerTagService.

			ls.add(simpleBlog);
		});

		return ls;
	}

}
