package com.yueny.blog.service.help;

import com.yueny.blog.bo.model.statistics.day.HisDevReportDayData;
import com.yueny.blog.bo.model.statistics.month.HisDevReportMonthData;

/**
 * 帮助服务
 *
 * @author yueny(yueny09@163.com)
 *
 * @date 2015年8月9日 下午3:39:48
 */
public interface IHelpService {
	/**
	 * @return 获取历史开发报告，统计到月
	 */
	HisDevReportMonthData queryHisDevReportsMonth();

	/**
	 * @return 获取历史开发报告，统计到天
	 */
	HisDevReportDayData queryHisDevReportsDay();

}
