package com.yueny.cropui.bo.enums;

import org.apache.commons.lang3.StringUtils;

import com.yueny.superclub.api.enums.core.IEnumType;
import com.yueny.superclub.util.redis.cache.CacheArgumentHelper;

/**
 * 系统相关 Cache 缓存key键枚举
 *
 * @author <a href="mailto:yueny09@126.com"> 袁洋 2014年11月27日
 */
public enum SystemCacheKeyType implements IEnumType {
	/**
	 * DEMO<br>
	 *
	 * @expireSeconds 默认值,一天,<code>CacheArgument.DAY_SECONDS = 86400</code>
	 */
	DEMO_KEY("redis.demo.key", "DEMO 缓存键", 86400),
	/**
	 * 数据库样式dbStyle redisCache 缓存键<br>
	 *
	 * @expireSeconds 一周,86400*7
	 */
	SYSTEM_DB_STYLE_KEY("redis.system.db.style.key",
			"数据库样式dbStyle redisCache 缓存键", 86400 * 7),
	/** */
	;

	/** Cache 缓存帮助对象 */
	private CacheArgumentHelper cacheArgumentHelper;

	/**
	 * @param cacheArgument
	 *            RedisCache 缓存帮助对象
	 */
	private SystemCacheKeyType(final CacheArgumentHelper cacheArgumentHelper) {
		this.cacheArgumentHelper = cacheArgumentHelper;
	}

	/**
	 * @param cacheKey
	 *            缓存key
	 * @param cacheKeyDesc
	 *            缓存key描述
	 * @param expireSeconds
	 *            键的缓存生存周期TTL
	 */
	private SystemCacheKeyType(final String cacheKey,
			final String cacheKeyDesc, final Integer expireSeconds) {
		this.cacheArgumentHelper = new CacheArgumentHelper(cacheKey,
				cacheKeyDesc, expireSeconds);
	}

	/**
	 * 如果枚举中的缓存key存在占位符的,且补充内容非空,往占位符中追加补充内容<br>
	 *
	 * @param placeholder
	 *            占位符的值
	 * @return 缓存key的格式化
	 */
	public String formatCacheKey(final String placeholder) {
		if (StringUtils.isNotEmpty(placeholder) && getCacheKey().contains("%s")) {
			return String.format(getCacheKey(), placeholder);
		}
		return getCacheKey();
	}

	/**
	 * @return Cache 缓存帮助对象
	 */
	public CacheArgumentHelper getCacheArgumentHelper() {
		return this.cacheArgumentHelper;
	}

	/**
	 * @return 缓存key
	 */
	public String getCacheKey() {
		return this.cacheArgumentHelper.getCacheKey();
	}

	/**
	 * @return 键的缓存生存周期TTL
	 */
	public Integer getExpireSeconds() {
		return this.cacheArgumentHelper.getExpireSeconds();
	}

}
