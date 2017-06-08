package com.yueny.blog.entry.cas.user;

import com.yueny.kapo.api.annnotation.EntryPk;
import com.yueny.kapo.api.annnotation.VersionEntry;
import com.yueny.kapo.api.pojo.instance.BaseEntry;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户扩展信息
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年7月17日 上午11:50:47
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@VersionEntry
public class UserBaseInfoExpEntry extends BaseEntry {
	/** 用户身份证反面(微图片安全数据存储对象)ID */
	private String cardBackTinyImageIoUrl;
	/** 用户身份证正面(微图片安全数据存储对象)ID */
	private String cardFrontTinyImageIoUrl;
	/** 用户头像(微图片安全数据存储对象)ID */
	private String headTinyImageIoUrl;
	/** 用户唯一标识，用户编号。md5加密生成 */
	private String uid;
	/** 主键 */
	@EntryPk
	private long userBaseInfoExpId;

}
