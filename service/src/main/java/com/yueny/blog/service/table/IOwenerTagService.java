package com.yueny.blog.service.table;

import java.util.List;
import java.util.Set;

import com.yueny.blog.bo.tag.OwenerTagBo;

/**
 * 我的文章标签类目
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月2日 上午10:07:48
 *
 */
public interface IOwenerTagService {
	/**
	 * 新增
	 *
	 * @param bo
	 *            个人分类类目实体
	 * @return 新增主键
	 */
	Long insert(final OwenerTagBo bo);

	/**
	 * 文章数目增加step
	 *
	 * @param primaryId
	 *            数据主键
	 * @param step
	 *            增加数目,eg: 1,-1
	 */
	boolean plusCorrelaArticle(final long primaryId, final int step);

	/**
	 * 获取所有(显示/隐藏)个人分类类目
	 *
	 * @param uid
	 *            uid
	 */
	List<OwenerTagBo> queryAllByUid(String uid);

	/**
	 * @param categoriesTagCode
	 *            全站分类编号
	 */
	List<OwenerTagBo> queryByCategoriesTagCode(final String categoriesTagCode);

	/**
	 * 主键查询
	 *
	 * @param primaryId
	 *            数据主键
	 */
	OwenerTagBo queryById(final long primaryId);

	/**
	 * 主键列表查询
	 *
	 * @param owenerTagIds
	 *            数据主键列表
	 */
	List<OwenerTagBo> queryById(Set<Long> owenerTagIds);

	/**
	 * 获取显示的个人分类类目
	 *
	 * @param uid
	 *            uid
	 */
	List<OwenerTagBo> queryByUid(String uid);
}
