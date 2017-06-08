package com.yueny.blog.service.cas.user;

import com.yueny.rapid.lang.exception.invalid.InvalidException;

/**
 * 用户密码认证服务
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年11月30日 下午4:49:29
 *
 */
public interface IUserPassportService {
	/**
	 * 是否存在登录和密码用户<br>
	 *
	 * @param loginName
	 *            登录用户名
	 * @param cryptPassword
	 *            密文登录密码
	 * @return 是否存在用户
	 */
	boolean getMatch(final String loginName, final String cryptPassword) throws InvalidException;

	/**
	 * 更改用户加密后的密码
	 *
	 * @param uid
	 *            用户uid
	 * @param passport
	 *            表单密码
	 *
	 * @return 是否更新成功
	 */
	boolean modifyPassPort(final String uid, final String passport) throws InvalidException;

}
