package com.yueny.blog.dao.more.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yueny.blog.dao.more.IViewerRecordDao;
import com.yueny.blog.entry.ViewerRecordEntry;
import com.yueny.kapo.api.annnotation.DbSchemaType;
import com.yueny.kapo.api.annnotation.TableSeg;
import com.yueny.kapo.core.condition.builder.QueryBuilder;
import com.yueny.kapo.core.dao.SingleTableDao;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年8月2日 下午1:15:36
 * @since
 */
@DbSchemaType("BLOG")
@TableSeg(tableName = "VIEWER_RECORD")
@Repository
public class ViewerRecordDaoImpl extends SingleTableDao<ViewerRecordEntry> implements IViewerRecordDao {

	@Override
	public List<ViewerRecordEntry> queryAllData() {
		final QueryBuilder builder = QueryBuilder.builder().orderBy("CREATE_TIME").build();
		return super.queryListByColumns(builder);
	}

}
