package com.yueny.blog.service.util.dozer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dozer.CustomConverter;
import org.dozer.MappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import com.yueny.rapid.lang.util.StringUtil;

/**
 * 集合类型转换
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月1日 下午5:39:54
 *
 */
public class SetsConverter implements CustomConverter {
	private static Class<?> extractClass(final Type genericSuperType) {
		if (!(genericSuperType instanceof ParameterizedType)) {
			return null;
		}

		final ParameterizedType genericSuperClass = (ParameterizedType) genericSuperType;
		final Type[] actualTypes = genericSuperClass.getActualTypeArguments();
		if (actualTypes.length == 0) {
			return null;
		}

		final Type firstType = actualTypes[0];
		if (!(firstType instanceof Class)) {
			return null;
		}

		return (Class<?>) firstType;
	}

	/**
	 * Logger available to subclasses.
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	// sourceFieldValue 原数据
	// sourceClass 原数据类型
	// destinationClass 目标类型
	/*
	 * (non-Javadoc)
	 *
	 * @see org.dozer.CustomConverter#convert(java.lang.Object,
	 * java.lang.Object, java.lang.Class, java.lang.Class)
	 */
	@Override
	public Object convert(final Object existingDestinationFieldValue, final Object sourceFieldValue,
			final Class<?> destinationClass, final Class<?> sourceClass) {
		if ((sourceFieldValue == null)
				|| ((sourceFieldValue instanceof java.lang.String) && StringUtils.isBlank((String) sourceFieldValue))) {
			return null;
		}

		// Set转其他(字符串)
		if (sourceFieldValue instanceof java.util.Set) {
			// 集合转字符串
			if (destinationClass.equals(String.class)) {
				return convertFromCollToString((Set<?>) sourceFieldValue);
			}
		}

		// 其他类型转集合
		if (Collection.class.isAssignableFrom(destinationClass)) {
			// destinationClass是Collection的子类
			if (sourceClass.equals(String.class)) {
				return convertFromStringToColl((String) sourceFieldValue, sourceClass, destinationClass);
			}
		}

		throw new MappingException("Can't convert from(" + sourceFieldValue + " to " + destinationClass.getName()
				+ ") with converter (" + this.getClass().getName() + ")!");
	}

	/**
	 * @param sourceFieldValue
	 * @return
	 */
	private String convertFromCollToString(final Set<?> sourceFieldValue) {
		if (sourceFieldValue == null) {
			return null;
		}

		return Joiner.on(",").join(sourceFieldValue);
	}

	/**
	 * @param sourceFieldValue
	 *            ,eg:9,11
	 * @return
	 */
	private Collection<?> convertFromStringToColl(final String sourceFieldValue, final Class<?> sourceClass,
			final Class<?> destinationClass) {
		if (sourceFieldValue == null) {
			return CollectionUtils.emptyCollection();
		}

		final String[] sa = StringUtil.split(sourceFieldValue, ",");

		// TODO 集合类型, 此处经过dozer转出来的为String
		if (Set.class.isAssignableFrom(destinationClass)) {
			return Sets.newHashSet(sa);
		}

		throw new MappingException("Can't convert from(" + sourceFieldValue + " to " + destinationClass.getName()
				+ ") with converter (" + this.getClass().getName() + ")!");
	}

	/**
	 * 查看类的注解
	 *
	 * @param clazz
	 *            类
	 * @param index
	 *            index
	 * @return 注解
	 */
	private Class<?> getGenericType(final Class<?> clazz, final int index) {
		final Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		final Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			throw new RuntimeException("Index outof bounds");
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class<?>) params[index];
	}

}
