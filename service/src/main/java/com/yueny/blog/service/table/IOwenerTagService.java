package com.yueny.blog.service.table;

import java.util.List;
import java.util.Set;

import com.yueny.blog.bo.tag.OwenerTagBo;
import com.yueny.rapid.lang.util.enums.EnableType;

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
	 * 根据主键设置为不可用(伪删除)
	 *
	 * @param primaryId
	 *            数据主键
	 * @return 删除结果
	 */
	boolean deleteById(List<Long> primaryIds);

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
	 * @param categoriesTagCode
	 *            全站分类编号
	 */
	List<OwenerTagBo> queryByCategoriesTagCode(String uid, final String categoriesTagCode);

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
	 * @param tagName
	 *            全站分类名称
	 */
	OwenerTagBo queryByTagName(String uid, final String tagName);

	/**
	 * 获取显示的个人分类类目
	 *
	 * @param uid
	 *            uid
	 */
	List<OwenerTagBo> queryByUid(String uid);

	/**
	 * 获取所有(显示/隐藏)个人分类类目
	 *
	 * @param uid
	 *            uid
	 */
	List<OwenerTagBo> queryByUidForAll(String uid);

	/**
	 * 根据主键值更新是否可见数据<br>
	 * 该操作会更新更新版本列,更新修改人,处理更新时间 * @param primaryId 主键
	 *
	 * @param isShow
	 *            是否可见
	 * @return 是否成功
	 */
	boolean update(final long primaryId, EnableType isShow);

}
