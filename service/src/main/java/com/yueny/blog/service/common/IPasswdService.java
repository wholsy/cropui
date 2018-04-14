/*
 * (C) 2007-2012 Alibaba Group Holding Limited.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 * Authors:
 *   leiwen <chrisredfield1985@126.com> , boyan <killme2008@gmail.com>
 */
package com.yueny.blog.service.common;

/**
 * 密码服务 
 * 
 * @author yueny09 <yueny09@163.com>
 * 
 * @DATE 2018年4月6日 下午5:20:25
 *
 */
public interface IPasswdService {
	/**
	 * 根据欲加密明文数据和盐获取密文
	 * 
	 * @param passwordVal
	 *            欲加密明文数据
	 * @param salt
	 *            盐值
	 */
	String getEncodeData(String passwordVal, String salt);

}
