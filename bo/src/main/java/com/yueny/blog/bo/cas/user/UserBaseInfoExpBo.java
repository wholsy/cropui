package com.yueny.blog.bo.cas.user;

import com.yueny.rapid.lang.mask.annotation.Mask;
import com.yueny.superclub.util.common.pojo.BaseBo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年7月17日 下午12:00:34
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserBaseInfoExpBo extends BaseBo {
	/**
	 *
	 */
	private static final long serialVersionUID = 7819661079754680533L;

	/** 用户身份证反面(微图片安全数据存储对象)ID */
	private String cardBackTinyImageIoUrl;
	/** 用户身份证正面(微图片安全数据存储对象)ID */
	private String cardFrontTinyImageIoUrl;
	/** 用户头像(微图片安全数据存储对象)ID */
	private String headTinyImageIoUrl;
	/** 用户唯一标识，用户编号。md5加密生成 */
	@Mask
	private String uid;
	/** 主键 */
	private long userBaseInfoExpId;

}
