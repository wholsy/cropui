package com.yueny.blog.service.manager;

import java.util.List;

import com.yueny.blog.bo.article.ArticleBlogViewBo;
import com.yueny.blog.bo.model.document.ArchiveData;
import com.yueny.rapid.lang.exception.DataVerifyAnomalyException;
import com.yueny.superclub.api.page.core.PageCond;

/**
 * 文章查询服务
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月10日 下午12:01:28
 *
 */
public interface IArticleQueryManageService {
	// /**
	// * 获得指定年份的存档信息
	// *
	// * @throws DataVerifyAnomalyException
	// */
	// ArchiveData getArchive(int year, PageCond pageable) throws
	// DataVerifyAnomalyException;

	/**
	 * 获得所有存档信息
	 *
	 * @throws DataVerifyAnomalyException
	 */
	List<ArchiveData> getArchive(PageCond pageable) throws DataVerifyAnomalyException;

	/**
	 * 博文明细
	 *
	 * @return 新增博文主键
	 * @throws DataVerifyAnomalyException
	 */
	ArticleBlogViewBo getArticleInfo(String articleBlogId) throws DataVerifyAnomalyException;

	//
	// /**
	// * 博文分页查询
	// *
	// * @param pageable
	// * 分页条件
	// */
	// List<ArticlePublishedCondition> findPageList(final IPageable pageable);
}
