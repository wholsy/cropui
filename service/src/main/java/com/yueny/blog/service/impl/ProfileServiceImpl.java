/*
 * (C) 2007-2012 Alibaba Group Holding Limited.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 * Authors:
 *   leiwen <chrisredfield1985@126.com> , boyan <killme2008@gmail.com>
 */
package com.yueny.blog.service.impl;

import com.taobao.diamond.extend.DynamicProperties;
import com.yueny.blog.common.enums.ProfileType;
import com.yueny.blog.service.IProfileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * 环境上线文服务
 * 
 * @author yueny09 <yueny09@163.com>
 * 
 * @DATE 2018年4月6日 下午5:22:24
 *
 */
@Service
public class ProfileServiceImpl implements IProfileService {
	@Value("${app.profile.env}")
	private String env;
	
	@Override
	public String profile() {
		return env;
	}

	@Override
	public ProfileType profileType() {
		String profile = profile();
		
		for (ProfileType profileType : ProfileType.values()) {
			if(StringUtils.equalsIgnoreCase(profileType.name(), profile)){
				return profileType;
			}
		}
		
		return ProfileType.LOCAL;
	}
	
	@Override
	public String getCfgProperty(String propertyKey) {
		String confVal= DynamicProperties.staticProperties.getProperty(propertyKey);
		
		return confVal;
	}

}
