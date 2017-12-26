package com.yueny.blog.bo.console.user;

import java.util.Date;

import com.yueny.rapid.lang.mask.annotation.Mask;
import com.yueny.superclub.util.common.pojo.BaseBo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年7月17日 下午12:00:24
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfoBo extends BaseBo {
	/**
	 *
	 */
	private static final long serialVersionUID = 8724431745301086880L;

	/** 最近一次登录时间 */
	private Date laterLoginTime;
	/** 用户登录名 */
	@Mask
	private String loginName;
	/** 加密后的登录密码 */
	@Mask
	private String passport;
	/** 用户唯一标识，用户编号。md5加密生成 */
	@Mask
	private String uid;
	/** 主键 */
	private long userInfoId;
	/** 用户状态,来源为UserStatusType.1正常,2未激活,4状态异常,8已注销,16已锁定. */
	private Integer userStatus;

}
