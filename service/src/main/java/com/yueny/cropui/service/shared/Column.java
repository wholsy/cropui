package com.yueny.cropui.service.shared;

import org.apache.commons.lang3.StringUtils;

/**
 * 列(单元)实体
 * 
 * @author <a href="mailto:yueny09@126.com"> 袁洋 2015年1月20日 上午11:21:31
 * 
 * @category tag
 */
public final class Column {
	private String align;

	private final String content;

	/**
	 * @param content
	 *            内容
	 */
	public Column(final String content) {
		this.content = content;
	}

	/**
	 * @param content
	 *            内容
	 * @param align
	 *            align
	 */
	public Column(final String content, final String align) {
		this.content = content;
		this.align = align;
	}

	@Override
	public String toString() {
		if (StringUtils.isBlank(align)) {
			return "<td>" + content + "</td>";
		}
		return "<td align='" + align + "'>" + content + "</td>";

	}

}
