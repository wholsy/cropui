package com.yueny.blog.service.manager.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.yueny.blog.bo.model.document.ChartData;
import com.yueny.blog.bo.tag.CategoriesTagBo;
import com.yueny.blog.bo.tag.OwenerTagBo;
import com.yueny.blog.service.BaseBiz;
import com.yueny.blog.service.cache.CacheDataHandler;
import com.yueny.blog.service.cache.comp.CacheService;
import com.yueny.blog.service.manager.ITagQueryManageService;
import com.yueny.blog.service.tag.ICategoriesTagService;
import com.yueny.blog.service.tag.IOwenerTagService;
import com.yueny.rapid.lang.exception.DataVerifyAnomalyException;

/**
 * 标签查询服务
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月12日 下午9:02:00
 *
 */
@Service
public class TagQueryManageServiceImpl extends BaseBiz implements ITagQueryManageService {
	@Autowired
	private CacheService<ChartData> cacheService;
	@Autowired
	private ICategoriesTagService categoriesTagService;
	@Autowired
	private IOwenerTagService owenerTagService;

	/**
	 * 递归处理
	 */
	private List<ChartData> eachChildren(final List<CategoriesTagBo> lists) {
		final List<ChartData> childrens = Lists.newArrayList();
		for (final CategoriesTagBo categoriesTagBo : lists) {
			final ChartData node = new ChartData(categoriesTagBo.getCategoriesTagCode(),
					categoriesTagBo.getCategoriesId(), categoriesTagBo.getCategoriesName());

			if (CollectionUtils.isNotEmpty(categoriesTagBo.getChildren())) {
				node.addChildrenNodes(eachChildren(categoriesTagBo.getChildren()));
			}
			childrens.add(node);
		}

		return childrens;
	}

	/**
	 * 数据对象本身递归处理
	 */
	private void eachChildrenChartData(final List<ChartData> chartDatas) {
		// 根据全站标签,获取最底层非叶子节点的叶子节点数据
		for (final ChartData nextChartData : chartDatas) {
			if (CollectionUtils.isNotEmpty(nextChartData.getChildren())) {
				eachChildrenChartData(nextChartData.getChildren());
			}

			final List<OwenerTagBo> oweners = owenerTagService.queryByCategoriesTagCode(nextChartData.getCode());
			if (CollectionUtils.isEmpty(oweners)) {
				continue;
			}

			for (final OwenerTagBo owenerTagBo : oweners) {
				// 组装mark语言
				// name": "[Chrome](http://www.google.com/chrome)/
				final String name = owenerTagBo.getOwenerTagName();
				final ChartData node = new ChartData(owenerTagBo.getOwenerTagId().toString(),
						owenerTagBo.getOwenerTagId(), name);
				nextChartData.addChildrenNodes(node);
			}
		}
	}

	@Override
	public ChartData getChartData() throws DataVerifyAnomalyException {
		return cacheService.cache(new CacheDataHandler<ChartData>() {
			@Override
			public ChartData caller() {
				final CategoriesTagBo root = categoriesTagService.findByCode("-1");
				final ChartData chartData = new ChartData(root.getCategoriesTagCode(), root.getCategoriesId(),
						root.getCategoriesName());

				final List<CategoriesTagBo> categories = categoriesTagService.findArticleCategoriesTree();
				if (CollectionUtils.isEmpty(categories)) {
					return chartData;
				}

				// 添加全站标签
				for (final CategoriesTagBo categoriesTagBo : categories) {
					final ChartData node = new ChartData(categoriesTagBo.getCategoriesTagCode(),
							categoriesTagBo.getCategoriesId(), categoriesTagBo.getCategoriesName());

					if (CollectionUtils.isNotEmpty(categoriesTagBo.getChildren())) {
						node.addChildrenNodes(eachChildren(categoriesTagBo.getChildren()));
					}
					chartData.addChildrenNodes(node);
				}

				// 根据全站标签,获取最底层非叶子节点的叶子节点数据
				eachChildrenChartData(chartData.getChildren());

				return chartData;
			}
		}, "getChartData_", "ROOT");
	}
}
