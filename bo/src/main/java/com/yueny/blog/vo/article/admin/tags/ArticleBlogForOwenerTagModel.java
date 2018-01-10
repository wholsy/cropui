package com.yueny.blog.vo.article.admin.tags;

import com.yueny.superclub.util.common.pojo.BaseBo;

import lombok.Getter;
import lombok.Setter;

/**
 * 个人分类
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年1月10日 上午12:03:02
 *
 */
@Getter
@Setter
public class ArticleBlogForOwenerTagModel extends BaseBo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 817591290074043472L;
	
	/** 个人分类编号 */
	private String owenerTagId;
	/** 个人分类名称 */
	private String owenerTagName;
	
}