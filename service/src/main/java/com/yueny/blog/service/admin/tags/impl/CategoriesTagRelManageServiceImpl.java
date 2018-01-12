/**
 *
 */
package com.yueny.blog.service.admin.tags.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.yueny.blog.bo.tag.CategoriesTagBo;
import com.yueny.blog.bo.tag.OwenerTagBo;
import com.yueny.blog.service.admin.tags.ICategoriesTagRelManageService;
import com.yueny.blog.service.tag.ICategoriesTagService;
import com.yueny.blog.service.tag.IOwenerTagService;
import com.yueny.blog.vo.article.admin.tags.TagsForCategoriesViewsVo;

/**
 * 后台的全站文章分类类目服务
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年1月10日 上午1:12:34
 *
 */
@Service
public class CategoriesTagRelManageServiceImpl implements ICategoriesTagRelManageService {
	@Autowired
	private ICategoriesTagService categoriesTagService;
	@Autowired
	private IOwenerTagService owenerTagService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.yueny.blog.service.admin.tags.ICategoriesTagRelManageService#findAll()
	 */
	@Override
	public List<TagsForCategoriesViewsVo> findAll() {
		final List<CategoriesTagBo> ctList = categoriesTagService.findRootArticleCategories();
		if (CollectionUtils.isEmpty(ctList)) {
			return Collections.emptyList();
		}

		final List<TagsForCategoriesViewsVo> list = new ArrayList<>();
		for (final CategoriesTagBo categoriesTagBo : ctList) {
			final TagsForCategoriesViewsVo vo = new TagsForCategoriesViewsVo();
			vo.setCategoriesTagBo(categoriesTagBo);

			// 添加个人分类列表
			final List<OwenerTagBo> owenerTagList = owenerTagService
					.queryByCategoriesTagCode(categoriesTagBo.getCategoriesTagCode());
			vo.setOwenerTags(Sets.newHashSet(owenerTagList));

			list.add(vo);
		}
		return list;
	}

}
