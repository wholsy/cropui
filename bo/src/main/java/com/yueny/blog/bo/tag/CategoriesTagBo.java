package com.yueny.blog.bo.tag;

import java.util.List;

import com.yueny.superclub.util.common.pojo.BaseBo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 全站文章标签类目
 *
 * @author 袁洋 2015年8月24日 下午1:43:39
 *
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class CategoriesTagBo extends BaseBo {
	/**
	 *
	 */
	private static final long serialVersionUID = 2683714643607192396L;
	/** 分类描述 */
	private String categoriesDesc;
	/** 主键 */
	private Long categoriesId;
	/** 分类名称 */
	private String categoriesName;
	/** 父级分类编号 */
	private String categoriesParentCode;
	/** 全站文章分类编号 */
	private String categoriesTagCode;
	/**
	 * 当前类目的子类目
	 */
	private List<CategoriesTagBo> children;
	/** 级别，顶级类目为1，子类目+1 */
	private Integer level;
	/** 备注 */
	private String memo;

}
