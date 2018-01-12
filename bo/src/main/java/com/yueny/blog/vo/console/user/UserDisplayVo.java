package com.yueny.blog.vo.console.user;

import com.yueny.blog.bo.console.user.UserBaseInfoBo;
import com.yueny.blog.bo.console.user.UserBaseInfoExpBo;
import com.yueny.superclub.api.pojo.instance.AbstractBo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户显示信息实体
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2017年6月8日 上午11:49:10
 * @since
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class UserDisplayVo extends AbstractBo {
	/**
	 *
	 */
	private static final long serialVersionUID = 4733308604363789091L;
	/**
	 * 登录掩码名
	 */
	private String displayName;
	/**
	 *
	 */
	private UserBaseInfoBo userBaseInfo;
	/**
	 *
	 */
	private UserBaseInfoExpBo userBaseInfoExp;
	/**
	 *
	 */
	private UserInfoVo userInfo;

}
