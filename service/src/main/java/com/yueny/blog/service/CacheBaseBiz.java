package com.yueny.blog.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.yueny.blog.service.env.CacheKeyConstant;
import com.yueny.rapid.lang.util.collect.ArrayUtil;

/**
 * 缓存基类<br>
 * 建议使用 CacheService<T>
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月9日 下午10:03:07
 *
 */
public abstract class CacheBaseBiz<T> extends BaseBiz {
	/**
	 * 缓存数据执行器
	 *
	 * @author yueny09 <deep_blue_yang@163.com>
	 *
	 * @DATE 2016年11月17日 下午2:15:41
	 */
	public interface ICacheExecutor<T> {
		/**
		 * @return
		 */
		T execute();
	}

	/**
	 * 缓存默认过期时间,15秒
	 */
	protected static final Long DEFAULT_EXPIRE_SECOND = 15L;
	@Autowired
	private RedisTemplate<String, T> defaultRedisTemplate;
	@Autowired
	private RedisTemplate<String, List<T>> defaultRedisTemplateList;

	/**
	 * 缓存对象
	 */
	public final T cache(final ICacheExecutor<T> cacheExecutor, final Long timeout, final TimeUnit unit,
			final Object... args) {
		// TODO redis异常处理
		// .

		final String redisKey = CacheKeyConstant.getPrefis(getClass().getSimpleName(), args);

		T t = defaultRedisTemplate.opsForValue().get(redisKey);
		if (t != null) {
			logger.debug("查询 {} 的键 {}，命中缓存：{}.", getClass().getSimpleName(), args, t);
			return t;
		}

		t = cacheExecutor.execute();

		defaultRedisTemplate.opsForValue().set(redisKey, t);
		defaultRedisTemplate.expire(redisKey, timeout, unit);
		return t;
	}

	/**
	 * 缓存对象
	 */
	public final T cache(final ICacheExecutor<T> cacheExecutor, final Object... args) {
		return cache(cacheExecutor, DEFAULT_EXPIRE_SECOND, TimeUnit.SECONDS, args);
	}

	/**
	 * 缓存对象
	 */
	public final T cache(final Object args, final ICacheExecutor<T> cacheExecutor) {
		return cache(cacheExecutor, ArrayUtil.newArray(args));
	}

	/**
	 * 缓存对象
	 */
	public final T cache(final Object args, final ICacheExecutor<T> cacheExecutor, final Long timeout) {
		return cache(cacheExecutor, timeout, TimeUnit.SECONDS, args);
	}

	/**
	 * 缓存对象删除,会删除对象和列表中的缓存值
	 */
	public final void cacheDelete(final Object... args) {
		final String redisKey = CacheKeyConstant.getPrefis(getClass().getSimpleName(), args);

		try {
			defaultRedisTemplate.delete(redisKey);
			defaultRedisTemplateList.delete(redisKey);
		} catch (final Exception e) {
			logger.error("删除键:" + args + "异常!", e);
		}
	}

	// /**
	// * 缓存对象删除
	// */
	// public final void cacheDelete(final Object[]... argses) {
	// final Set<String> keys = Sets.newHashSet();
	// for (final Object[] args : argses) {
	// // 指定键删除
	// final String redisKey =
	// CacheKeyConstant.getPrefis(getClass().getSimpleName(), args);
	// keys.add(redisKey);
	// }
	//
	// try {
	// defaultRedisTemplate.delete(keys);
	// defaultRedisTemplateList.delete(keys);
	// } catch (final Exception e) {
	// logger.error("删除键:" + argses + "异常!", e);
	// }
	// }

	/**
	 * 缓存对象删除
	 *
	 * @param key
	 *            缓存键值
	 */
	@Deprecated
	public final void cacheFuzzyDelete(final Object someKey) {
		// 匹配键值后缀,模糊删除
		defaultRedisTemplate.delete("*_" + someKey);
		return;
	}

	/**
	 * 缓存列表
	 */
	public final List<T> cacheList(final ICacheExecutor<List<T>> cacheExecutor, final Long timeout, final TimeUnit unit,
			final Object... argses) {
		final String redisKey = CacheKeyConstant.getPrefis(getClass().getSimpleName(), argses);

		List<T> ts = defaultRedisTemplateList.opsForValue().get(redisKey);
		if (CollectionUtils.isNotEmpty(ts)) {
			logger.debug("查询 {} 的键 {}，命中缓存：{}.", getClass().getSimpleName(), argses, ts);
			return ts;
		}

		ts = cacheExecutor.execute();

		defaultRedisTemplateList.opsForValue().set(redisKey, ts);
		defaultRedisTemplateList.expire(redisKey, timeout, unit);
		return ts;
	}

	/**
	 * 缓存列表
	 */
	public final List<T> cacheList(final ICacheExecutor<List<T>> cacheExecutor, final Object... argses) {
		return this.cacheList(cacheExecutor, DEFAULT_EXPIRE_SECOND, TimeUnit.SECONDS, argses);
	}

	/**
	 * 缓存列表
	 */
	public final List<T> cacheList(final Object args, final ICacheExecutor<List<T>> cacheExecutor) {
		return cacheList(cacheExecutor, ArrayUtil.newArray(args));
	}

	/**
	 * 缓存列表
	 */
	public final List<T> cacheList(final Object args, final ICacheExecutor<List<T>> cacheExecutor, final Long timeout) {
		return cacheList(cacheExecutor, timeout, TimeUnit.SECONDS, args);
	}

	/**
	 * 缓存列表
	 */
	public final List<T> cacheList(final Object args, final ICacheExecutor<List<T>> cacheExecutor, final Long timeout,
			final TimeUnit unit) {
		return cacheList(cacheExecutor, timeout, unit, ArrayUtil.newArray(args));
	}

}
