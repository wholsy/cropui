package com.yueny.blog.vo.article.admin.tags;

import com.yueny.superclub.util.common.pojo.BaseBo;

import lombok.Getter;
import lombok.Setter;

/**
 * 全站文章分类

 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年1月10日 上午12:03:02
 *
 */
@Getter
@Setter
public class ArticleBlogForCategoryTagModel extends BaseBo {
	/** */
	private static final long serialVersionUID = 6938452333399672864L;

	/** 全站文章分类编号 */
	private String categoryTagCode;
	/** 全站文章分类名称 */
	private String categoryTagName;
}