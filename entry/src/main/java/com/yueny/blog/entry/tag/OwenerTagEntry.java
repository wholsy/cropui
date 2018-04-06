package com.yueny.blog.entry.tag;

import com.yueny.kapo.api.annnotation.EntryPk;
import com.yueny.kapo.api.annnotation.VersionEntry;
import com.yueny.kapo.api.pojo.instance.BaseEntry;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 个人分类表
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月2日 上午9:43:25
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@VersionEntry
public class OwenerTagEntry extends BaseEntry {
	/** 全站文章分类编号 */
	private String categoriesTagCode;
	/** 个人分类编号 */
	private String owenerTagCode;
	/** 关联文章数目 */
	private Integer correlaArticleSum;
	/** 是否显示,1显示,0隐藏 */
	private Integer isShow;
	/** 主键 */
	@EntryPk
	private Long owenerTagId;
	/** 个人分类名称 */
	private String owenerTagName;
	/** 用户唯一标识，用户编号。md5加密生成 */
	private String uid;
	/** 权重, 1-255 */
	private Integer weight;
}
