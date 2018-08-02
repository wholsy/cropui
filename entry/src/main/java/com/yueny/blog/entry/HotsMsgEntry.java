package com.yueny.blog.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.yueny.kapo.api.pojo.instance.BaseEntry;

/**
 * 热点信息表
 *
 * @author 袁洋 2015年8月6日 下午2:32:17
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HotsMsgEntry extends BaseEntry {
	/** 信息发布者ip */
	private String ip;

}
