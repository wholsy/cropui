/**
 *
 * @author <a href="mailto:yueny09@163.com"> 袁洋
 *
 * @date 2015年9月18日 下午5:23:28
 */
package com.yueny.cropui.service.io.strategy;

/**
 * 文件读取结束后的自定义策略操作
 *
 * @author <a href="mailto:yueny09@163.com"> 袁洋
 * @param <T>
 *            存储类型
 * @date 2015年9月18日 下午5:23:28
 */
public interface IDataRenderHandler<T> {
	/**
	 * 文件读取结束的操作
	 *
	 * @param t
	 *            读取的结果
	 */
	void after(final T t);

}
