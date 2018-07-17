// package com.yueny.blog.service.comp.cache;
//
// import java.io.Serializable;
// import java.util.concurrent.Callable;
//
// import org.apache.commons.lang3.SerializationUtils;
// import org.springframework.cache.Cache;
// import org.springframework.cache.support.SimpleValueWrapper;
// import org.springframework.dao.DataAccessException;
// import org.springframework.data.redis.connection.RedisConnection;
// import org.springframework.data.redis.core.RedisCallback;
// import org.springframework.data.redis.core.RedisTemplate;
//
// import lombok.Setter;
//
/// **
// * Cache接口实现类
// *
// * @author yueny09 <deep_blue_yang@163.com>
// *
// * @DATE 2018年7月16日 下午9:38:42
// * @since
// */
// @Deprecated
// public class SpringRedisCache implements Cache {
// @Setter
// private String name;
// @Setter
// private RedisTemplate<String, Object> redisTemplate;
//
// @Override
// public void clear() {
// // 緩存清理
// redisTemplate.execute(new RedisCallback<String>() {
// @Override
// public String doInRedis(RedisConnection connection) throws
// DataAccessException {
// connection.flushDb();
// return "ok";
// }
// });
// }
//
// @Override
// public void evict(Object key) {
// // 根据 key 来删除缓存中的一条记录，此空间支持模糊匹配
// final String keyf = key.toString();
// redisTemplate.execute(new RedisCallback<Long>() {
// @Override
// public Long doInRedis(RedisConnection connection) throws DataAccessException
// {
// return connection.del(keyf.getBytes());
// }
// });
// }
//
// @Override
// public ValueWrapper get(Object key) {
// // 缓存获取
// final String keyf = key.toString();
// Object object = null;
// object = redisTemplate.execute(new RedisCallback<Object>() {
// @Override
// public Object doInRedis(RedisConnection connection) throws
// DataAccessException {
// final byte[] key = keyf.getBytes();
// final byte[] value = connection.get(key);
// if (value == null) {
// System.out.println("------缓存不存在-------");
// return null;
// }
// return SerializationUtils.deserialize(value);
// }
// });
// final ValueWrapper obj = (object != null ? new SimpleValueWrapper(object) :
// null);
// return obj;
// }
//
// @Override
// public <T> T get(Object key, Callable<T> valueLoader) {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public <T> T get(Object key, Class<T> type) {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public String getName() {
// return this.name;
// }
//
// @Override
// public Object getNativeCache() {
// return this.redisTemplate;
// }
//
// @Override
// public void put(Object key, Object value) {
// // 加入缓存
// final String keyString = key.toString();
// final Object valuef = value;
// final long liveTime = 86400;
// redisTemplate.execute(new RedisCallback<Long>() {
// @Override
// public Long doInRedis(RedisConnection connection) throws DataAccessException
// {
// final byte[] keyb = keyString.getBytes();
// final byte[] valueb = SerializationUtils.serialize((Serializable) valuef);
// connection.set(keyb, valueb);
// if (liveTime > 0) {
// connection.expire(keyb, liveTime);
// }
// return 1L;
// }
// });
// }
//
// @Override
// public ValueWrapper putIfAbsent(Object key, Object value) {
// // TODO Auto-generated method stub
// return null;
// }
//
// }
