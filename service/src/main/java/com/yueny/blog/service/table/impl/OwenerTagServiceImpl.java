package com.yueny.blog.service.table.impl;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yueny.blog.bo.tag.OwenerTagBo;
import com.yueny.blog.dao.tag.IOwenerTagDao;
import com.yueny.blog.entry.tag.OwenerTagEntry;
import com.yueny.blog.service.BaseBiz;
import com.yueny.blog.service.cache.CacheDataHandler;
import com.yueny.blog.service.cache.comp.CacheListService;
import com.yueny.blog.service.cache.comp.CacheService;
import com.yueny.blog.service.table.IOwenerTagService;
import com.yueny.rapid.topic.profiler.ProfilerLog;

/**
 * 文章标签类目
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月2日 上午10:08:24
 *
 */
@Service
public class OwenerTagServiceImpl extends BaseBiz implements IOwenerTagService {
	@Autowired
	private CacheListService<OwenerTagBo> cacheListService;
	@Autowired
	private CacheService<OwenerTagBo> cacheService;
	@Autowired
	private IOwenerTagDao owenerTagDao;

	@Override
	public Long insert(final OwenerTagBo bo) {
		return owenerTagDao.insert(map(bo, OwenerTagEntry.class));
	}

	@Override
	public boolean plusCorrelaArticle(final long primaryId, final int step) {
		return owenerTagDao.plusCorrelaArticle(primaryId, step);
	}

	@Override
	@ProfilerLog
	public List<OwenerTagBo> queryAllByUid(final String uid) {
		return cacheListService.cache(new CacheDataHandler<List<OwenerTagBo>>() {
			@Override
			public List<OwenerTagBo> caller() {
				final List<OwenerTagEntry> entrys = owenerTagDao.queryAllByUid(uid);
				if (CollectionUtils.isEmpty(entrys)) {
					Collections.emptyList();
				}

				return map(entrys, OwenerTagBo.class);
			}
		}, 2L, TimeUnit.SECONDS, "queryAllByUid", uid);
	}

	@Override
	public List<OwenerTagBo> queryByCategoriesTagCode(final String categoriesTagCode) {
		return cacheListService.cache(new CacheDataHandler<List<OwenerTagBo>>() {
			@Override
			public List<OwenerTagBo> caller() {
				final List<OwenerTagEntry> entrys = owenerTagDao.queryByCategoriesTagCode(categoriesTagCode);
				if (CollectionUtils.isEmpty(entrys)) {
					Collections.emptyList();
				}

				return map(entrys, OwenerTagBo.class);
			}
		}, "queryByCategoriesTagCode", categoriesTagCode);
	}

	@Override
	public OwenerTagBo queryById(final long primaryId) {
		return cacheService.cache(new CacheDataHandler<OwenerTagBo>() {
			@Override
			public OwenerTagBo caller() {
				final OwenerTagEntry entry = owenerTagDao.queryByID(primaryId);
				if (entry == null) {
					return null;
				}

				return map(entry, OwenerTagBo.class);
			}
		}, 2L, TimeUnit.SECONDS, "queryById", primaryId);
	}

	@Override
	public List<OwenerTagBo> queryById(final Set<Long> owenerTagIds) {
		return cacheListService.cache(owenerTagIds, new CacheDataHandler<List<OwenerTagBo>>() {
			@Override
			public List<OwenerTagBo> caller() {
				final List<OwenerTagEntry> entrys = owenerTagDao.queryByID(owenerTagIds);
				if (CollectionUtils.isEmpty(entrys)) {
					Collections.emptyList();
				}

				return map(entrys, OwenerTagBo.class);
			}
		}, 2L);
	}

	@Override
	@ProfilerLog
	public List<OwenerTagBo> queryByUid(final String uid) {
		return cacheListService.cache(new CacheDataHandler<List<OwenerTagBo>>() {
			@Override
			public List<OwenerTagBo> caller() {
				final List<OwenerTagEntry> entrys = owenerTagDao.queryByUid(uid);
				if (CollectionUtils.isEmpty(entrys)) {
					Collections.emptyList();
				}

				return map(entrys, OwenerTagBo.class);
			}
		}, 2L, TimeUnit.SECONDS, "queryByUid", uid);
	}

}