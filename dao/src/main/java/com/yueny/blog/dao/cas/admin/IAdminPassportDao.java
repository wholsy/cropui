package com.yueny.blog.dao.cas.admin;

import com.yueny.blog.entry.cas.admin.AdminEntry;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年11月30日 下午4:23:04
 *
 */
public interface IAdminPassportDao {
	/**
	 * 修改密码(密码加密后)
	 *
	 * @param entry
	 *            用户实体信息
	 *
	 * @return 是否更新成功
	 */
	boolean modifyPassPort(final AdminEntry entry);

	/**
	 * @param loginName
	 *            登录用户名
	 * @return 用户密文
	 */
	String queryPwdByLoginName(final String loginName);
}
