package com.yueny.blog.entry.help;

import com.yueny.kapo.api.annnotation.EntryPk;
import com.yueny.kapo.api.pojo.instance.BaseEntry;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 关于我们信息实体
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月11日 上午11:00:12
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AboutUsInfoEntry extends BaseEntry {
	/** 生日天 */
	private Integer authorBirthdayDay;
	/** 生日月 */
	private Integer authorBirthdayMonth;
	/** 生日年 */
	private Integer authorBirthdayYear;
	/** 作者 */
	private String authorName;
	/** 联系方式,多种以 "|&|" 分隔 */
	private String contactWay;
	/** 主键 */
	@EntryPk
	private Long id;
	/** 履历,多行以 "|&|" 分隔 */
	private String resume;
	/** 花名/别名/佚名 */
	private String screenName;

}
