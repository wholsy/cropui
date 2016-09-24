package com.yueny.blog.bo.model.statistics.month;

import java.util.Set;

import lombok.Data;

/**
 * 历史开发报告数据结构对象<br>
 * 统计到月
 *
 * @author 袁洋 2015年8月10日 下午2:03:57
 *
 */
@Data
public class HisDevReportMonthData {
	/**
	 * 报告数据结构<br>
	 * 年度日期倒排
	 */
	private Set<HisYearDevReportMonthData> hisYearDevReports;
	/**
	 * 报告总标题
	 */
	private String title;

}
