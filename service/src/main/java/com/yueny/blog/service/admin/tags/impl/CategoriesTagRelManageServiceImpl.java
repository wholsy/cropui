/**
 *
 */
package com.yueny.blog.service.admin.tags.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.yueny.blog.bo.tag.CategoriesTagBo;
import com.yueny.blog.bo.tag.OwenerTagBo;
import com.yueny.blog.common.BlogConstant;
import com.yueny.blog.console.vo.tags.TagsForCategorieBaseVo;
import com.yueny.blog.console.vo.tags.TagsForCategoriesViewsVo;
import com.yueny.blog.service.BaseBiz;
import com.yueny.blog.service.admin.tags.ICategoriesTagRelManageService;
import com.yueny.blog.service.tag.ICategoriesTagService;
import com.yueny.blog.service.tag.IOwenerTagService;
import com.yueny.rapid.topic.profiler.ProfilerLog;

/**
 * 后台的全站文章分类类目服务
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年1月10日 上午1:12:34
 *
 */
@Service
public class CategoriesTagRelManageServiceImpl extends BaseBiz implements ICategoriesTagRelManageService {
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
	@ProfilerLog
	public List<TagsForCategoriesViewsVo> findAll() {
		final List<CategoriesTagBo> ctList = categoriesTagService.findRootArticleCategories();
		if (CollectionUtils.isEmpty(ctList)) {
			return Collections.emptyList();
		}

		final List<TagsForCategoriesViewsVo> list = new ArrayList<>();
		for (final CategoriesTagBo categoriesTagBo : ctList) {
			final TagsForCategoriesViewsVo vo = new TagsForCategoriesViewsVo();
			vo.setCategoriesName(categoriesTagBo.getCategoriesName());
			vo.setCategoriesDesc(categoriesTagBo.getCategoriesDesc());
			vo.setCategoriesTagCode(categoriesTagBo.getCategoriesTagCode());
			vo.setCategoriesParentCode(categoriesTagBo.getCategoriesParentCode());
			vo.setLevel(categoriesTagBo.getLevel());
			vo.setMemo(categoriesTagBo.getMemo());

			// 添加个人分类列表
			final List<OwenerTagBo> owenerTagList = owenerTagService
					.queryByCategoriesTagCode(categoriesTagBo.getCategoriesTagCode());
			vo.setOwenerTags(Sets.newHashSet(owenerTagList));

			list.add(vo);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.yueny.blog.service.admin.tags.ICategoriesTagRelManageService#
	 * findByTagsForCode(java.lang.String)
	 */
	@Override
	@ProfilerLog
	public TagsForCategoriesViewsVo findByTagsForCode(final String categoriesTagCode) {
		final CategoriesTagBo categoriesTagBo = categoriesTagService.findByCode(categoriesTagCode);
		if (categoriesTagBo == null) {
			return null;
		}

		final TagsForCategoriesViewsVo vo = new TagsForCategoriesViewsVo();
		vo.setCategoriesName(categoriesTagBo.getCategoriesName());
		vo.setCategoriesDesc(categoriesTagBo.getCategoriesDesc());
		vo.setCategoriesTagCode(categoriesTagBo.getCategoriesTagCode());
		vo.setCategoriesParentCode(categoriesTagBo.getCategoriesParentCode());
		vo.setLevel(categoriesTagBo.getLevel());
		vo.setMemo(categoriesTagBo.getMemo());

		// 添加个人分类列表
		final List<OwenerTagBo> owenerTagList = owenerTagService
				.queryByCategoriesTagCode(categoriesTagBo.getCategoriesTagCode());
		vo.setOwenerTags(Sets.newHashSet(owenerTagList));

		return vo;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.yueny.blog.service.admin.tags.ICategoriesTagRelManageService#
	 * findByTagsForParentCode(java.lang.String)
	 */
	@Override
	@ProfilerLog
	public List<TagsForCategorieBaseVo> findParentTagsByChildrenCodeForUp(final String categoriesChildrenCode) {
		if (StringUtils.isEmpty(categoriesChildrenCode)) {
			return Collections.emptyList();
		}

		// 获取当前子分类的父分类信息 'A950003'/-1
		final CategoriesTagBo currentTagBo = categoriesTagService.findByCode(categoriesChildrenCode);
		if (currentTagBo == null) {
			return Collections.emptyList();
		}

		String queryCode = "";
		if (StringUtils.equals(currentTagBo.getCategoriesTagCode(), BlogConstant.CATEGORIES_TAG_CODE_FOR_ROOT)) {
			// 已经是顶级项 '-2'
			queryCode = currentTagBo.getCategoriesParentCode();
		} else {
			// 获取指定父分类 '-1'
			final CategoriesTagBo parentTagBo = categoriesTagService.findByCode(currentTagBo.getCategoriesParentCode());
			if (parentTagBo == null) {
				return Collections.emptyList();
			}
			queryCode = parentTagBo.getCategoriesParentCode();
		}

		// 获取所有父分类那一层的分类信息 '-2'
		final List<CategoriesTagBo> ctList = categoriesTagService.findByParentCode(queryCode);
		if (CollectionUtils.isEmpty(ctList)) {
			return Collections.emptyList();
		}

		return mapAny(ctList, TagsForCategorieBaseVo.class);
	}

}
