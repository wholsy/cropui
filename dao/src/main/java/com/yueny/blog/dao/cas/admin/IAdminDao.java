package com.yueny.blog.dao.cas.admin;

import java.util.List;

import com.yueny.blog.entry.cas.admin.AdminEntry;
import com.yueny.kapo.core.dao.biz.origin.IOriginDao;

/**
 * 后台系统管理员
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年11月30日 下午4:18:27
 *
 */
public interface IAdminDao extends IOriginDao<AdminEntry> {
	/**
	 * @param loginName
	 *            登录用户名
	 * @return 登录用户信息
	 */
	AdminEntry queryByLoginName(final String loginName);

	/**
	 * 获取系统编号内的管理员用户
	 */
	List<AdminEntry> queryBySystemCode(final String systemCode);
}
