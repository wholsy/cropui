package com.yueny.blog.service.admin.manager;

import java.util.List;

import com.yueny.blog.console.request.TagsForCategoriesModifyRequest;
import com.yueny.blog.console.vo.tags.TagsForCategorieBaseVo;
import com.yueny.blog.console.vo.tags.TagsForCategoriesViewsVo;

/**
 * 后台的全站文章分类类目服务
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年1月10日 上午1:09:40
 *
 */
public interface ICategoriesTagManagerService {
	/**
	 * 获取全站文章分类列表
	 *
	 * @return 类目树
	 */
	List<TagsForCategoriesViewsVo> findAll(String uid);

	/**
	 * 获取指定文章分类类目信息
	 *
	 * @param categoriesTagCode
	 *            全站文章分类编号
	 */
	TagsForCategoriesViewsVo findByTagsForCode(String uid, String categoriesTagCode);

	/**
	 * 查询当前子分类对应的父级所属层级的父分类列表
	 *
	 * @param categoriesChildrenCode
	 *            当前子分类编号
	 */
	List<TagsForCategorieBaseVo> findParentTagsByChildrenCodeForUp(String uid, final String categoriesChildrenCode);

	/**
	 * 修改文章分类类目信息
	 *
	 * @param tagsForCategoriesModifyRequest
	 *            全站文章分类的数据修改请求对象
	 * @param uid
	 *            用户uid
	 * @return
	 */
	boolean update(TagsForCategoriesModifyRequest tagsForCategoriesModifyRequest, String uid);

}
