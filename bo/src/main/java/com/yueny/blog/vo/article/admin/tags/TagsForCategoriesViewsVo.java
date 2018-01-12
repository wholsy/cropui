package com.yueny.blog.vo.article.admin.tags;

import java.util.Set;

import com.yueny.blog.bo.tag.CategoriesTagBo;
import com.yueny.blog.bo.tag.OwenerTagBo;
import com.yueny.superclub.api.pojo.instance.AbstractBo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 全站文章分类列表展示的数据实体
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

	/**
	 * 具体的全站文章标签类目
	 */
	@Getter
	@Setter
	private CategoriesTagBo categoriesTagBo;

	/**
	 * 全站文章标签的个人分类列表
	 */
	@Getter
	@Setter
	private Set<OwenerTagBo> owenerTags;

}
