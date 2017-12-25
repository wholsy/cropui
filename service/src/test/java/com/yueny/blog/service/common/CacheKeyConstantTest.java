package com.yueny.blog.service.common;

import org.junit.Assert;
import org.junit.Test;

import com.yueny.blog.service.cache.CacheKeyConstant;
import com.yueny.rapid.lang.util.collect.ArrayUtil;

public class CacheKeyConstantTest {
	@Test
	public void testGetPrefis() {
		final String key = CacheKeyConstant.assmebledKeys("simpleName");
		Assert.assertEquals("blog_redis_key_simpleName_", key);

		final String key1 = CacheKeyConstant.assmebledKeys("simpleName",
				ArrayUtil.newArray("findByPreviousBlogId", 66L));
		Assert.assertEquals("blog_redis_key_simpleName_findByPreviousBlogId_66_", key1);
	}
}
