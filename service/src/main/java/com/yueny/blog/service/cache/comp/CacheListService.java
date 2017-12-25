/**
 *
 */
package com.yueny.blog.service.cache.comp;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.yueny.blog.service.cache.CacheDataHandler;

/**
 * 缓存服务
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年11月17日 下午2:14:14
 */
@Component
public class CacheListService<T> extends BaseCacheBiz<List<T>> {
	/**
	 * 缓存列表
	 */
	public final List<T> cache(final CacheDataHandler<List<T>> handler, final Long timeout, final Object... argses) {
		return cache(handler, timeout, TimeUnit.SECONDS, argses);
	}

}
