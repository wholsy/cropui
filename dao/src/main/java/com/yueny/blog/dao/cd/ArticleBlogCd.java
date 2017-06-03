package com.yueny.blog.dao.cd;

import com.yueny.superclub.api.page.IPageable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 文章blog查询
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2017年6月3日 下午4:08:26
 * @since
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleBlogCd {
	/** 文章对外ID */
	private String articleBlogId;
	/** 文章标题， 右模糊查询 */
	private String articleTitle;
	/**
	 * 分页信息
	 */
	private IPageable pageable;
	/** 阅读次数N次及以上的 */
	private Integer readTimes;
	/** 文章标题类型 */
	private Integer selTypeCode;
}
