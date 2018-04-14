package com.yueny.blog.service;

import com.yueny.blog.bo.console.user.UserInfoBo;

/**
 * 用户服务
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年11月30日 下午4:49:29
 *
 */
public interface IUserInfoService {
	/**
	 * @param loginName
	 *            登录用户名
	 * @return 登录用户信息
	 */
	UserInfoBo queryByLoginName(final String loginName);
}
