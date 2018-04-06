package com.yueny.blog.bo.article;

import java.util.List;

import com.yueny.blog.bo.tag.CategoriesTagBo;
import com.yueny.blog.bo.tag.OwenerTagBo;
import com.yueny.superclub.util.common.pojo.BaseBo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 博文明细信息
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月4日 下午10:09:39
 *
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class ArticleBlogViewBo extends BaseBo {
	/**
	 *
	 */
	private static final long serialVersionUID = 5553164796623611167L;
	/**
	 * 博文
	 */
	private ArticleBlogBo articleBlog;
	// /** 文章标签ID,最多添加5个标签，多个标签之间用“,”分隔 */
	// private Set<String> articleTagIds;
	/**
	 * 该博文所归属的全站文章分类
	 */
	private List<CategoriesTagBo> categoriesTagList;
	/**
	 * 下一篇博文
	 */
	private ArticleSimpleBlogBo nextSimpleBlog;
	/**
	 * 该博文所归属的个人分类
	 */
	private List<OwenerTagBo> owenerTags;
	/**
	 * 上一篇博文
	 */
	private ArticleSimpleBlogBo previousSimpleBlog;
}
