package com.yueny.blog.bo;

import com.yueny.superclub.util.common.pojo.BaseBo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 热点信息
 *
 * @author 袁洋 2015年8月6日 下午2:33:59
 *
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class HotsMsgBo extends BaseBo {
	/**
	 *
	 */
	private static final long serialVersionUID = 2192375454315802386L;
	/** 内容 */
	private String context;
	/** 信息发布者ip */
	private String ip;
	/** 标题 */
	private String title;

}
