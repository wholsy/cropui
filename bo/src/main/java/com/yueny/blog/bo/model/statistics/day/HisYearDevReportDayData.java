package com.yueny.blog.bo.model.statistics.day;

import java.util.Set;

import com.yueny.blog.bo.help.HisDevReportBo;

import lombok.Data;

/**
 * 年度历史开发报告数据结构对象<br>
 * 统计到天
 *
 * @author 袁洋 2015年8月10日 下午3:36:26
 *
 */
@Data
public class HisYearDevReportDayData implements
		Comparable<HisYearDevReportDayData> {
	/**
	 * 月内的每次度报告数据结构<br>
	 * 该月内的事件记录<br>
	 */
	private Set<HisDevReportBo> hisDayDevReports;
	/**
	 * 年度
	 */
	private Integer year;

	@Override
	public int compareTo(final HisYearDevReportDayData o) {
		// 年度降序
		return o.getYear() - this.year;
	}

}
