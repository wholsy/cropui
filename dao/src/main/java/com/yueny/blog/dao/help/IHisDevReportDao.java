package com.yueny.blog.dao.help;

import java.util.List;

import com.yueny.blog.entry.help.HisDevReportEntry;
import com.yueny.kapo.api.ISingleTableDao;
import com.yueny.kapo.api.IWholeTableQueryDao;

/**
 * 历史开发报告
 *
 * @author yueny(yueny09@163.com)
 *
 * @date 2015年8月9日 下午7:16:58
 */
public interface IHisDevReportDao extends ISingleTableDao<HisDevReportEntry>, IWholeTableQueryDao<HisDevReportEntry> {
	/**
	 * @return 全表查询
	 */
	List<HisDevReportEntry> queryAllData();

}
