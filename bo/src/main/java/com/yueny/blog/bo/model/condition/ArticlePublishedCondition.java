package com.yueny.blog.bo.model.condition;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.yueny.rapid.data.resp.pojo.request.BaseRequest;
import com.yueny.rapid.lang.mask.annotation.Mask;
import com.yueny.superclub.api.annnotation.et.UnUsed;

import lombok.Getter;
import lombok.Setter;

/**
 * 文章发布请求实体
 *
 * @author 袁洋 2015年8月25日 上午10:50:01
 *
 */
@Getter
@Setter
public class ArticlePublishedCondition extends BaseRequest {
	/**
	 *
	 */
	private static final long serialVersionUID = -1355151071685892599L;

	/** 文章别名（URL中使用，取代文章ID） */
	private String articleAlias;
	/** 文章对外ID */
	private String articleBlogId;
	// @Email(message = "邮箱格式不合法,请输入合法邮箱")
	/** 文章内容 */
	@NotEmpty(message = "文章内容不能为空！")
	@Mask(left = 80, right = 20)
	private String articleContext;
	/** 文章MARKDOWN内容 */
	@NotEmpty(message = "文章内容不能为空！")
	@Mask(left = 80, right = 20)
	private String articleContextForMd;
	/** 文章摘要 */
	@Mask(left = 50, right = 20)
	private String articleDigest;
	/** 更多文章（添加关联的文章url），多个文章地址之间用“,”分隔 */
	private String articleMore;

	/** 文章标签,最多添加5个标签，多个标签之间用“,”分隔 */
	@UnUsed
	private String articleTag;

	/** 文章标题 */
	@NotEmpty(message = "文章标题不能为空！")
	private String articleTitle;

	/** 全站文章分类编号, （到分类首页）,固定,单选 */
	@NotEmpty(message = "全站文章分类不能为空！")
	private String categoryTagCode;

	/** 个人分类,多个分类之间用“,”分隔,包含已存在分类的主键,和新增分类,eg: '1,3,6,8,things' */
	@NotEmpty(message = "个人分类类型不能为空！")
	private String owenerTag;

	/** 文章标题类型 */
	@NotNull(message = "个人分类类型不能为空！")
	private Integer selTypeCode;
}
