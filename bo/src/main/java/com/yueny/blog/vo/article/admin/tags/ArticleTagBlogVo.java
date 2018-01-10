package com.yueny.blog.vo.article.admin.tags;

import java.util.Set;

import com.yueny.blog.bo.article.ArticleSimpleBlogBo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 博文列表中的简要博文对象
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月5日 下午1:26:17
 *
 */
@EqualsAndHashCode(callSuper = false)
public class ArticleTagBlogVo extends ArticleSimpleBlogBo {

	/**
	*
	*/
	private static final long serialVersionUID = 5991141008111258637L;

	/**
	 * 该文章所拥有的全站文章分类列表
	 */
	@Getter
	@Setter
	private Set<ArticleBlogForCategoryTagModel> categoryTagsForBlog;

//	/**
//	 * 该文章所拥有的个人分类列表
//	 */
//	@Getter
//	@Setter
//	private Set<ArticleBlogForOwenerTagModel> owenerTagsList;
	
}
