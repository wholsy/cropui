package com.yueny.blog.service.comp.cache.core;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.yueny.blog.service.BaseBiz;
import com.yueny.blog.service.comp.cache.CacheDataHandler;
import com.yueny.blog.service.comp.cache.CacheKeyConstant;
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
public abstract class BaseCacheBiz<L> extends BaseBiz {
	/**
	 * 缓存默认过期时间,15秒
	 */
	protected static final Long DEFAULT_EXPIRE_SECOND = 15L;
	@Autowired
	private RedisTemplate<String, L> redisTemplate;

	/**
	 * 缓存对象, null不进行缓存， 发生异常不进行捕获， 不进行缓存
	 */
	public final L cache(final CacheDataHandler<L> cacheExecutor, final Long timeout, final TimeUnit unit,
			final Object... args) {
		// TODO redis异常处理
		// .

		// getClass() is 'CacheListService'
		final String redisKey = CacheKeyConstant.assmebledKeys(getClass().getSimpleName(), args);

		L t = redisTemplate.opsForValue().get(redisKey);
		if (t != null) {
			logger.trace("查询 {} 的键 {}，命中缓存：{}.", getClass().getSimpleName(), args, t);
			return t;
		}

		t = cacheExecutor.caller();

		if (t == null) {
			return t;
		}

		redisTemplate.opsForValue().set(redisKey, t);
		redisTemplate.expire(redisKey, timeout, unit);
		return t;
	}

	/**
	 * 缓存对象, null不进行缓存， 发生异常不进行捕获， 不进行缓存
	 */
	public final L cache(final CacheDataHandler<L> cacheExecutor, final Object... args) {
		return cache(cacheExecutor, DEFAULT_EXPIRE_SECOND, TimeUnit.SECONDS, args);
	}

	/**
	 * 缓存对象, null不进行缓存， 发生异常不进行捕获， 不进行缓存
	 */
	public final L cache(final Object args, final CacheDataHandler<L> cacheExecutor) {
		return cache(cacheExecutor, ArrayUtil.newArray(args));
	}

	/**
	 * 缓存对象, null不进行缓存， 发生异常不进行捕获， 不进行缓存
	 *
	 * @param args
	 *            缓存键值串,缓存key, 调用此接口,请确保该key在全局唯一
	 * @param handler
	 * @param timeout
	 *            失效时间,秒 缓存对象
	 */
	public final L cache(final Object args, final CacheDataHandler<L> cacheExecutor, final Long timeout) {
		return cache(cacheExecutor, timeout, TimeUnit.SECONDS, args);
	}

	/**
	 * 缓存对象, null不进行缓存， 发生异常不进行捕获， 不进行缓存
	 */
	public final L cache(final Object args, final CacheDataHandler<L> cacheExecutor, final Long timeout,
			final TimeUnit unit) {
		return cache(cacheExecutor, timeout, TimeUnit.SECONDS, ArrayUtil.newArray(args));
	}

	/**
	 * 缓存对象删除,会删除对象和列表中的缓存值
	 */
	public final void cacheDelete(final Object... args) {
		final String redisKey = CacheKeyConstant.assmebledKeys(getClass().getSimpleName(), args);

		try {
			redisTemplate.delete(redisKey);
		} catch (final Exception e) {
			logger.error("删除键:" + args + "异常!", e);
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
		redisTemplate.delete("*_" + someKey);
		return;
	}

}
