package com.yueny.blog.service.task.cron;

import java.text.ParseException;
import java.util.StringTokenizer;

import org.quartz.CronExpression;

import lombok.Getter;

/**
 * @author 袁洋 2015年8月12日 上午9:31:49
 *
 */
@Deprecated
public final class CronExpressionEx {
	/**
	 * ALL_SPEC
	 */
	public static final Integer ALL_SPEC = new Integer(99);
	/**
	 * NO_SPEC_INT
	 */
	public static final int NO_SPEC_INT = 98;
	private final CronExpression cronExp;
	@Getter
	private final String daysOfMonthExp;
	@Getter
	private final String daysOfWeekExp;
	@Getter
	private final String hoursExp;
	@Getter
	private final String minutesExp;
	@Getter
	private final String monthsExp;
	@Getter
	private final String secondsExp;

	/**
	 * @param cronExpression
	 *            cronExp配置表达式，如"0 0/1 * * * ?" 开始触发，并每隔一分钟触发一次
	 * @throws ParseException
	 *             ParseException异常
	 */
	public CronExpressionEx(final String cronExpression) throws ParseException {
		cronExp = new CronExpression(cronExpression);

		final StringTokenizer exprsTok = new StringTokenizer(cronExpression, " \t", false);
		this.secondsExp = exprsTok.nextToken().trim();
		this.minutesExp = exprsTok.nextToken().trim();
		this.hoursExp = exprsTok.nextToken().trim();
		this.daysOfMonthExp = exprsTok.nextToken().trim();
		this.monthsExp = exprsTok.nextToken().trim();
		this.daysOfWeekExp = exprsTok.nextToken().trim();
	}

	public final CronExpression exp() {
		return this.cronExp;
	}

}
