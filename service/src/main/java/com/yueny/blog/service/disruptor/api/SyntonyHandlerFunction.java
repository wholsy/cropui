/**
*
*/
package com.yueny.blog.service.disruptor.api;

/**
* 执行函数接口
*
* @author yueny09 <deep_blue_yang@163.com>
*
* @DATE 2018年1月11日 上午1:26:07
*
*/
public interface SyntonyHandlerFunction<R> {
	/**
	 * 执行函数
	 */
	R execute();

}
