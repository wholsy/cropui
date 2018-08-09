package com.yueny.blog.dao.more;

import java.util.List;

import com.yueny.blog.entry.ViewerRecordEntry;
import com.yueny.kapo.api.ISingleTableDao;
import com.yueny.kapo.api.IWholeTableQueryDao;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年8月2日 下午1:14:38
 * @since
 */
public interface IViewerRecordDao extends ISingleTableDao<ViewerRecordEntry>, IWholeTableQueryDao<ViewerRecordEntry> {
	/**
	 * @return 全表查询
	 */
	List<ViewerRecordEntry> queryAllData();

}
