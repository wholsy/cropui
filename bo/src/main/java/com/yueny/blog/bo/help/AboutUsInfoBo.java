package com.yueny.blog.bo.help;

import java.util.List;

import com.yueny.superclub.util.common.pojo.BaseBo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 关于我们信息实体
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月11日 上午11:08:10
 *
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class AboutUsInfoBo extends BaseBo {
	/**
	 *
	 */
	private static final long serialVersionUID = -936838372323741589L;
	/** 生日天 */
	private Integer authorBirthdayDay;
	/** 生日月 */
	private Integer authorBirthdayMonth;
	/** 生日年 */
	private Integer authorBirthdayYear;
	/** 作者 */
	private String authorName;
	/** 联系方式,多种以 "|&|" 分隔 */
	private List<String> contactWays;
	/** 主键 */
	private Long id;
	/** 履历,多行以 "|&|" 分隔 */
	private List<String> resumes;
	/** 花名/别名/佚名 */
	private String screenName;

}
