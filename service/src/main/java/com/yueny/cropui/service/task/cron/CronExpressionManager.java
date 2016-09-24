package com.yueny.cropui.service.task.cron;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import com.yueny.rapid.lang.date.DateUtil;

/**
 * cronExp配置表达式管理器
 *
 * @author 袁洋 2015年8月12日 上午9:35:46
 *
 */
public class CronExpressionManager {
	// 日
	private static final Map<Integer, String> jCB_DD_Map = new TreeMap<Integer, String>();
	// 小时
	private static final Map<Integer, String> jCB_HH_Map = new TreeMap<Integer, String>();
	// 月
	private static final Map<Integer, String> jCB_MM_Map = new TreeMap<Integer, String>();
	// 分钟
	private static final Map<Integer, String> jCB_MU_Map = new TreeMap<Integer, String>();
	// 周
	private static final Map<Integer, String> jCB_WK_Map = new TreeMap<Integer, String>();

	/**
	 * 初始化
	 */
	static {
		for (int i = 0; i < 60; i++) {
			jCB_MU_Map.put(Integer.valueOf(i), "" + i + "");
		}
		for (int i = 10; i <= 31; i++) {
			jCB_DD_Map.put(Integer.valueOf(i), "" + i + "");
		}
		for (int i = 1; i <= 12; i++) {
			jCB_MM_Map.put(Integer.valueOf(i), "" + i + "");
		}
		for (int i = 1; i <= 7; i++) {
			jCB_WK_Map.put(Integer.valueOf(i), "" + i + "");
		}
		for (int i = 0; i <= 23; i++) {
			jCB_HH_Map.put(Integer.valueOf(i), "" + i + "");
		}
	}

	/**
	 * @param args
	 *            main args
	 */
	public static void main(final String[] args) {
		System.out.println(CronExpressionManager.parseExpression("0 0/1 * * * ? *")); // 开始触发，并每隔一分钟触发一次
		System.out.println(CronExpressionManager.parseExpression("0 15 10 L * ?", 3)); // 每月最后一日的上午10:15触发
	}

	/**
	 * 执行计划的匹配结果，默认匹配8次
	 *
	 * @param expression
	 *            cronExp配置表达式，如"0 0/1 * * * ?" 开始触发，并每隔一分钟触发一次
	 * @return 执行计划的匹配结果
	 */
	public static String parseExpression(final String expression) {
		return parseExpression(expression, 8);
	}

	/**
	 * @param expression
	 *            cronExp配置表达式，如"0 0/1 * * * ?" 开始触发，并每隔一分钟触发一次
	 * @param step
	 *            匹配次数
	 * @return 执行计划的匹配结果
	 */
	public static String parseExpression(final String expression, final Integer step) {
		String expresText = "";
		try {
			final CronExpressionEx exp = new CronExpressionEx(expression.trim());
			Date date = new Date();
			final String startTime = DateUtil.formatLongWeb(date);
			final StringBuffer executePlan = new StringBuffer();
			executePlan.append("计划执行时间:\n");
			for (int i = 1; i <= step; i++) {
				date = exp.getNextValidTimeAfter(date);
				executePlan.append(i + ": " + DateUtil.formatLongWeb(date) + "\n");
				date = new Date(date.getTime() + 1000L);
			}
			expresText = "开始时间:" + startTime + "\n" + executePlan.toString();
		} catch (final Exception e) {
			expresText = "表达式有误";
		}
		return expresText;
	}

}
