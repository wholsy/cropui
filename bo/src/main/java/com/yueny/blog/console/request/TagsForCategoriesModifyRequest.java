/**
 *
 */
package com.yueny.blog.console.request;

import org.hibernate.validator.constraints.NotEmpty;

import com.yueny.superclub.api.pojo.instance.AbstractBo;

import lombok.Getter;
import lombok.Setter;

/**
 * 指定的全站文章分类的数据修改请求对象
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年1月14日 下午3:23:52
 *
 */
public class TagsForCategoriesModifyRequest extends AbstractBo {
	/**
	 *
	 */
	private static final long serialVersionUID = 4474445246052726352L;

	/** 分类描述 */
	@Getter
	@Setter
	private String categoriesDesc;
	/** 分类名称 */
	@Getter
	@Setter
	@NotEmpty(message = "分类名称不能为空")
	private String categoriesName;
	/** 上级父级分类编号 */
	@Getter
	@Setter
	@NotEmpty(message = "上级分类不能为空")
	private String tagsForUpategoriesCode;
	/** 全站文章分类编号 */
	@Getter
	@Setter
	@NotEmpty(message = "全站文章分类不能为空")
	private String categoriesTagCode;
	/** 备注 */
	@Getter
	@Setter
	private String memo;

	/** 个人分类名称，以逗号 COMMA 分隔 */
	@Getter
	@Setter
	private String owenerTagNameArrays;

}
