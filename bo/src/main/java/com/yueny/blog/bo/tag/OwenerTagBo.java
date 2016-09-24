package com.yueny.blog.bo.tag;

import com.yueny.superclub.util.common.pojo.BaseBo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 我的个人分类类目
 *
 * @author 袁洋 2015年8月24日 下午1:43:39
 *
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class OwenerTagBo extends BaseBo {
	/**
	 *
	 */
	private static final long serialVersionUID = 8545790009589535202L;
	/** 全站文章分类编号 */
	private String categoriesTagCode;
	/** 关联文章数目 */
	private Integer correlaArticleSum;
	/** 是否显示,1显示,0隐藏 */
	private Integer isShow;
	/** 主键 */
	private Long owenerTagId;
	/** 个人分类名称 */
	private String owenerTagName;
	/** 用户唯一标识，用户编号。md5加密生成 */
	private String uid;
	/** 权重, 1-255 */
	private Integer weight;
}
