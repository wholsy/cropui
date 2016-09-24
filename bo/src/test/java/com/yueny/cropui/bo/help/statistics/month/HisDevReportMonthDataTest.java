package com.yueny.cropui.bo.help.statistics.month;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import com.yueny.blog.bo.help.HisDevReportBo;
import com.yueny.blog.bo.model.statistics.month.HisDevReportMonthData;
import com.yueny.blog.bo.model.statistics.month.HisMonthDevReportData;
import com.yueny.blog.bo.model.statistics.month.HisYearDevReportMonthData;

/**
 * @author 袁洋 2015年8月11日 上午10:32:23
 *
 */
public class HisDevReportMonthDataTest {

	/**
	 * 降序测试
	 */
	@Test
	public void testReverse() {
		final Set<HisYearDevReportMonthData> hisYearDevReports = new TreeSet<>();

		final Set<HisMonthDevReportData> hisMonthDevReports = new TreeSet<>();
		final HisMonthDevReportData monthDevReportData1 = new HisMonthDevReportData();
		final Set<HisDevReportBo> hisDayDevReports = new TreeSet<>();
		final HisDevReportBo hisDevReportBo1 = new HisDevReportBo();
		hisDevReportBo1.setReportTime("2015-08-05 15:21:21");
		hisDayDevReports.add(hisDevReportBo1);
		final HisDevReportBo hisDevReportBo2 = new HisDevReportBo();
		hisDevReportBo2.setReportTime("2015-08-10 09:37:21");
		hisDayDevReports.add(hisDevReportBo2);
		final HisDevReportBo hisDevReportBo3 = new HisDevReportBo();
		hisDevReportBo3.setReportTime("2015-08-08 18:01:21");
		hisDayDevReports.add(hisDevReportBo3);
		monthDevReportData1.setMonth(8);
		monthDevReportData1.setHisDayDevReports(hisDayDevReports);
		hisMonthDevReports.add(monthDevReportData1);
		final HisMonthDevReportData monthDevReportData2 = new HisMonthDevReportData();
		monthDevReportData2.setMonth(12);
		hisMonthDevReports.add(monthDevReportData2);
		final HisMonthDevReportData monthDevReportData3 = new HisMonthDevReportData();
		monthDevReportData3.setMonth(9);
		hisMonthDevReports.add(monthDevReportData3);
		final HisYearDevReportMonthData hisYearDevReportData1 = new HisYearDevReportMonthData();
		hisYearDevReportData1.setYear(2013);
		hisYearDevReportData1.setHisMonthDevReports(hisMonthDevReports);
		hisYearDevReports.add(hisYearDevReportData1);

		final HisYearDevReportMonthData hisYearDevReportData2 = new HisYearDevReportMonthData();
		hisYearDevReportData2.setYear(2015);
		hisYearDevReports.add(hisYearDevReportData2);
		final HisYearDevReportMonthData hisYearDevReportData3 = new HisYearDevReportMonthData();
		hisYearDevReportData3.setYear(2014);
		hisYearDevReports.add(hisYearDevReportData3);

		final HisDevReportMonthData hisDevReportData = new HisDevReportMonthData();
		hisDevReportData.setTitle("cropui开发历史报告");
		hisDevReportData.setHisYearDevReports(hisYearDevReports);

		System.out.println(hisDevReportData);
	}

}
