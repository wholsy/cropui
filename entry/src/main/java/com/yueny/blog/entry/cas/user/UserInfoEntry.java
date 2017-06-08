package com.yueny.blog.entry.cas.user;

import java.sql.Timestamp;

import com.yueny.kapo.api.annnotation.EntryPk;
import com.yueny.kapo.api.annnotation.VersionEntry;
import com.yueny.kapo.api.pojo.instance.BaseEntry;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户信息表
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年7月17日 上午11:50:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
@VersionEntry
public class UserInfoEntry extends BaseEntry {
	/** 最近一次登录时间 */
	private Timestamp laterLoginTime;
	/** 用户登录名 */
	private String loginName;
	/** 加密后的登录密码 */
	private String passport;
	/** 用户唯一标识，用户编号。md5加密生成 */
	private String uid;
	/** 主键 */
	@EntryPk
	private long userInfoId;
	/** 用户状态,来源为UserStatusType.1正常,2未激活,4状态异常,8已注销,16已锁定. */
	private Integer userStatus;

}
