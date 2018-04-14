/**
 * 
 */
package com.yueny.blog.service;

import com.yueny.blog.common.enums.ProfileType;

/**
 * 环境上线文服务
 * 
 * @author yueny09 <yueny09@163.com>
 * 
 * @DATE 2018年4月6日 下午5:34:27
 *
 */
public interface IProfileService {
	/**
	 * 获取 profile 信息
	 * @return profile 信息
	 */
	String profile();

	/**
	 * 获取 profile 枚举信息
	 * 
	 * @return profile枚举
	 */
	ProfileType profileType();
	
	/**
	 * 获取当前 配置中心 中指定键的  property 信息
	 * @return propertyKey 键
	 */
	String getCfgProperty(String propertyKey);
	
}
