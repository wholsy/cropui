package com.yueny.blog.entry;

import com.yueny.kapo.api.annnotation.EntryPk;
import com.yueny.kapo.api.annnotation.ModifyUserEntry;
import com.yueny.kapo.api.annnotation.VersionEntry;
import com.yueny.kapo.api.pojo.instance.BaseEntry;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 访客记录实体
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年8月2日 下午1:02:52
 * @since
 */
@Data
@EqualsAndHashCode(callSuper = false)
@VersionEntry
@ModifyUserEntry
public class ViewerRecordEntry extends BaseEntry {
	/** 客户端IP */
	private String clientIp;
	/** 主键 */
	@EntryPk
	private Long id;
	/** 访问地址 */
	private String requestUri;

}
