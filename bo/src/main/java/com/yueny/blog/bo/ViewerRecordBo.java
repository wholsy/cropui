package com.yueny.blog.bo;

import com.yueny.superclub.util.common.pojo.BaseBo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 关于我们信息实体
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年8月2日 下午1:06:33
 * @since
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class ViewerRecordBo extends BaseBo {
	/**
	 *
	 */
	private static final long serialVersionUID = 6697969087323485381L;

	/** 客户端IP */
	private String clientIp;
	/** 主键 */
	private Long id;
	/** 访问地址 */
	private String requestUri;

}
