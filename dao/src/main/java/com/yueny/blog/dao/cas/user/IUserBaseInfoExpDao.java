package com.yueny.blog.dao.cas.user;

import com.yueny.blog.entry.cas.user.UserBaseInfoExpEntry;
import com.yueny.kapo.api.IWholeTableQueryDao;
import com.yueny.kapo.core.dao.biz.origin.IOriginDao;

/**
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