package com.yueny.blog.dao.help;

import java.util.List;

import com.yueny.blog.entry.help.AboutUsInfoEntry;
import com.yueny.kapo.api.ISingleTableDao;
import com.yueny.kapo.api.IWholeTableQuery;

/**
 * 关于我们信息
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月11日 上午11:09:12
 *
 */
public interface IAboutUsInfoDao extends ISingleTableDao<AboutUsInfoEntry>, IWholeTableQuery<AboutUsInfoEntry> {
	/**
	 * @return 全表查询
	 */
	List<AboutUsInfoEntry> queryAllData();

}
