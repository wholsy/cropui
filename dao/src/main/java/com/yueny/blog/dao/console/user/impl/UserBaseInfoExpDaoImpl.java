package com.yueny.blog.dao.console.user.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yueny.blog.dao.console.user.IUserBaseInfoExpDao;
import com.yueny.blog.entry.console.user.UserBaseInfoExpEntry;
import com.yueny.kapo.api.annnotation.DbSchemaType;
import com.yueny.kapo.api.annnotation.TableSeg;
import com.yueny.kapo.core.dao.biz.origin.AbstractOrginDao;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年7月17日 下午12:05:47
 *
 */
@Repository
@DbSchemaType(value = "BLOG")
@TableSeg(tableName = "USER_BASE_INFO_EXP")
class UserBaseInfoExpDaoImpl extends AbstractOrginDao<UserBaseInfoExpEntry> implements IUserBaseInfoExpDao {

	@Override
	public UserBaseInfoExpEntry queryByUid(final String uid) {
		final Map<String, Object> whereColumns = new HashMap<String, Object>();
		whereColumns.put("UID", uid);
		return super.queryByColumns(whereColumns);
	}

}