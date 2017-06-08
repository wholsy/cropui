package com.yueny.blog.bo.cas.user;

import java.sql.Date;

import com.yueny.rapid.lang.mask.annotation.Mask;
import com.yueny.rapid.lang.util.enums.GenderType;
import com.yueny.superclub.util.common.pojo.BaseBo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年7月17日 下午12:00:08
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserBaseInfoBo extends BaseBo {
	/**
	 *
	 */
	private static final long serialVersionUID = -1580090523137876312L;

	/** 生日 */
	private Date birthDayDate;
	/** 身份证号 */
	@Mask
	private String cardId;
	/** 邮箱 */
	@Mask
	private String email;
	/** 手机号码 */
	@Mask
	private String mobile;
	/** 性别,值来源枚举GenderType */
	private GenderType sex;
	/** 用户真实姓名 */
	@Mask
	private String trueName;
	/** 用户唯一标识，用户编号。md5加密生成 */
	@Mask
	private String uid;
	/** 主键 */
	private long userBaseInfoId;

}
