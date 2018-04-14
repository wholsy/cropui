/*
 * (C) 2007-2012 Alibaba Group Holding Limited.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 * Authors:
 *   leiwen <chrisredfield1985@126.com> , boyan <killme2008@gmail.com>
 */
package com.yueny.blog.service.common.impl;

import com.yueny.blog.service.BaseBiz;
import com.yueny.blog.service.common.IPasswdService;
import com.yueny.superclub.util.crypt.core.group.MD5Util;
import org.springframework.stereotype.Service;


/**
 * 密码服务 
 * 
 * @author yueny09 <yueny09@163.com>
 * 
 * @DATE 2018年4月6日 下午5:22:24
 *
 */
@Service
public class PasswdServiceImpl extends BaseBiz implements IPasswdService {
	/* (non-Javadoc)
	 * @see com.taobao.diamond.server.service.IPasswdService#getEncodeData(java.lang.String, java.lang.String)
	 */
	@Override
	public String getEncodeData(String passwordVal, String salt) {
		return MD5Util.encode(passwordVal, salt);
	}

//	public boolean changePassword(String userName, String password) {
//		// 处理明文密码得到密文
//		String mpwd = getEncodeData(password, "");
//
//		int rs = userService.updatePassword(userName, mpwd);
//
//		return rs == 1;
//	}

}
