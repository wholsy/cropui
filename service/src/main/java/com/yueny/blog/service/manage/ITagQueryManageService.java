package com.yueny.blog.service.manage;

import com.yueny.blog.bo.model.document.ChartData;
import com.yueny.rapid.lang.exception.DataVerifyAnomalyException;

/**
 * 标签查询服务
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月12日 下午8:56:23
 *
 */
public interface ITagQueryManageService {
	/**
	 * 获得全站标签树
	 *
	 * @throws DataVerifyAnomalyException
	 */
	ChartData getChartData() throws DataVerifyAnomalyException;
}
