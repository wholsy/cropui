package com.yueny.blog.service.common;

import org.junit.Assert;
import org.junit.Test;

import com.yueny.rapid.lang.util.collect.ArrayUtil;

public class CacheKeyConstantTest {
	@Test
	public void testGetPrefis() {
		final String key = CacheKeyConstant.getPrefis("simpleName");
		Assert.assertEquals("blog_redis_key_simpleName_", key);

		final String key1 = CacheKeyConstant.getPrefis("simpleName", ArrayUtil.newArray("findByPreviousBlogId", 66L));
		Assert.assertEquals("blog_redis_key_simpleName_findByPreviousBlogId_66_", key1);
	}
}
