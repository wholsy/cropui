package com.yueny.blog.bo.article;

import org.apache.commons.lang3.StringUtils;

import com.yueny.rapid.lang.util.StringUtil;
import com.yueny.superclub.util.common.pojo.BaseBo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 简要博文对象
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月5日 下午1:26:17
 *
 */
@EqualsAndHashCode(callSuper = false)
public class ArticleSimpleBlogBo extends BaseBo {
	/**
	 *
	 */
	private static final long serialVersionUID = -2076997508273879596L;

	/** 文章别名（URL中使用，取代文章ID） */
	@Getter
	@Setter
	private String articleAlias;
	/** 文章对外ID */
	@Getter
	@Setter
	private String articleBlogId;
	/** 文章标题 */
	@Getter
	@Setter
	private String articleTitle;
	/** 创建日期,yyyy-MM-dd */
	@Getter
	@Setter
	private String today;

	/**
	 * 文章标题概要,当标题过长时,该概要会截取标题的前20个字
	 */
	public String getArticleSchemaTitle() {
		final String articleSchemaTitle = StringUtil.substrings(this.articleTitle, 36);
		if (StringUtils.equals(articleSchemaTitle, this.articleTitle)) {
			return articleSchemaTitle;
		}

		return articleSchemaTitle + "......";
	}

}
