package com.yueny.blog.entry.tag;

import com.yueny.kapo.api.annnotation.EntryPk;
import com.yueny.kapo.api.pojo.instance.BaseEntry;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 全站文章标签类目表
 *
 * @author 袁洋 2015年8月24日 下午1:41:19
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CategoriesTagEntry extends BaseEntry {
	/** 分类描述 */
	private String categoriesDesc;
	/** 主键 */
	@EntryPk
	private Long categoriesId;
	/** 分类名称 */
	private String categoriesName;
	/** 父级分类编号 */
	private String categoriesParentCode;
	/** 全站文章分类编号 */
	private String categoriesTagCode;
	/** 级别，顶级类目为1，子类目+1 */
	private Integer level;
	/** 备注 */
	private String memo;

}
