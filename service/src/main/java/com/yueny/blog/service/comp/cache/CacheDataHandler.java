/**
 *
 */
package com.yueny.blog.service.comp.cache;

/**
 * 缓存数据执行器
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年11月17日 下午2:15:41
 * @since 1.5.3
 */
public interface CacheDataHandler<D> {// D extends ICacheWidget
	/**
	 * @return
	 */
	D caller();
}
