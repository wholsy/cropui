package com.yueny.blog.dao.more.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yueny.blog.dao.more.IAboutUsInfoDao;
import com.yueny.blog.entry.AboutUsInfoEntry;
import com.yueny.kapo.api.annnotation.DbSchemaType;
import com.yueny.kapo.api.annnotation.TableSeg;
import com.yueny.kapo.core.condition.builder.QueryBuilder;
import com.yueny.kapo.core.dao.SingleTableDao;

/**
 * 关于我们信息
 * 
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月11日 上午11:12:05
 *
 */
@DbSchemaType("BLOG")
@TableSeg(tableName = "ABOUT_US_INFO")
@Repository
public class AboutUsInfoDaoImpl extends SingleTableDao<AboutUsInfoEntry> implements IAboutUsInfoDao {

	@Override
	public List<AboutUsInfoEntry> queryAllData() {
		final QueryBuilder builder = QueryBuilder.builder().orderBy("CREATE_TIME").build();
		return super.queryListByColumns(builder);
	}

}
