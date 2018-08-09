package com.yueny.blog.dao.more.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yueny.blog.dao.more.IHisDevReportDao;
import com.yueny.blog.entry.HisDevReportEntry;
import com.yueny.kapo.api.annnotation.DbSchemaType;
import com.yueny.kapo.core.condition.builder.QueryBuilder;
import com.yueny.kapo.core.dao.SingleTableDao;

/**
 *
 * @author yueny(yueny09@163.com)
 *
 * @date 2015年8月9日 下午7:17:19
 */
@DbSchemaType("BLOG")
@Repository
class HisDevReportDaoImpl extends SingleTableDao<HisDevReportEntry> implements IHisDevReportDao {

	@Override
	public List<HisDevReportEntry> queryAllData() {
		// return super.queryAll();
		final QueryBuilder builder = QueryBuilder.builder().orderBy("CREATE_TIME").build();
		return super.queryListByColumns(builder);
	}

}
