package com.yueny.blog.service.comp.cache;

import java.util.Collection;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.yueny.superclub.api.constant.Constants;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月9日 下午9:09:21
 *
 */
public class CacheKeyConstant {
	/**
	 * redis常规前缀标识
	 */
	public static final String REDIS_PREFIX_KEY = "blog_redis_key_";

	/**
	 * key组装
	 */
	public static String assmebledKeys(final List<Object> keys) {
		return getPrefis(keys);
	}

	/**
	 * key组装
	 */
	public static String assmebledKeys(final Object... keys) {
		return getPrefis(keys);
	}

	/**
	 * 获取缓存标识
	 *
	 * @param key
	 *            附加值
	 */
	private static String getPrefis(final Object... keyArgs) {
		final StringBuilder ks = new StringBuilder();
		ks.append(REDIS_PREFIX_KEY);

		for (final Object key : keyArgs) {
			if (key instanceof java.util.Collection) {
				ks.append(Joiner.on(Constants.UNDERLINE).skipNulls().join(Lists.newArrayList(((Collection<?>) key))));
				ks.append(Constants.UNDERLINE);
				continue;
			}

			if (key instanceof Object[]) {
				ks.append(Joiner.on(Constants.UNDERLINE).skipNulls().join(Lists.newArrayList(((Object[]) key))));
				ks.append(Constants.UNDERLINE);
				continue;
			}

			ks.append(key);
			ks.append(Constants.UNDERLINE);
		}

		return ks.toString();
	}

}
