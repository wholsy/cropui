package com.yueny.blog.dao.console.user;

import com.yueny.blog.entry.console.user.UserBaseInfoExpEntry;
import com.yueny.kapo.api.IWholeTableQueryDao;
import com.yueny.kapo.core.dao.biz.origin.IOriginDao;

/**
 * 用户扩展信息
 * 
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年7月17日 下午12:03:16
 *
 */
public interface IUserBaseInfoExpDao
		extends IOriginDao<UserBaseInfoExpEntry>, IWholeTableQueryDao<UserBaseInfoExpEntry> {
	/**
	 * @param uid
	 *            uid
	 */
	UserBaseInfoExpEntry queryByUid(final String uid);

}