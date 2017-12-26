package com.yueny.blog.entry.console.user;

import java.sql.Date;

import com.yueny.kapo.api.annnotation.EntryPk;
import com.yueny.kapo.api.annnotation.VersionEntry;
import com.yueny.kapo.api.pojo.instance.BaseEntry;
import com.yueny.rapid.lang.util.enums.GenderType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户基本信息表
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年7月17日 上午11:50:47
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@VersionEntry
public class UserBaseInfoEntry extends BaseEntry {
	/** 生日 */
	private Date birthDayDate;
	/** 身份证号 */
	private String cardId;
	/** 邮箱 */
	private String email;
	/** 手机号码 */
	private String mobile;
	/** 性别,值来源枚举GenderType */
	private GenderType sex;
	/** 用户真实姓名 */
	private String trueName;
	/** 用户唯一标识，用户编号。md5加密生成 */
	private String uid;
	/** 主键 */
	@EntryPk
	private long userBaseInfoId;

}
