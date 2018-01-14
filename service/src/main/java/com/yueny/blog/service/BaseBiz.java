package com.yueny.blog.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.yueny.kapo.api.pojo.IEntry;
import com.yueny.superclub.api.biz.IBiz;
import com.yueny.superclub.api.pojo.IBo;

/**
 * 基类
 *
 * @author yueny(yueny09@163.com)
 *
 * @date 2015年8月9日 下午7:21:34
 */
public abstract class BaseBiz implements IBiz {
	/**
	 * Logger available to subclasses.
	 */
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	/** */
	@Autowired
	private Mapper mapper;

	/**
	 * 映射数组
	 *
	 * @param <T>
	 *            目标数据类型
	 * @param <S>
	 *            源数据类型
	 * @param sourceList
	 *            源数据列表
	 * @param targetInfo
	 *            目标数据类型信息
	 * @return 映射后的目标数据列表
	 */
	protected <T extends IBo, S extends IEntry> List<T> map(final List<S> sourceList, final Class<T> targetInfo) {
		if (CollectionUtils.isEmpty(sourceList)) {
			logger.warn("使用空数据源进行映射!");
			return Collections.<T>emptyList();
		}

		final List<T> targetList = new ArrayList<T>(sourceList.size());
		for (final S entry : sourceList) {
			// targetList.add(mapper.map(entry, targetInfo));
			targetList.add(map(entry, targetInfo));
		}
		return targetList;
	}

	/**
	 * 将对象实体转换为业务对象
	 *
	 * @param <T>
	 *            业务对象类型
	 * @param <S>
	 *            对象实体类型
	 * @param sourceEntry
	 *            实体对象
	 * @param targetBOClass
	 *            业务对象类
	 * @return 映射后的业务对象
	 */
	protected <T extends IBo, S extends IEntry> T map(final S sourceEntry, final Class<T> targetBOClass) {
		Assert.notNull(sourceEntry, "映射的实体对象不可为空");
		return mapper.map(sourceEntry, targetBOClass);
	}

	/**
	 * 将业务对象转换为对象实体
	 *
	 * @param <T>
	 *            业务对象类型
	 * @param <S>
	 *            对象实体类型
	 * @param sourceBO
	 *            业务对象
	 * @param targetEntryClass
	 *            实体对象类
	 * @return 映射后的实体对象
	 */
	protected <T extends IBo, S extends IEntry> S map(final T sourceBO, final Class<S> targetEntryClass) {
		Assert.notNull(sourceBO, "映射的业务对象不可为空");
		return mapper.map(sourceBO, targetEntryClass);
	}

	/**
	 * 映射数组
	 *
	 * @param <T>
	 *            目标数据类型
	 * @param <S>
	 *            源数据类型
	 * @param sourceList
	 *            源数据列表
	 * @param targetInfo
	 *            目标数据类型信息
	 * @return 映射后的目标数据列表
	 */
	protected <T extends IBo, S> List<T> mapAny(final List<S> sourceList, final Class<T> targetInfo) {
		if (CollectionUtils.isEmpty(sourceList)) {
			logger.warn("使用空数据源进行映射!");
			return Collections.<T>emptyList();
		}

		final List<T> targetList = new ArrayList<T>(sourceList.size());
		for (final S entry : sourceList) {
			targetList.add(mapAny(entry, targetInfo));
		}
		return targetList;
	}

	/**
	 * 将对象实体转换为业务对象
	 *
	 * @param <T>
	 *            业务对象类型
	 * @param <S>
	 *            对象实体类型
	 * @param sourceEntry
	 *            实体对象
	 * @param targetBOClass
	 *            业务对象类
	 * @return 映射后的业务对象
	 */
	protected <T extends IBo, S> T mapAny(final S sourceEntry, final Class<T> targetBOClass) {
		Assert.notNull(sourceEntry, "映射的实体对象不可为空");
		return mapper.map(sourceEntry, targetBOClass);
	}

}
