package com.yueny.blog.dao.cas.user;

import com.yueny.blog.entry.cas.user.UserInfoEntry;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年7月17日 下午12:03:16
 *
 */
public interface IUserInfoPassportDao {
	/**
	 * 修改密码(密码加密后)
	 *
	 * @param entry
	 *            用户实体信息
	 *
	 * @return 是否更新成功
	 */
	boolean modifyPassPort(final UserInfoEntry entry);

	/**
	 * @param loginName
	 *            登录用户名
	 * @return 用户密文
	 */
	String queryPwdByLoginName(final String loginName);

}