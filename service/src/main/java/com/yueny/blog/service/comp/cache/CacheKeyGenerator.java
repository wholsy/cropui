// package com.yueny.blog.service.comp.cache;
//
// import java.lang.reflect.Array;
// import java.lang.reflect.Method;
// import java.nio.charset.Charset;
//
// import org.springframework.cache.interceptor.KeyGenerator;
// import org.springframework.stereotype.Component;
// import org.springframework.util.ClassUtils;
//
// import com.google.common.hash.Hashing;
//
// import lombok.extern.slf4j.Slf4j;
//
// @Slf4j
// @Component
// public class CacheKeyGenerator implements KeyGenerator {
// // custom cache key
// public static final int NO_PARAM_KEY = 0;
// public static final int NULL_PARAM_KEY = 53;
//
// @Override
// public Object generate(Object target, Method method, Object... params) {
// // 为给定的方法及其参数生成一个键
// final StringBuilder key = new StringBuilder();
// key.append(target.getClass().getSimpleName())// 类名
// .append(".").append(method.getName())// 方法名
// .append(":");
//
// if (params.length == 0) {
// return key.append(NO_PARAM_KEY).toString();
// }
//
// // 参数
// for (final Object param : params) {
// if (param == null) {
// log.warn("input null param for Spring cache, use default key={}",
// NULL_PARAM_KEY);
// key.append(NULL_PARAM_KEY);
// } else if (ClassUtils.isPrimitiveArray(param.getClass())) {
// final int length = Array.getLength(param);
// for (int i = 0; i < length; i++) {
// key.append(Array.get(param, i));
// key.append(',');
// }
// } else if (ClassUtils.isPrimitiveOrWrapper(param.getClass()) || param
// instanceof String) {
// key.append(param);
// } else {
// log.warn("Using an object as a cache key may lead to unexpected results. "
// + "Either use @Cacheable(key=..) or implement CacheKey. Method is " +
// target.getClass() + "#"
// + method.getName());
// key.append(param.hashCode());
// }
// key.append('-');
// }
//
// final String finalKey = key.toString();
// final long cacheKeyHash = Hashing.murmur3_128().hashString(finalKey,
// Charset.defaultCharset()).asLong();
// log.debug("using cache key={} hashCode={}", finalKey, cacheKeyHash);
// return key.toString();
// }
//
// }
