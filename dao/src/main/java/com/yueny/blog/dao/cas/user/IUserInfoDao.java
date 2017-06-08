package com.yueny.blog.dao.cas.user;

import com.yueny.blog.entry.cas.user.UserInfoEntry;
import com.yueny.kapo.api.IWholeTableQueryDao;
import com.yueny.kapo.core.dao.biz.origin.IOriginDao;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年7月17日 下午12:03:16
 *
 */
public interface IUserInfoDao extends IOriginDao<UserInfoEntry>, IWholeTableQueryDao<UserInfoEntry> {
	/**
	 * @param loginName
	 *            登录用户名
	 */
	UserInfoEntry queryByLoginName(final String loginName);

	/**
	 * @param uid
	 *            uid
	 */
	UserInfoEntry queryByUid(final String uid);

}