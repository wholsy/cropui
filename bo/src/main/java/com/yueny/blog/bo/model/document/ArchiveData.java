package com.yueny.blog.bo.model.document;

import java.util.List;

import com.yueny.blog.bo.article.ArticleSimpleBlogBo;
import com.yueny.superclub.api.pojo.instance.AbstractBo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 文章存档数据对象
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月10日 上午11:58:55
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ArchiveData extends AbstractBo {
	/**
	 *
	 */
	private static final long serialVersionUID = 922223697133301091L;
	/**
	 * 年内的月度简要博文对象<br>
	 */
	private List<ArticleSimpleBlogBo> list;
	/**
	 * 年度
	 */
	private Integer year;
}
