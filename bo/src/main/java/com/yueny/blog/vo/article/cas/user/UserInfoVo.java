package com.yueny.blog.vo.article.cas.user;

import java.io.Serializable;
import java.util.Date;

import com.yueny.rapid.lang.mask.annotation.Mask;
import com.yueny.rapid.lang.mask.pojo.instance.AbstractMaskBo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2017年6月8日 下午1:09:40
 * @since
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfoVo extends AbstractMaskBo implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 5881135763806583755L;
	/** 最近一次登录时间 */
	private Date laterLoginTime;
	/** 用户登录名 */
	@Mask
	private String loginName;
	/** 用户唯一标识，用户编号。md5加密生成 */
	@Mask
	private String uid;
	/** 用户状态,来源为UserStatusType.1正常,2未激活,4状态异常,8已注销,16已锁定. */
	private Integer userStatus;

}
