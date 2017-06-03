package com.yueny.blog.service.cas.admin;

/**
 * 用户密码认证服务
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年11月30日 下午4:49:29
 *
 */
public interface IAdminPassportService {
	/**
	 * 是否存在登录和密码用户<br>
	 *
	 * @param loginName
	 *            登录用户名
	 * @param originalPassword
	 *            登录密码
	 * @return 是否存在用户
	 */
	boolean getMatch(final String loginName, final String originalPassword);

	/**
	 * 更改用户加密后的密码
	 *
	 * @param loginUserId
	 *            用户主键
	 * @param md5Passport
	 *            md5密码
	 *
	 * @return 是否更新成功
	 */
	boolean modifyPassPort(final Long loginUserId, final String md5Passport);

}
