package com.yueny.blog.bo.help;

import com.yueny.blog.bo.enums.DevStep;
import com.yueny.superclub.util.common.pojo.BaseBo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 历史开发报告实体
 *
 * @author yueny(yueny09@163.com)
 *
 * @date 2015年8月9日 下午3:36:54
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class HisDevReportBo extends BaseBo implements Comparable<HisDevReportBo> {
	/**
	 *
	 */
	private static final long serialVersionUID = 3218285279144248658L;
	/** 报告内容 */
	private String context;
	/** 备注 */
	private String memo;
	/** 报告日期,格式为 MM-dd */
	private String reportTime;
	/** 报告提交人 */
	private String reportUser;
	/** 标志性阶段 */
	private DevStep step;
	/** 报告标题 */
	private String title;

	@Override
	public int compareTo(final HisDevReportBo o) {
		// this.reportTime.compareTo(o.getReportTime());
		// 的比较结果适用于日期的升序，若要为降序，这比较对象倒过来
		/* 日期的降序 */
		return o.getReportTime().compareTo(this.reportTime);
	}

	// /**
	// * @return 将报告日期格式重写为"月-日" MM-dd
	// */
	// @JsonSerialize(using = SmallMonthDayDateSerializer.class)
	// public String getReportTime() {
	// return this.reportTime;
	// }

}
