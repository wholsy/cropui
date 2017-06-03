package com.yueny.blog.entry.article;

import com.yueny.kapo.api.annnotation.EntryPk;
import com.yueny.kapo.api.annnotation.ModifyUserEntry;
import com.yueny.kapo.api.annnotation.VersionEntry;
import com.yueny.kapo.api.pojo.instance.BaseEntry;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 博客表
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月1日 下午5:23:14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@VersionEntry
@ModifyUserEntry
public class ArticleBlogEntry extends BaseEntry {
	/** 文章别名（URL中使用，取代文章ID） */
	private String articleAlias;
	/** 文章对外ID */
	private String articleBlogId;
	/** 文章内容 */
	private String articleContext;
	/** 文章摘要 */
	private String articleDigest;
	/** 文章主键 */
	@EntryPk
	private Long articleId;
	/** 更多文章（添加关联的文章url），多个文章地址之间用“,”分隔 */
	private String articleMore;
	/** 此文章的上一篇文章对外ID */
	private String articlePreviousBlogId;
	/** 文章标签ID,最多添加5个标签，多个标签之间用“,”分隔 */
	private String articleTagIds;
	/** 文章标题 */
	private String articleTitle;
	/** 全站文章分类编号 ，多个标签之间用“,”分隔 */
	private String categoryTagCodes;
	/** 个人分类ID,多个分类之间用“,”分隔 */
	private String owenerTagIds;
	/** 阅读次数 */
	private Integer readTimes;
	/** 文章标题类型 */
	private Integer selTypeCode;
	/** 用户唯一标识，用户编号。md5加密生成 */
	private String uid;
}