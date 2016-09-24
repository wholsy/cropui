package com.yueny.blog.dao.tag;

import java.util.List;
import java.util.Set;

import com.yueny.blog.entry.tag.CategoriesTagEntry;
import com.yueny.kapo.api.ISingleTableDao;
import com.yueny.kapo.api.IWholeTableQuery;

/**
 * 文章分类类目持久层操作
 *
 * @author 袁洋 2015年8月24日 下午1:45:35
 *
 */
public interface ICategoriesTagDao extends ISingleTableDao<CategoriesTagEntry>, IWholeTableQuery<CategoriesTagEntry> {
	/**
	 * 获取父级分类编号的孩子
	 *
	 * @param categoriesParentCode
	 *            父级分类编号
	 * @return 获取父级分类编号的孩子
	 */
	List<CategoriesTagEntry> queryByParentTagCode(final String categoriesParentCode);

	/**
	 * 全站文章分类编号查询
	 *
	 * @param categoriesCodes
	 *            全站文章分类编号
	 */
	List<CategoriesTagEntry> queryByTagCode(Set<String> categoriesCodes);

	/**
	 * @param categoriesCode
	 *            文章分类编号
	 */
	CategoriesTagEntry queryByTagCode(String categoriesCode);

	/**
	 * 获取父级分类编号的孩子数目
	 *
	 * @param categoriesParentCode
	 *            父级分类编号
	 * @return 父级分类编号的孩子数目
	 */
	Long queryCountByParentTagCode(final String categoriesParentTagCode);
}
