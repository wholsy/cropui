/**
 * 
 */
package com.yueny.blog.common.enums;


public enum ProfileType {
	PROD("正式版"),
	LOCAL("本地版"),
	DEV("开发版");

	private ProfileType(String desc) {
		this.desc = desc;
	}

	private String desc;
	/**
	 * @return the desc  描述
	 */
	public String getDesc() {
		return desc;
	}

}
