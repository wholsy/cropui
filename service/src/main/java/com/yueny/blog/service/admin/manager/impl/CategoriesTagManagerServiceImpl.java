/**
 *
 */
package com.yueny.blog.service.admin.manager.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.yueny.blog.bo.tag.CategoriesTagBo;
import com.yueny.blog.bo.tag.OwenerTagBo;
import com.yueny.blog.common.BlogConstant;
import com.yueny.blog.console.request.TagsForCategoriesModifyRequest;
import com.yueny.blog.console.vo.tags.TagsForCategorieBaseVo;
import com.yueny.blog.console.vo.tags.TagsForCategoriesViewsVo;
import com.yueny.blog.service.BaseBiz;
import com.yueny.blog.service.admin.manager.ICategoriesTagManagerService;
import com.yueny.blog.service.comp.uid.OwenerTagCodeGenerate;
import com.yueny.blog.service.disruptor.event.FigureTagCheckerEvent;
import com.yueny.blog.service.disruptor.producer.FigureTagCheckerEventProducer;
import com.yueny.blog.service.table.IArticleBlogService;
import com.yueny.blog.service.table.ICategoriesTagService;
import com.yueny.blog.service.table.IOwenerTagService;
import com.yueny.rapid.lang.util.enums.EnableType;
import com.yueny.rapid.topic.profiler.ProfilerLog;
import com.yueny.superclub.api.constant.Constants;

/**
 * 后台的全站文章分类类目服务
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年1月10日 上午1:12:34
 *
 */
@Service
public class CategoriesTagManagerServiceImpl extends BaseBiz implements ICategoriesTagManagerService {
	@Autowired
	private ICategoriesTagService categoriesTagService;
	@Autowired
	private IOwenerTagService owenerTagService;
	@Autowired
	private IArticleBlogService articleBlogService;
	@Autowired
	private OwenerTagCodeGenerate owenerTagCodeGenerate;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.yueny.blog.service.admin.tags.ICategoriesTagRelManageService#findAll()
	 */
	@Override
	@ProfilerLog
	public List<TagsForCategoriesViewsVo> findAll(final String uid) {
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
			final List<OwenerTagBo> owenerTagList = owenerTagService.queryByCategoriesTagCode(uid,
					categoriesTagBo.getCategoriesTagCode());
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
	public TagsForCategoriesViewsVo findByTagsForCode(final String uid, final String categoriesTagCode) {
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
		final List<OwenerTagBo> owenerTagList = owenerTagService.queryByCategoriesTagCode(uid,
				categoriesTagBo.getCategoriesTagCode());
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
	public List<TagsForCategorieBaseVo> findParentTagsByChildrenCodeForUp(final String uid,
			final String categoriesChildrenCode) {
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

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.yueny.blog.service.admin.tags.ICategoriesTagRelManageService#update(com.
	 * yueny.blog.console.request.TagsForCategoriesModifyRequest)
	 */
	@Override
	@Transactional
	public boolean update(final TagsForCategoriesModifyRequest tagsForCategoriesModifyRequest, final String uid) {
		// 获取最新的数据
		final CategoriesTagBo tagBo = categoriesTagService
				.findByCode(tagsForCategoriesModifyRequest.getCategoriesTagCode());
		// 对更新数据进行重新赋值
		tagBo.setCategoriesDesc(tagsForCategoriesModifyRequest.getCategoriesDesc());
		tagBo.setCategoriesName(tagsForCategoriesModifyRequest.getCategoriesName());
		tagBo.setMemo(tagsForCategoriesModifyRequest.getMemo());
		tagBo.setCategoriesParentCode(tagsForCategoriesModifyRequest.getTagsForUpategoriesCode());
		categoriesTagService.update(tagBo);

		final String owenerTagNames = tagsForCategoriesModifyRequest.getOwenerTagNameArrays();
		final List<String> names = Splitter.on(Constants.COMMA)
				// 去掉空的子串
				.omitEmptyStrings()
				// 去掉子串的空格
				.trimResults().splitToList(owenerTagNames);

		// 在删除阶段保留的记录，在新增阶段也不进行新增
		final List<String> retainNames = Lists.newArrayList();
		// 删除 categoriesTag 下的原有 owenerTag
		final List<OwenerTagBo> ls = owenerTagService.queryByCategoriesTagCode(uid, tagBo.getCategoriesTagCode());
		if (CollectionUtils.isNotEmpty(ls)) {
			final List<Long> deleteIds = ls.stream().filter(owenerTagBo -> {
				// 如果将要新增项包含，则返回false; 意味着不进行该项的删除。
				if (names.contains(owenerTagBo.getOwenerTagName())) {
					retainNames.add(owenerTagBo.getOwenerTagName());
					return false;
				}
				return true;
			}).map(owenerTagBo -> owenerTagBo.getOwenerTagId()).collect(Collectors.toList());

			owenerTagService.deleteById(deleteIds);
		}

		// 新增 categoriesTag 下的 owenerTag
		names.stream().filter(name -> {
			// 在删除阶段保留的记录，在新增阶段也不进行新增
			if (retainNames.contains(name)) {
				return false;
			}
			return true;
		}).forEach(name -> {
			// 先看看当前这个name是否已经存在。如果存在，则直接更新状态为可见
			final OwenerTagBo tag = owenerTagService.queryByTagName(uid, name);
			if (tag != null) {
				owenerTagService.update(tag.getOwenerTagId(), EnableType.ENABLE);
			} else {
				final OwenerTagBo bo = new OwenerTagBo();
				bo.setCategoriesTagCode(tagBo.getCategoriesTagCode());
				bo.setOwenerTagCode(owenerTagCodeGenerate.getOps());
				bo.setIsShow(EnableType.ENABLE.getValue());
				bo.setOwenerTagName(name);
				bo.setUid(uid);
				bo.setCorrelaArticleSum(0);
				bo.setWeight(8);

				owenerTagService.insert(bo);
			}
		});

		// 进行标签完整性检查
		new FigureTagCheckerEventProducer().publishData(FigureTagCheckerEvent.builder()
				.articleBlogService(articleBlogService).owenerTagService(owenerTagService).uid(uid).build());

		return true;
	}

}
