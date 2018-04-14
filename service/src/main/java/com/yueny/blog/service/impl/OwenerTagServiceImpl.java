package com.yueny.blog.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yueny.blog.bo.tag.OwenerTagBo;
import com.yueny.blog.dao.tag.IOwenerTagDao;
import com.yueny.blog.entry.tag.OwenerTagEntry;
import com.yueny.blog.service.BaseBiz;
import com.yueny.blog.service.comp.cache.CacheDataHandler;
import com.yueny.blog.service.comp.cache.core.CacheListService;
import com.yueny.blog.service.comp.cache.core.CacheService;
import com.yueny.blog.service.IOwenerTagService;
import com.yueny.rapid.lang.util.enums.EnableType;
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

	/*
	 * (non-Javadoc)
	 *
	 * @see com.yueny.blog.service.IOwenerTagService#deleteById(long)
	 */
	@Override
	public boolean deleteById(final List<Long> primaryIds) {
		final int sInt = primaryIds.stream().mapToInt(id -> {
			final OwenerTagEntry entry = owenerTagDao.queryByID(id);
			// 更新为不可见
			entry.setIsShow(EnableType.DISENABLE.getValue());
			final boolean rs = owenerTagDao.update(entry);

			if (!rs) {
				return 0;
			}
			return 1;
		}).sum();

		return primaryIds.size() == sInt;
	}

	@Override
	public Long insert(final OwenerTagBo bo) {
		return owenerTagDao.insert(map(bo, OwenerTagEntry.class));
	}

	@Override
	public boolean plusCorrelaArticle(final long primaryId, final int step) {
		return owenerTagDao.plusCorrelaArticle(primaryId, step);
	}

	@Override
	public List<OwenerTagBo> queryByCategoriesTagCode(final String uid, final String categoriesTagCode) {
		return cacheListService.cache(new CacheDataHandler<List<OwenerTagBo>>() {
			@Override
			public List<OwenerTagBo> caller() {
				final List<OwenerTagEntry> entrys = owenerTagDao.queryByCategoriesTagCode(uid, categoriesTagCode);
				if (CollectionUtils.isEmpty(entrys)) {
					Collections.emptyList();
				}

				return map(entrys, OwenerTagBo.class);
			}
		}, "queryByCategoriesTagCode", categoriesTagCode);
	}

	@Override
	public List<OwenerTagBo> queryByCode(final Set<String> owenerTagCode) {
		if (CollectionUtils.isEmpty(owenerTagCode)) {
			return Collections.emptyList();
		}
		final List<OwenerTagEntry> entry = owenerTagDao.queryByCode(owenerTagCode);
		if (entry == null) {
			return Collections.emptyList();
		}

		return map(entry, OwenerTagBo.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.yueny.blog.service.IOwenerTagService#queryByCode(java.lang.String)
	 */
	@Override
	public OwenerTagBo queryByCode(final String owenerTagCode) {
		if (StringUtils.isEmpty(owenerTagCode)) {
			return null;
		}

		final OwenerTagEntry entry = owenerTagDao.queryByCode(owenerTagCode);
		if (entry == null) {
			return null;
		}

		return map(entry, OwenerTagBo.class);
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

	/*
	 * (non-Javadoc)
	 *
	 * @see com.yueny.blog.service.IOwenerTagService#queryByTagName(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public OwenerTagBo queryByTagName(final String uid, final String tagName) {
		final OwenerTagEntry entry = owenerTagDao.queryByTagName(uid, tagName);
		if (entry == null) {
			return null;
		}

		return map(entry, OwenerTagBo.class);
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

	@Override
	@ProfilerLog
	public List<OwenerTagBo> queryByUidForAll(final String uid) {
		return cacheListService.cache(new CacheDataHandler<List<OwenerTagBo>>() {
			@Override
			public List<OwenerTagBo> caller() {
				final List<OwenerTagEntry> entrys = owenerTagDao.queryByUidForAll(uid);
				if (CollectionUtils.isEmpty(entrys)) {
					Collections.emptyList();
				}

				return map(entrys, OwenerTagBo.class);
			}
		}, 2L, TimeUnit.SECONDS, "queryAllByUid", uid);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.yueny.blog.service.IOwenerTagService#update(long,
	 * java.lang.Integer)
	 */
	@Override
	public boolean update(final long primaryId, final EnableType isShow) {
		return owenerTagDao.update(primaryId, isShow.getValue());
	}

}
