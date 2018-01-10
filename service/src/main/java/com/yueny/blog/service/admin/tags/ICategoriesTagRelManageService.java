package com.yueny.blog.service.admin.tags;

import java.util.List;
import com.yueny.blog.vo.article.admin.tags.TagsForCategoriesViewsVo;

/**
 * 后台的全站文章分类类目服务
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年1月10日 上午1:09:40
 *
 */
public interface ICategoriesTagRelManageService {
	/**
	 * 获取当前所有的文章分类类目树
	 *
	 * @return 类目树
	 */
	List<TagsForCategoriesViewsVo>  findAll();

}
