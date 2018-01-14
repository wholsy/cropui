/**
 *
 */
package com.yueny.blog.console.vo.tags;

import com.yueny.superclub.api.pojo.instance.AbstractBo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 只包含code的分类信息的数据实体
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年1月13日 下午11:16:42
 *
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class TagsForCategorieBaseVo extends AbstractBo {
	/**
	 *
	 */
	private static final long serialVersionUID = -2246942503133300318L;

	/** 分类名称 */
	private String categoriesName;
	/** 全站文章分类编号 */
	private String categoriesTagCode;
	/** 备注 */
	private String memo;

}
