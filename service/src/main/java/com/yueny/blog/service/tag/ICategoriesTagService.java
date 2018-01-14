package com.yueny.blog.service.tag;

import java.util.List;
import java.util.Set;

import com.yueny.blog.bo.tag.CategoriesTagBo;

/**
 * 全站文章分类类目服务
 *
 * @author 袁洋 2015年8月24日 下午3:30:00
 *
 */
public interface ICategoriesTagService {
	/**
	 * 获取当前文章分类类目树
	 *
	 * @return 类目树
	 */
	List<CategoriesTagBo> findArticleCategoriesTree();

	/**
	 * 全站文章分类编号查询
	 *
	 * @param categoriesCodes
	 *            全站文章分类编号
	 */
	List<CategoriesTagBo> findByCode(Set<String> categoriesCodes);

	/**
	 * 文章分类编号查询
	 *
	 * @param categoriesCode
	 *            文章分类编号
	 */
	CategoriesTagBo findByCode(final String categoriesCode);

	/**
	 * 主键查询
	 *
	 * @param categoriesId
	 *            数据主键
	 */
	CategoriesTagBo findByID(final Long categoriesId);

	/**
	 * 查询父级文章的子类分类编号
	 *
	 * @param categoriesParentCode
	 *            父级分类编号
	 */
	List<CategoriesTagBo> findByParentCode(final String categoriesParentCode);

	/**
	 * @return 获取顶级文章分类类目
	 */
	List<CategoriesTagBo> findRootArticleCategories();

	/**
	 * 新增
	 *
	 * @param bo
	 *            文章分类类目实体
	 * @return 新增主键
	 */
	Long insert(final CategoriesTagBo bo);
}
