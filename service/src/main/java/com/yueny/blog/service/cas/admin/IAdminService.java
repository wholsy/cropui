package com.yueny.blog.service.cas.admin;

import com.yueny.blog.bo.cas.admin.AdminBo;

/**
 * 用户密码认证服务
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年11月30日 下午4:49:29
 *
 */
public interface IAdminService {
	/**
	 * @param loginName
	 *            登录用户名
	 * @return 登录用户信息
	 */
	AdminBo queryByLoginName(final String loginName);
}
