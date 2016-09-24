package com.yueny.blog.entry.help;

import java.sql.Timestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.yueny.kapo.api.annnotation.EntryPk;
import com.yueny.kapo.api.pojo.instance.BaseEntry;

/**
 * 历史开发报告实体
 *
 * @author yueny(yueny09@163.com)
 *
 * @date 2015年8月9日 下午7:08:23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HisDevReportEntry extends BaseEntry {
	/** 报告内容 */
	private String context;
	/** 主键 */
	@EntryPk
	private Long hisDevReportId;
	/** 备注 */
	private String memo;
	/** 报告日期,格式为yyyy-MM-dd HH:mm:ss */
	private Timestamp reportTime;
	/** 报告提交人 */
	private String reportUser;
	/** 标志性阶段 */
	private String step;
	/** 报告标题 */
	private String title;

}
