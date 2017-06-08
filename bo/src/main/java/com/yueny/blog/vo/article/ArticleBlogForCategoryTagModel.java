package com.yueny.blog.vo.article;

import com.yueny.superclub.util.common.pojo.BaseBo;

import lombok.Getter;
import lombok.Setter;

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