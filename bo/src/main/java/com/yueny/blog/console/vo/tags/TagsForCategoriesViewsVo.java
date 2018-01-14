package com.yueny.blog.console.vo.tags;

import java.util.Set;

import com.yueny.blog.bo.tag.OwenerTagBo;
import com.yueny.superclub.api.pojo.instance.AbstractBo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 指定的全站文章分类列表展示的数据实体
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月5日 下午1:26:17
 *
 */
@EqualsAndHashCode(callSuper = false)
public class TagsForCategoriesViewsVo extends AbstractBo {
	/**
	 *
	 */
	private static final long serialVersionUID = 4257670446302357004L;

	// /**
	// * 具体的全站文章标签类目
	// */
	// @Getter
	// @Setter
	// private CategoriesTagBo categoriesTagBo;

	/** 分类描述 */
	@Getter
	@Setter
	private String categoriesDesc;
	/** 分类名称 */
	@Getter
	@Setter
	private String categoriesName;
	/** 父级分类编号 */
	@Getter
	@Setter
	private String categoriesParentCode;
	/** 全站文章分类编号 */
	@Getter
	@Setter
	private String categoriesTagCode;
	/** 级别，顶级类目为1，子类目+1 */
	@Getter
	@Setter
	private Integer level;
	/** 备注 */
	@Getter
	@Setter
	private String memo;

	/**
	 * 全站文章标签的个人分类列表
	 */
	@Getter
	@Setter
	private Set<OwenerTagBo> owenerTags;

}
