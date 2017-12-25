package com.yueny.blog.common.cache;

import java.lang.reflect.Method;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月10日 下午6:25:00
 *
 */
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {
	@Bean
	public KeyGenerator customKeyGenerator() {
		// 提供一个不同的默认键生成器，需要实现org.springframework.cache.KeyGenerator接口。
		// 一旦配置，这个生成器将会用于每个没有特殊指定它自己的键生成策略的声明中
		return new KeyGenerator() {
			@Override
			public Object generate(final Object clazz, final Method method, final Object... objects) {
				final StringBuilder sb = new StringBuilder();
				// eg:
				// com.yueny.blog.service.manage.impl.ArticleQueryServiceImpl@639aea8b
				sb.append(clazz.getClass().getName());
				// eg: public abstract
				// com.yueny.blog.bo.model.document.OwenerTagsData
				// com.yueny.blog.service.manage.IArticleQueryService.getOwenerTag(java.lang.String)
				// throws
				// com.yueny.rapid.lang.exception.DataVerifyAnomalyException
				sb.append(method.getName());
				// 参数,eg: [yuanyang]
				for (final Object obj : objects) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}

}
