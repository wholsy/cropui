package com.yueny.blog.service.tag.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yueny.blog.bo.tag.CategoriesTagBo;
import com.yueny.blog.dao.tag.ICategoriesTagDao;
import com.yueny.blog.entry.tag.CategoriesTagEntry;
import com.yueny.blog.service.BaseBiz;
import com.yueny.blog.service.cache.CacheDataHandler;
import com.yueny.blog.service.cache.comp.CacheListService;
import com.yueny.blog.service.cache.comp.CacheService;
import com.yueny.blog.service.tag.ICategoriesTagService;
import com.yueny.rapid.lang.util.StringUtil;
import com.yueny.rapid.topic.profiler.ProfilerLog;

/**
 * 文章分类类目服务
 *
 * @author 袁洋 2015年8月24日 下午3:30:38
 *
 */
@Service
public class CategoriesTagServiceImpl extends BaseBiz implements ICategoriesTagService {
	@Autowired
	private ICategoriesTagDao articleCategoriesDao;
	@Autowired
	private CacheListService<CategoriesTagBo> cacheListService;
	@Autowired
	private CacheService<CategoriesTagBo> cacheService;

	/**
	 * 递归处理
	 *
	 * @param categoriesCode
	 *            当前文章分类编号
	 * @return children
	 */
	private List<CategoriesTagBo> eachChildren(final String categoriesTagCode) {
		final List<CategoriesTagEntry> each = articleCategoriesDao.queryByParentTagCode(categoriesTagCode);
		if (CollectionUtils.isEmpty(each)) {
			return Collections.emptyList();
		}

		final List<CategoriesTagBo> childrens = new ArrayList<>(map(each, CategoriesTagBo.class));
		for (final CategoriesTagBo children : childrens) {
			// 查看是否还存在子孙类目
			if (articleCategoriesDao.queryCountByParentTagCode(children.getCategoriesTagCode()) > 0) {
				children.setChildren(eachChildren(children.getCategoriesTagCode()));
			}
		}

		return childrens;
	}

	@Override
	@ProfilerLog
	public List<CategoriesTagBo> findArticleCategoriesTree() {
		return cacheListService.cache("findArticleCategoriesTree", new CacheDataHandler<List<CategoriesTagBo>>() {
			@Override
			public List<CategoriesTagBo> caller() {
				/* 获取顶级文章分类类目 */
				final List<CategoriesTagEntry> entrys = articleCategoriesDao.queryByParentTagCode("-1");
				if (CollectionUtils.isEmpty(entrys)) {
					return Collections.emptyList();
				}

				/* 获取子类目 */
				final List<CategoriesTagBo> articleCategoriesTree = map(entrys, CategoriesTagBo.class);
				for (final CategoriesTagBo articleCategories : articleCategoriesTree) {
					if (StringUtil.isEmpty(articleCategories.getCategoriesTagCode())) {
						continue;
					}

					final List<CategoriesTagBo> children = eachChildren(articleCategories.getCategoriesTagCode());
					if (CollectionUtils.isEmpty(children)) {
						continue;
					}
					articleCategories.setChildren(children);
				}

				return articleCategoriesTree;
			}
		});
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.yueny.blog.service.tag.ICategoriesTagService#findByCode(java.util.
	 * Set)
	 */
	@Override
	public List<CategoriesTagBo> findByCode(final Set<String> categoriesCodes) {
		return cacheListService.cache(new CacheDataHandler<List<CategoriesTagBo>>() {
			@Override
			public List<CategoriesTagBo> caller() {
				if (CollectionUtils.isEmpty(categoriesCodes)) {
					return Collections.emptyList();
				}

				final List<CategoriesTagEntry> entrys = articleCategoriesDao.queryByTagCode(categoriesCodes);
				if (CollectionUtils.isEmpty(entrys)) {
					return Collections.emptyList();
				}

				return map(entrys, CategoriesTagBo.class);
			}
		}, "findByCode", categoriesCodes);
	}

	@Override
	public CategoriesTagBo findByCode(final String categoriesCode) {
		return cacheService.cache(categoriesCode, new CacheDataHandler<CategoriesTagBo>() {
			@Override
			public CategoriesTagBo caller() {
				final CategoriesTagEntry entry = articleCategoriesDao.queryByTagCode(categoriesCode);
				if (entry == null) {
					return null;
				}

				return map(entry, CategoriesTagBo.class);
			}
		});
	}

	@Override
	public CategoriesTagBo findByID(final Long categoriesId) {
		return cacheService.cache(categoriesId, new CacheDataHandler<CategoriesTagBo>() {
			@Override
			public CategoriesTagBo caller() {
				final CategoriesTagEntry entry = articleCategoriesDao.queryByID(categoriesId);
				if (entry == null) {
					return null;
				}

				return map(entry, CategoriesTagBo.class);
			}
		});
	}

	@Override
	public List<CategoriesTagBo> findByParentCode(final String categoriesParentCode) {
		return cacheListService.cache(categoriesParentCode, new CacheDataHandler<List<CategoriesTagBo>>() {
			@Override
			public List<CategoriesTagBo> caller() {
				final List<CategoriesTagEntry> entrys = articleCategoriesDao.queryByParentTagCode(categoriesParentCode);
				if (CollectionUtils.isEmpty(entrys)) {
					return Collections.emptyList();
				}
				return map(entrys, CategoriesTagBo.class);
			}
		});
	}

	@Override
	public List<CategoriesTagBo> findRootArticleCategories() {
		return findByParentCode("-1");
	}

	@Override
	public Long insert(final CategoriesTagBo bo) {
		return articleCategoriesDao.insert(map(bo, CategoriesTagEntry.class));
	}

}
