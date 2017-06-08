package com.yueny.blog.dao.cas.user.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yueny.blog.dao.cas.user.IUserInfoDao;
import com.yueny.blog.dao.cas.user.IUserInfoPassportDao;
import com.yueny.blog.entry.cas.user.UserInfoEntry;
import com.yueny.kapo.api.annnotation.DbSchemaType;
import com.yueny.kapo.api.annnotation.TableSeg;
import com.yueny.kapo.core.condition.builder.UpdateBuilder;
import com.yueny.kapo.core.dao.biz.origin.AbstractOrginDao;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年7月17日 下午12:05:47
 *
 */
@Repository
@DbSchemaType(value = "BLOG")
@TableSeg(tableName = "USER_INFO")
class UserInfoDaoImpl extends AbstractOrginDao<UserInfoEntry> implements IUserInfoDao, IUserInfoPassportDao {

	@Override
	public boolean modifyPassPort(final UserInfoEntry entry) {
		final UpdateBuilder builder = UpdateBuilder.builder().set("PASSPORT", entry.getPassport())
				.where("UID", entry.getUid()).where("LOGIN_NAME", entry.getLoginName()).build();

		return super.updateByColumns(builder) == 1;
	}

	@Override
	public UserInfoEntry queryByLoginName(final String loginName) {
		final Map<String, Object> whereColumns = new HashMap<String, Object>();
		whereColumns.put("LOGIN_NAME", loginName);
		return super.queryByColumns(whereColumns);
	}

	@Override
	public UserInfoEntry queryByUid(final String uid) {
		final Map<String, Object> whereColumns = new HashMap<String, Object>();
		whereColumns.put("UID", uid);
		return super.queryByColumns(whereColumns);
	}

	@Override
	public String queryPwdByLoginName(final String loginName) {
		final UserInfoEntry entry = queryByLoginName(loginName);
		if (entry == null) {
			return null;
		}

		return entry.getPassport();
	}

}