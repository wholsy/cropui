package com.yueny.blog.dao.tag;

import java.util.List;
import java.util.Set;

import com.yueny.blog.entry.tag.OwenerTagEntry;
import com.yueny.kapo.api.IDeleteTableDao;
import com.yueny.kapo.api.ISingleTableDao;
import com.yueny.kapo.api.IWholeTableQueryDao;

/**
 * 个人分类类目持久层操作
 *
 * @author 袁洋 2015年8月24日 下午1:45:35
 *
 */
public interface IOwenerTagDao
		extends ISingleTableDao<OwenerTagEntry>, IDeleteTableDao, IWholeTableQueryDao<OwenerTagEntry> {
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
	 * 根据全站分类编号获取可用的个人类目
	 *
	 * @param categoriesTagCode
	 *            全站分类编号
	 */
	List<OwenerTagEntry> queryByCategoriesTagCode(String uid, final String categoriesTagCode);

	/**
	 * 使用主键列表查询实体
	 *
	 * @param entryIds
	 *            实体主键列表
	 * @return 对应的实体，如果不存在返回null
	 */
	List<OwenerTagEntry> queryByID(final Set<Long> entryIds);

	/**
	 * @param uid
	 *            uid
	 * @param tagName
	 *            全站分类名称
	 */
	OwenerTagEntry queryByTagName(String uid, String tagName);

	/**
	 * 获取显示的个人可用分类类目
	 *
	 * @param uid
	 *            uid
	 */
	List<OwenerTagEntry> queryByUid(String uid);

	/**
	 * 获取所有(显示/隐藏)个人分类类目
	 *
	 * @param uid
	 *            uid
	 */
	List<OwenerTagEntry> queryByUidForAll(String uid);

	/**
	 * 根据主键值更新是否可见数据<br>
	 * 该操作会更新更新版本列,更新修改人,处理更新时间 * @param primaryId 主键
	 *
	 * @param isShow
	 *            是否可见
	 * @return 是否成功
	 */
	boolean update(final long primaryId, Integer isShow);

}
