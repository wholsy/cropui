package com.yueny.blog.service.help.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yueny.blog.bo.help.HisDevReportBo;
import com.yueny.blog.bo.model.statistics.day.HisDevReportDayData;
import com.yueny.blog.bo.model.statistics.day.HisYearDevReportDayData;
import com.yueny.blog.bo.model.statistics.month.HisDevReportMonthData;
import com.yueny.blog.bo.model.statistics.month.HisMonthDevReportData;
import com.yueny.blog.bo.model.statistics.month.HisYearDevReportMonthData;
import com.yueny.blog.dao.help.IHisDevReportDao;
import com.yueny.blog.entry.help.HisDevReportEntry;
import com.yueny.blog.service.BaseBiz;
import com.yueny.blog.service.CacheBaseBiz.ICacheExecutor;
import com.yueny.blog.service.env.CacheService;
import com.yueny.blog.service.help.IHelpService;
import com.yueny.rapid.lang.date.DateFormatType;
import com.yueny.rapid.lang.date.DateTimeUtil;
import com.yueny.rapid.lang.date.DateUtil;
import com.yueny.rapid.lang.json.JsonUtil;

/**
 * 帮助服务
 *
 * @author yueny(yueny09@163.com)
 *
 * @date 2015年8月9日 下午3:40:22
 */
@Service
public class HelpServiceImpl extends BaseBiz implements IHelpService {
	@Autowired
	private CacheService<String> cacheService;
	@Autowired
	private IHisDevReportDao hisDevReportDao;

	@Override
	public HisDevReportDayData queryHisDevReportsDay() {
		final String json = cacheService.cache("", new ICacheExecutor<String>() {
			@Override
			public String execute() {
				final List<HisDevReportEntry> entrys = hisDevReportDao.queryAll();

				// 对数据格式进行封装
				final Map<String, Set<HisDevReportBo>> hisYearDevReportMap = new HashMap<String, Set<HisDevReportBo>>();
				for (final HisDevReportEntry entry : entrys) {
					final HisDevReportBo dayHisDevReport = map(entry, HisDevReportBo.class);
					// 覆盖,格式为 MM-dd
					dayHisDevReport.setReportTime(DateUtil.format(entry.getReportTime(), DateFormatType.MM_DD));

					final String currentYear = String.valueOf(DateTimeUtil.thisYear(entry.getReportTime()));
					/* 年度数据已经存在 */
					if (hisYearDevReportMap.containsKey(currentYear)) {
						hisYearDevReportMap.get(currentYear).add(dayHisDevReport);
						continue;
					}

					/* 年度数据不存在。java 7的钻石石写法(构造器后面的尖括号中不需要写类型) */
					final Set<HisDevReportBo> monthData = new TreeSet<>();
					monthData.add(dayHisDevReport);

					hisYearDevReportMap.put(currentYear, monthData);
				}

				// 转换为年度
				final Set<HisYearDevReportDayData> hisYearDevReports = new TreeSet<>();
				/* 年度遍历 */
				for (final Map.Entry<String, Set<HisDevReportBo>> yearEntry : hisYearDevReportMap.entrySet()) {
					final HisYearDevReportDayData yearDevReportDayData = new HisYearDevReportDayData();
					yearDevReportDayData.setYear(Integer.parseInt(yearEntry.getKey()));
					yearDevReportDayData.setHisDayDevReports(yearEntry.getValue());

					hisYearDevReports.add(yearDevReportDayData);
				}

				final HisDevReportDayData hisDevReportData = new HisDevReportDayData();
				hisDevReportData.setTitle("cropui 开发日度报告");
				hisDevReportData.setHisYearDevReports(hisYearDevReports);

				return JsonUtil.toJson(hisDevReportData);
			}
		});

		return JsonUtil.fromJson(json, HisDevReportDayData.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.yueny.cropui.service.help.IHelpService#queryHisDevReportsMonth
	 * ()
	 */
	@Override
	public HisDevReportMonthData queryHisDevReportsMonth() {
		final List<HisDevReportEntry> entrys = hisDevReportDao.queryAll();

		// 对数据格式进行封装
		final Map<String, Map<String, Set<HisDevReportBo>>> hisYearDevReportMap = new HashMap<String, Map<String, Set<HisDevReportBo>>>();
		for (final HisDevReportEntry entry : entrys) {
			final HisDevReportBo dayHisDevReport = map(entry, HisDevReportBo.class);

			/* 年度 */
			final String currentYear = String.valueOf(DateTimeUtil.thisYear(entry.getReportTime()));
			/* 月度 */
			final String currentMonth = String.valueOf(DateTimeUtil.thisMonth(entry.getReportTime()));
			;
			/* 年度数据已经存在 */
			if (hisYearDevReportMap.containsKey(currentYear)) {
				final Map<String, Set<HisDevReportBo>> monthData = hisYearDevReportMap.get(currentYear);
				/* 月度数据已经存在 */
				if (monthData.containsKey(currentMonth)) {
					monthData.get(currentMonth).add(dayHisDevReport);
					continue;
				}
				/* 月度数据不存在 */
				final Set<HisDevReportBo> monthDataList = new TreeSet<>();
				monthDataList.add(dayHisDevReport);
				hisYearDevReportMap.get(currentYear).put(currentMonth, monthDataList);
				continue;
			}

			/* 年度数据不存在，月度数据也一定不存在 */
			final Set<HisDevReportBo> monthDataList = new TreeSet<>();
			monthDataList.add(dayHisDevReport);

			final Map<String, Set<HisDevReportBo>> hisMonthDevReportMap = new HashMap<String, Set<HisDevReportBo>>();
			hisMonthDevReportMap.put(currentMonth, monthDataList);

			hisYearDevReportMap.put(currentYear, hisMonthDevReportMap);
		}

		// 转换为年度
		final Set<HisYearDevReportMonthData> hisYearDevReports = new TreeSet<>();
		/* 年度遍历 */
		for (final Map.Entry<String, Map<String, Set<HisDevReportBo>>> yearEntry : hisYearDevReportMap.entrySet()) {
			final HisYearDevReportMonthData hisYearDevReportData = new HisYearDevReportMonthData();
			hisYearDevReportData.setYear(Integer.parseInt(yearEntry.getKey()));

			/* 月度遍历 */
			final Set<HisMonthDevReportData> hisMonthDevReports = new TreeSet<>();
			for (final Map.Entry<String, Set<HisDevReportBo>> monthEntry : yearEntry.getValue().entrySet()) {
				final Integer month = Integer.parseInt(monthEntry.getKey());
				final Set<HisDevReportBo> hisDayDevReports = monthEntry.getValue();

				final HisMonthDevReportData monthDevReportData = new HisMonthDevReportData();
				monthDevReportData.setMonth(month);
				// 取每月的第一个项目的标题
				final HisDevReportBo firstHisDevReport = ((HisDevReportBo) hisDayDevReports
						.toArray()[hisDayDevReports.size() - 1]);
				monthDevReportData.setTitle(firstHisDevReport.getTitle());

				monthDevReportData.setHisDayDevReports(hisDayDevReports);

				hisMonthDevReports.add(monthDevReportData);
			}
			hisYearDevReportData.setHisMonthDevReports(hisMonthDevReports);

			hisYearDevReports.add(hisYearDevReportData);
		}

		final HisDevReportMonthData hisDevReportData = new HisDevReportMonthData();
		hisDevReportData.setTitle("cropui 开发月度报告");
		hisDevReportData.setHisYearDevReports(hisYearDevReports);

		return hisDevReportData;
	}
}
