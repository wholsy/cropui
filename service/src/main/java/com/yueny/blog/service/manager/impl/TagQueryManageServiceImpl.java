package com.yueny.blog.service.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.yueny.blog.bo.article.ArticleBlogBo;
import com.yueny.blog.bo.article.ArticleSimpleBlogBo;
import com.yueny.blog.bo.model.document.OwenerTagsData;
import com.yueny.blog.bo.tag.OwenerTagBo;
import com.yueny.blog.common.cache.ICacheUsableful;
import com.yueny.blog.service.IArticleBlogService;
import com.yueny.blog.service.IOwenerTagService;
import com.yueny.blog.service.manager.ITagQueryManageService;
import com.yueny.rapid.lang.date.DateFormatType;
import com.yueny.rapid.lang.date.DateUtil;
import com.yueny.rapid.lang.exception.DataVerifyAnomalyException;
import com.yueny.rapid.topic.profiler.ProfilerLog;

@Service
public class TagQueryManageServiceImpl implements ITagQueryManageService, ICacheUsableful {
	@Autowired
	private IArticleBlogService articleBlogService;
	@Autowired
	private IOwenerTagService owenerTagService;

	private List<ArticleSimpleBlogBo> getArticleSimpleBlog(final List<ArticleBlogBo> ArticleBlogList) {
		final List<ArticleSimpleBlogBo> articleSimpleBlogList = new ArrayList<ArticleSimpleBlogBo>();
		for (final ArticleBlogBo articleBlogBo : ArticleBlogList) {
			final ArticleSimpleBlogBo simpleBlog = new ArticleSimpleBlogBo();
			simpleBlog.setArticleBlogId(articleBlogBo.getArticleBlogId());
			simpleBlog.setArticleTitle(articleBlogBo.getArticleTitle());
			simpleBlog.setArticleAlias(articleBlogBo.getArticleAlias());
			simpleBlog.setToday(DateUtil.format(articleBlogBo.getCreateTime(), DateFormatType.TIME_LEFT_DIAGONAL));
			simpleBlog.setCreateTime(articleBlogBo.getCreateTime());
			simpleBlog.setModifyUser(articleBlogBo.getModifyUser());
			simpleBlog.setUpdateTime(articleBlogBo.getUpdateTime());

			articleSimpleBlogList.add(simpleBlog);
		}

		return articleSimpleBlogList;
	}

	@Override
	@ProfilerLog
	@Cacheable(value = "content", key = "#uid + 'getOwenerTagByUId'")
	public OwenerTagsData getOwenerTag(final String uid) throws DataVerifyAnomalyException {
		final OwenerTagsData owenerTagsData = new OwenerTagsData();
		owenerTagsData.setUid(uid);

		final List<OwenerTagBo> owenerTags = owenerTagService.queryByUid(uid);
		owenerTagsData.setOwenerTags(owenerTags);

		for (final OwenerTagBo owenerTagBo : owenerTags) {
			final List<ArticleBlogBo> list = articleBlogService.findByOwenerTagIds(owenerTagBo.getOwenerTagId());

			owenerTagsData.addSimpleBlog(owenerTagBo.getOwenerTagName(), getArticleSimpleBlog(list));
		}

		// 尚未分配标签的博文
		final List<ArticleBlogBo> listForEmpty = articleBlogService.findByOwenerTagIds(null);
		if (CollectionUtils.isNotEmpty(listForEmpty)) {
			final OwenerTagBo emptyOwenerTagBo = new OwenerTagBo();
			emptyOwenerTagBo.setCorrelaArticleSum(listForEmpty.size());
			emptyOwenerTagBo.setOwenerTagName("未分配标签");
			owenerTagsData.getOwenerTags().add(emptyOwenerTagBo);

			owenerTagsData.addSimpleBlog(emptyOwenerTagBo.getOwenerTagName(), getArticleSimpleBlog(listForEmpty));
		}

		return owenerTagsData;
	}

}
