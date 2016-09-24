//package com.yueny.cropui.service.exception;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.yueny.superclub.api.enums.exception.IErrorType;
//import com.yueny.superclub.util.common.spring.EnumTypeContainer;
//
///**
// * @category 错误枚举管理类
// *
// * @author 袁洋 2014年8月27日
// */
//public final class ErrorTypeManager {
//	private static final Map<String, IErrorType> TYPE_MAP;
//	static {
//		final List<IErrorType> errorTypeList = new ArrayList<IErrorType>();
//		errorTypeList
//				.addAll(EnumTypeContainer
//						.getEnumType(
//								IErrorType.class,
//								"com.yueny.superclub.api.*;com.yueny.superclub.singletable.*;"
//										+ "com.yueny.superclub.util.*;com.yueny.superclub.util.*;com.yueny.cropui.*"));
//		TYPE_MAP = extractTypeMap(errorTypeList);
//	}
//
//	/**
//	 * @param code
//	 *            代码
//	 * @return 对应的枚举类型
//	 */
//	public static IErrorType fromCode(final String code) {
//		final IErrorType errorType = TYPE_MAP.get(code);
//		if (errorType == null) {
//			throw new IllegalArgumentException("errorType"
//					+ " don't contain the style——>" + errorType);
//		}
//		return errorType;
//	}
//
//	/**
//	 * @return IErrorType 枚举的所有集合
//	 */
//	public static Map<String, IErrorType> getTypeMap() {
//		return TYPE_MAP;
//	}
//
//	/**
//	 * 获取所有实现 IErrorType 接口的枚举，并将枚举名和枚举存入TYPE_MAP
//	 *
//	 * @param errorTypeList
//	 *            枚举清单
//	 * @return 关联信息
//	 */
//	private static Map<String, IErrorType> extractTypeMap(
//			final List<IErrorType> errorTypeList) {
//		final Map<String, IErrorType> typeMap = new HashMap<String, IErrorType>();
//		for (final IErrorType errorType : errorTypeList) {
//			typeMap.put(errorType.getValue(), errorType);
//		}
//		return typeMap;
//	}
//
// }
