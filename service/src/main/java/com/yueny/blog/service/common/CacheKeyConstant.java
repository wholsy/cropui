package com.yueny.blog.service.common;

import java.util.Collection;

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
	 * 获取缓存标识
	 *
	 * @param key
	 *            附加值
	 */
	public static String getPrefis(final Object... keyArgs) {
		final StringBuilder sb = new StringBuilder();
		sb.append(REDIS_PREFIX_KEY);

		for (final Object key : keyArgs) {
			if (key instanceof java.util.Collection) {
				sb.append(Joiner.on(Constants.UNDERLINE).join(Lists.newArrayList(((Collection<?>) key))));
				sb.append(Constants.UNDERLINE);
				continue;
			}

			if (key instanceof Object[]) {
				sb.append(Joiner.on(Constants.UNDERLINE).join(Lists.newArrayList(((Object[]) key))));
				sb.append(Constants.UNDERLINE);
				continue;
			}

			sb.append(key);
			sb.append(Constants.UNDERLINE);
		}

		return sb.toString();
	}

}
