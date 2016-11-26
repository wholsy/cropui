package com.yueny.blog.service;

import java.util.concurrent.TimeUnit;

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
public abstract class CacheBaseBiz<L> extends BaseBiz {
	/**
	 * 缓存数据执行器
	 *
	 * @author yueny09 <deep_blue_yang@163.com>
	 *
	 * @DATE 2016年11月17日 下午2:15:41
	 */
	public interface ICacheExecutor<L> {
		/**
		 * @return Objs or collections
		 */
		L execute();
	}

	/**
	 * 缓存默认过期时间,15秒
	 */
	protected static final Long DEFAULT_EXPIRE_SECOND = 15L;
	@Autowired
	private RedisTemplate<String, L> redisTemplate;

	/**
	 * 缓存对象
	 */
	public final L cache(final ICacheExecutor<L> cacheExecutor, final Long timeout, final TimeUnit unit,
			final Object... args) {
		// TODO redis异常处理
		// .

		// TODO getClass() is 'CacheListService'
		final String redisKey = CacheKeyConstant.getPrefis(getClass().getSimpleName(), args);

		L t = redisTemplate.opsForValue().get(redisKey);
		if (t != null) {
			logger.debug("查询 {} 的键 {}，命中缓存：{}.", getClass().getSimpleName(), args, t);
			return t;
		}

		t = cacheExecutor.execute();

		redisTemplate.opsForValue().set(redisKey, t);
		redisTemplate.expire(redisKey, timeout, unit);
		return t;
	}

	/**
	 * 缓存对象
	 */
	public final L cache(final ICacheExecutor<L> cacheExecutor, final Object... args) {
		return cache(cacheExecutor, DEFAULT_EXPIRE_SECOND, TimeUnit.SECONDS, args);
	}

	/**
	 * 缓存对象
	 */
	public final L cache(final Object args, final ICacheExecutor<L> cacheExecutor) {
		return cache(cacheExecutor, ArrayUtil.newArray(args));
	}

	/**
	 * 缓存对象
	 */
	public final L cache(final Object args, final ICacheExecutor<L> cacheExecutor, final Long timeout) {
		return cache(cacheExecutor, timeout, TimeUnit.SECONDS, args);
	}

	/**
	 * 缓存对象
	 */
	public final L cache(final Object args, final ICacheExecutor<L> cacheExecutor, final Long timeout,
			final TimeUnit unit) {
		return cache(cacheExecutor, timeout, TimeUnit.SECONDS, ArrayUtil.newArray(args));
	}

	/**
	 * 缓存对象删除,会删除对象和列表中的缓存值
	 */
	public final void cacheDelete(final Object... args) {
		final String redisKey = CacheKeyConstant.getPrefis(getClass().getSimpleName(), args);

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
