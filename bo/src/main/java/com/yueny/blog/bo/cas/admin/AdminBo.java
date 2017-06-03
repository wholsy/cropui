package com.yueny.blog.bo.cas.admin;

import java.sql.Timestamp;

import com.yueny.superclub.util.common.pojo.BaseBo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年7月17日 下午12:00:24
 *
 */
@Getter
@Setter
public class AdminBo extends BaseBo {
	/**
	 *
	 */
	private static final long serialVersionUID = 8724431745301086880L;

	private long id;
	/**
	 * 最近一次登陆时间
	 */
	private Timestamp laterLoginTime;
	/**
	 * 登录名
	 */
	private String loginName;

}
