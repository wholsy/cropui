/**
 *
 */
package com.yueny.blog.service.comp.cache.core;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.yueny.blog.service.comp.cache.CacheDataHandler;

/**
 * 缓存服务
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年11月17日 下午2:14:14
 */
@Component
@Deprecated
public class CacheObjectService<T> extends BaseCacheBiz<T> {

	/**
	 * 缓存对象
	 */
	@Deprecated
	public final T cache(final CacheDataHandler<T> handler, final Long timeout, final Object... args) {
		return cache(handler, timeout, TimeUnit.SECONDS, args);
	}

}
