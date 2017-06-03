package com.yueny.blog.dao.cas.admin.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yueny.blog.dao.cas.admin.IAdminDao;
import com.yueny.blog.dao.cas.admin.IAdminPassportDao;
import com.yueny.blog.entry.cas.admin.AdminEntry;
import com.yueny.kapo.api.annnotation.DbSchemaType;
import com.yueny.kapo.core.condition.builder.UpdateBuilder;
import com.yueny.kapo.core.dao.biz.origin.AbstractOrginDao;

/**
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年11月30日 下午4:20:10
 *
 */
@Repository
@DbSchemaType(value = "BLOG")
// @TableSeg(tableName = "ADMIN")
class AdminDaoImpl extends AbstractOrginDao<AdminEntry> implements IAdminDao, IAdminPassportDao {

	@Override
	public boolean modifyPassPort(final AdminEntry entry) {
		final UpdateBuilder builder = UpdateBuilder.builder().set("PASSPORT", entry.getPassport())
				.where("ID", entry.getId()).where("LOGIN_NAME", entry.getLoginName()).build();

		return super.updateByColumns(builder) == 1;
	}

	@Override
	public AdminEntry queryByLoginName(final String loginName) {
		final Map<String, Object> whereColumns = new HashMap<String, Object>();
		whereColumns.put("LOGIN_NAME", loginName);
		return super.queryByColumns(whereColumns);
	}

	@Override
	public List<AdminEntry> queryBySystemCode(final String systemCode) {
		final Map<String, Object> whereColumns = new HashMap<String, Object>();
		whereColumns.put("SUB_SYSTEM_CODE", systemCode);
		return super.queryListByColumns(whereColumns);
	}

	@Override
	public String queryPwdByLoginName(final String loginName) {
		final Map<String, Object> whereColumns = new HashMap<String, Object>();
		whereColumns.put("LOGIN_NAME", loginName);
		whereColumns.put("SUB_SYSTEM_CODE", "CMS");

		final AdminEntry entry = super.queryByColumns(whereColumns);
		if (entry == null) {
			return null;
		}

		return entry.getPassport();
	}

}