package com.yueny.blog.service.admin.manager;

import com.yueny.rapid.lang.exception.invalid.InvalidException;

/**
 * 登录管理
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2017年6月8日 下午1:27:53
 * @since
 */
public interface ILoginManager {
	/**
	 * @param loginName
	 *            登录用户名
	 * @param cryptPassword
	 *            明文登录密码
	 * @return 登陆结果
	 */
	boolean login(final String loginName, final String originalPassword) throws InvalidException;

}
