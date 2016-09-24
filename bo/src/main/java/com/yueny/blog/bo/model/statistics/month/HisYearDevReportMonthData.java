package com.yueny.blog.bo.model.statistics.month;

import java.util.Set;

import lombok.Data;

/**
 * 年度历史开发报告数据结构对象<br>
 * 统计到月
 *
 * @author 袁洋 2015年8月10日 下午2:33:15
 *
 */
@Data
public class HisYearDevReportMonthData implements
		Comparable<HisYearDevReportMonthData> {
	/**
	 * 年内的月度报告数据结构<br>
	 * 该年内的事件记录<br>
	 */
	private Set<HisMonthDevReportData> hisMonthDevReports;
	/**
	 * 年度
	 */
	private Integer year;

	@Override
	public int compareTo(final HisYearDevReportMonthData o) {
		// 年度降序
		return o.getYear() - this.year;
	}

}
