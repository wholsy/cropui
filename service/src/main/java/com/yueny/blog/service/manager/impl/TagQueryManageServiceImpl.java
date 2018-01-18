package com.yueny.blog.service.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yueny.blog.bo.article.ArticleBlogBo;
import com.yueny.blog.bo.article.ArticleSimpleBlogBo;
import com.yueny.blog.bo.model.document.OwenerTagsData;
import com.yueny.blog.bo.tag.OwenerTagBo;
import com.yueny.blog.service.article.IArticleBlogService;
import com.yueny.blog.service.cache.CacheDataHandler;
import com.yueny.blog.service.cache.comp.CacheService;
import com.yueny.blog.service.manager.ITagQueryManageService;
import com.yueny.blog.service.table.IOwenerTagService;
import com.yueny.rapid.lang.date.DateFormatType;
import com.yueny.rapid.lang.date.DateUtil;
import com.yueny.rapid.lang.exception.DataVerifyAnomalyException;
import com.yueny.rapid.lang.util.collect.ArrayUtil;
import com.yueny.rapid.topic.profiler.ProfilerLog;

@Service
public class TagQueryManageServiceImpl implements ITagQueryManageService {
	@Autowired
	private IArticleBlogService articleBlogService;
	@Autowired
	private CacheService<OwenerTagsData> cacheService;
	@Autowired
	private IOwenerTagService owenerTagService;

	@Override
	@ProfilerLog
	// @Cacheable(value = "getOwenerTagByUId", keyGenerator =
	// "customKeyGenerator")
	public OwenerTagsData getOwenerTag(final String uid) throws DataVerifyAnomalyException {
		final CacheDataHandler<OwenerTagsData> cacheDataHandler = new CacheDataHandler<OwenerTagsData>() {
			@Override
			public OwenerTagsData caller() {

				final OwenerTagsData owenerTagsData = new OwenerTagsData();
				owenerTagsData.setUid(uid);

				final List<OwenerTagBo> owenerTags = owenerTagService.queryByUid(uid);
				owenerTagsData.setOwenerTags(owenerTags);

				for (final OwenerTagBo owenerTagBo : owenerTags) {
					final List<ArticleBlogBo> list = articleBlogService.findByOwenerTagId(owenerTagBo.getOwenerTagId());
					for (final ArticleBlogBo articleBlogBo : list) {
						final ArticleSimpleBlogBo simpleBlog = new ArticleSimpleBlogBo();
						simpleBlog.setArticleBlogId(articleBlogBo.getArticleBlogId());
						simpleBlog.setArticleTitle(articleBlogBo.getArticleTitle());
						simpleBlog.setArticleAlias(articleBlogBo.getArticleAlias());
						simpleBlog.setToday(
								DateUtil.format(articleBlogBo.getCreateTime(), DateFormatType.TIME_LEFT_DIAGONAL));
						simpleBlog.setCreateTime(articleBlogBo.getCreateTime());
						simpleBlog.setModifyUser(articleBlogBo.getModifyUser());
						simpleBlog.setUpdateTime(articleBlogBo.getUpdateTime());

						owenerTagsData.addSimpleBlog(owenerTagBo.getOwenerTagName(), simpleBlog);
					}
				}
				return owenerTagsData;
			}
		};

		return cacheService.cache(ArrayUtil.newArray("getOwenerTagByUId", uid), cacheDataHandler);
	}

}
