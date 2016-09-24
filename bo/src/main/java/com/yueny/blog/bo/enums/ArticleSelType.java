package com.yueny.blog.bo.enums;

import com.yueny.superclub.api.annnotation.EnumValue;
import com.yueny.superclub.api.enums.core.IEnumType;

/**
 * 文章标题类型
 * 
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月7日 下午10:54:46
 *
 */
public enum ArticleSelType implements IEnumType {
	/**
	 * 原创
	 */
	ORIGINAL(1, "原创"),
	/**
	 * 转载
	 */
	TRANSFER(2, "转载"),
	/**
	 * 翻译
	 */
	translate(4, "翻译");
	/**
	 * 根据值得到枚举类型
	 */
	public static ArticleSelType getTypeByValue(final Integer value) {
		for (final ArticleSelType type : values()) {
			if (type.getValue().intValue() == value) {
				return type;
			}
		}
		return null;
	}

	/**
	 * 描述
	 */
	private final String desc;
	/**
	 * 值
	 */
	private final Integer value;

	/**
	 * @param value
	 *            值
	 */
	private ArticleSelType(final Integer value, final String desc) {
		this.value = value;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	@EnumValue
	public Integer getValue() {
		return value;
	}
}
