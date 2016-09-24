package com.yueny.blog.bo.model.statistics.day;

import java.util.Set;

import lombok.Data;

/**
 * 历史开发报告数据结构对象<br>
 * 统计到天
 *
 * @author 袁洋 2015年8月10日 下午3:40:03
 *
 */
@Data
public class HisDevReportDayData {
	/**
	 * 报告数据结构<br>
	 * 年度日期倒排
	 */
	private Set<HisYearDevReportDayData> hisYearDevReports;
	/**
	 * 报告总标题
	 */
	private String title;

}
