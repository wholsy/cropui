package com.yueny.cropui.service.exception;

import com.yueny.superclub.service.rest.enums.RestErrorType;

/**
 * cropui业务异常
 *
 * @author 袁洋 2014年8月2日
 */
public class CropuiToolkitsRuntimeException extends Exception {
	private static final long serialVersionUID = 2688032168051922671L;

	/** 错误码 */
	private final String code;
	/** 错误的描述性文字. */
	private final String message;

	/**
	 * /**
	 *
	 * @param errorType
	 *            错误码枚举
	 */
	public CropuiToolkitsRuntimeException(final RestErrorType errorType) {
		this(errorType.getCode(), errorType.getMessage());
	}

	/**
	 * @param code
	 *            错误码
	 * @param message
	 *            describe
	 */
	public CropuiToolkitsRuntimeException(final String code,
			final String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
