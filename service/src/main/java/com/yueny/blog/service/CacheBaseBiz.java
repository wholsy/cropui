package com.yueny.blog.service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.google.common.collect.Sets;
import com.yueny.blog.service.common.CacheKeyConstant;
import com.yueny.rapid.lang.util.collect.ArrayUtil;

/**
 * 缓存基类
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月9日 下午10:03:07
 *
 */
public abstract class CacheBaseBiz<T> extends BaseBiz {
	public interface ICacheExecutor<T> {
		T execute();
	}

	/**
	 * 缓存默认过期时间,15秒
	 */
	protected static final int DEFAULT_EXPIRE_SECOND = 15;
	@Autowired
	private RedisTemplate<String, T> defaultRedisTemplate;
	@Autowired
	private RedisTemplate<String, List<T>> defaultRedisTemplateList;

	/**
	 * 缓存对象
	 */
	public final T cache(final Object args, final ICacheExecutor<T> cacheExecutor) {
		return cache(ArrayUtil.newArray(args), cacheExecutor);
	}

	/**
	 * 缓存对象
	 */
	public final T cache(final Object[] args, final ICacheExecutor<T> cacheExecutor) {
		return cache(args, cacheExecutor, DEFAULT_EXPIRE_SECOND, TimeUnit.SECONDS);
	}

	/**
	 * 缓存对象
	 */
	public final T cache(final Object[] args, final ICacheExecutor<T> cacheExecutor, final long timeout,
			final TimeUnit unit) {
		// TODO redis异常处理
		// .

		final String redisKey = CacheKeyConstant.getPrefis(getClass().getSimpleName(), args);

		T t = defaultRedisTemplate.opsForValue().get(redisKey);
		if (t != null) {
			return t;
		}

		t = cacheExecutor.execute();

		defaultRedisTemplate.opsForValue().set(redisKey, t);
		defaultRedisTemplate.expire(redisKey, timeout, unit);
		return t;
	}

	/**
	 * 缓存对象删除
	 */
	public final void cacheDelete(final Object[]... argses) {
		final Set<String> keys = Sets.newHashSet();
		for (final Object[] args : argses) {
			// 指定键删除
			final String redisKey = CacheKeyConstant.getPrefis(getClass().getSimpleName(), args);
			keys.add(redisKey);
		}

		try {
			defaultRedisTemplate.delete(keys);
			defaultRedisTemplateList.delete(keys);
		} catch (final Exception e) {
			logger.error("删除键:" + argses + "异常!", e);
		}
	}

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
	public final List<T> cacheList(final Object args, final ICacheExecutor<List<T>> cacheExecutor) {
		return cacheList(ArrayUtil.newArray(args), cacheExecutor);
	}

	/**
	 * 缓存列表
	 */
	public final List<T> cacheList(final Object[] args, final ICacheExecutor<List<T>> cacheExecutor) {
		return cacheList(args, cacheExecutor, DEFAULT_EXPIRE_SECOND, TimeUnit.SECONDS);
	}

	/**
	 * 缓存列表
	 */
	public final List<T> cacheList(final Object[] args, final ICacheExecutor<List<T>> cacheExecutor, final long timeout,
			final TimeUnit unit) {
		final String redisKey = CacheKeyConstant.getPrefis(getClass().getSimpleName(), args);

		List<T> ts = defaultRedisTemplateList.opsForValue().get(redisKey);
		if (CollectionUtils.isNotEmpty(ts)) {
			return ts;
		}

		ts = cacheExecutor.execute();

		defaultRedisTemplateList.opsForValue().set(redisKey, ts);
		defaultRedisTemplateList.expire(redisKey, timeout, unit);
		return ts;
	}

}
