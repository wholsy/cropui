package com.yueny.blog.entry.cas.admin;

import java.sql.Timestamp;

import com.yueny.kapo.api.annnotation.EntryPk;
import com.yueny.kapo.api.annnotation.ModifyUserEntry;
import com.yueny.kapo.api.annnotation.VersionEntry;
import com.yueny.kapo.api.pojo.instance.BaseEntry;

import lombok.Getter;
import lombok.Setter;

/**
 * 后台系统管理员表
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年11月30日 下午4:08:46
 *
 */
@Getter
@Setter
@ModifyUserEntry
@VersionEntry
public class AdminEntry extends BaseEntry {
	@EntryPk
	private long id;
	/**
	 * 最近一次登陆时间
	 */
	private Timestamp laterLoginTime;
	/**
	 * 登录名
	 */
	private String loginName;
	/** 用户唯一标识uid,128位 */
	/**
	 * 密码密文
	 */
	private String passport;
	/**
	 * 所属子系统编号
	 */
	private String subSystemCode;

}
