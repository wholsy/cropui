package com.yueny.blog.bo.model.statistics.month;

import java.util.Set;

import com.yueny.blog.bo.HisDevReportBo;

import lombok.Data;

/**
 * 月度历史开发报告数据结构对象
 *
 * @author 袁洋 2015年8月10日 下午3:07:39
 *
 */
@Data
public class HisMonthDevReportData implements Comparable<HisMonthDevReportData> {
	/**
	 * 月内的每次度报告数据结构<br>
	 * 该月内的事件记录<br>
	 * 月度日期倒排
	 */
	private Set<HisDevReportBo> hisDayDevReports;
	/**
	 * 月度
	 */
	private Integer month;
	/**
	 * 月度标题
	 */
	private String title;

	@Override
	public int compareTo(final HisMonthDevReportData bo) {
		// 月度降序
		return bo.getMonth() - this.month;
	}

}
