package com.yueny.cropui.service.io.enums;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.yueny.superclub.api.enums.core.IEnumType;

/**
 * 文本类型
 *
 * @author yueny(yueny09@163.com)
 *
 * @date 2015年9月19日 下午3:41:50
 */
public enum TxtType implements IEnumType {
	/**
	 * .txt 文本
	 */
	TXT("text/plain");

	private String contentType;

	TxtType(final String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return 该文本类型支持的所有文本扩展名
	 */
	public List<String> getContentType() {
		final String[] containTypes = StringUtils.split(contentType, ",");
		return Arrays.asList(containTypes);
	}

}