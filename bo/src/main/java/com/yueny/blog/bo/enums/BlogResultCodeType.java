package com.yueny.blog.bo.enums;

import com.yueny.superclub.api.enums.IResultCodeType;

public enum BlogResultCodeType implements IResultCodeType {
	/*** 非法请求,请刷新后重试! */
	ILLEGAL_REQUEST("610001", "非法请求,请刷新后重试!"),
	/*** 数据记录不存在 */
	RECORD_NOT_EXIT_PLACE("610005", "数据记录:%s 不存在!"),
	/*** 博文不存在 */
	BLOG_RECORD_NOT_EXIT("610006", "博文不存在!"),
	/*** 不支持的上传类型 */
	FILE_UPLOAD_TYPE_UNSUPPORT("610010", "不支持的上传类型"),
	/*** 上传文件大小超过限制 */
	FILE_UPLOAD_TOO_LARGE("610011", "上传文件大小超过限制"),
	/*** 不支持的文件类型 */
	FILE_UPLOAD_EXT_UNSUPPORT("610012", "不支持的文件类型"),
	/*** 上传失败 */
	FILE_UPLOAD_FAIL("610013", "上传失败"),
	/*** 上传根目录不存在 */
	FILE_UPLOAD_DIRECTORY_NOT_EXIST("610014", "上传根目录不存在"),
	/*** 上传根目录没有写权限 */
	FILE_UPLOAD_DIRECTORY_WRITE_PERMISSION("610015", "上传根目录没有写权限"),
	/*** 数据记录不存在 */
	EXIT_FOR_OWENER_TAG_NAME("610016", "个人分类标签:%s 已经被使用!"),
	/*** 不支持的操作 */
	UNSUPPORT_ACTION("610017", "不支持的操作！"),
	/*** 个人分类类型不能为空！ */
	INVALID_IS_NULL_FOR_OWENER_TAG("610018", "个人分类类型不能为空！"),
	/*** 全站文章分类不能为空！ */
	INVALID_IS_NULL_FOR_CATEGORY_TAG_CODE("610019", "全站文章分类不能为空！"),
	;
	
	/** 错误码 */
	private String code;

	/** 错误码描述 */
	private String message;

	/**
	 * @param errorCode
	 *            错误码
	 * @param describe
	 *            错误码描述
	 */
	private BlogResultCodeType(final String errorCode, final String describe) {
		this.code = errorCode;
		this.message = describe;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

}
