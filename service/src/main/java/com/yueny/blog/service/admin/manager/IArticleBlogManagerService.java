/**
 *
 */
package com.yueny.blog.service.admin.manager;

import java.util.List;

import com.yueny.blog.console.vo.article.ArticleTagBlogVo;
import com.yueny.superclub.api.page.IPageable;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年1月18日 下午5:45:01
 *
 */
public interface IArticleBlogManagerService {
	/**
	 * 根据title进行的简要博文分页模糊查询
	 *
	 * @param pageable
	 *            分页项
	 * @param articleTitle
	 *            文章标题
	 */
	List<ArticleTagBlogVo> findPageListForSimpleWithTitle(IPageable pageable, String articleTitle);

}
