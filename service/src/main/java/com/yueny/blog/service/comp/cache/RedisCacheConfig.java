package com.yueny.blog.service.comp.cache;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.util.ClassUtils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;

import lombok.extern.slf4j.Slf4j;

/**
 * 缓存适用于读多写少的场合，查询时缓存命中率很低、写操作很频繁等场景不适宜用缓存。
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年7月16日 下午11:17:18
 * @since
 */
@Configuration
@Slf4j
public class RedisCacheConfig extends CachingConfigurerSupport {
	// custom cache key
	public static final int NO_PARAM_KEY = 0;
	public static final int NULL_PARAM_KEY = 53;

	@Bean
	public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
		final RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		// Number of seconds before expiration. Defaults to unlimited (0)
		// 设置默认的过期时间(以秒为单位)， 默认5 min
		cacheManager.setDefaultExpiration(3000); // Sets the default expire time
													// (in seconds)

		// 启动时加载远程缓存
		cacheManager.setLoadRemoteCachesOnStartup(true);
		// 是否使用前缀生成器
		cacheManager.setUsePrefix(true);
		cacheManager.setCacheNames(Arrays.asList("content"));

		return cacheManager;
	}

	/**
	 * RedisCache异常自定义处理
	 *
	 * @see org.springframework.cache.annotation.CachingConfigurerSupport#errorHandler()
	 */
	@Bean
	@Override
	public CacheErrorHandler errorHandler() {
		final CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
			@Override
			public void handleCacheClearError(RuntimeException e, Cache cache) {

			}

			@Override
			public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {

			}

			@Override
			public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
				System.out.println(key);
			}

			@Override
			public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
				System.out.println(value);
			}
		};
		return cacheErrorHandler;
	}

	/**
	 * 代替配置 @see service-redis.xml
	 */
	@Bean(name = "stringRedisTemplate")
	public RedisTemplate<String, String> stringRedisTemplate(RedisConnectionFactory jedisConnectionFactory) {
		final StringRedisTemplate template = new StringRedisTemplate(jedisConnectionFactory);

		final Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		final ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();

		return template;
	}

	@Bean
	public KeyGenerator wiselyKeyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				// 为给定的方法及其参数生成一个键
				final StringBuilder key = new StringBuilder();
				key.append(target.getClass().getSimpleName())// 类名
						.append(".").append(method.getName())// 方法名
						.append(":");

				if (params.length == 0) {
					return key.append(NO_PARAM_KEY).toString();
				}

				// 参数
				for (final Object param : params) {
					if (param == null) {
						log.warn("input null param for Spring cache, use default key={}", NULL_PARAM_KEY);
						key.append(NULL_PARAM_KEY);
					} else if (ClassUtils.isPrimitiveArray(param.getClass())) {
						final int length = Array.getLength(param);
						for (int i = 0; i < length; i++) {
							key.append(Array.get(param, i));
							key.append(',');
						}
					} else if (ClassUtils.isPrimitiveOrWrapper(param.getClass()) || param instanceof String) {
						key.append(param);
					} else {
						log.warn("Using an object as a cache key may lead to unexpected results. "
								+ "Either use @Cacheable(key=..) or implement CacheKey. Method is " + target.getClass()
								+ "#" + method.getName());
						key.append(param.hashCode());
					}
					key.append('-');
				}

				final String finalKey = key.toString();
				final long cacheKeyHash = Hashing.murmur3_128().hashString(finalKey, Charset.defaultCharset()).asLong();
				log.debug("using cache key={} hashCode={}", finalKey, cacheKeyHash);
				return key.toString();
			}
		};

	}

}
