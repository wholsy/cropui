package com.yueny.blog.service.comp.uid;

import org.springframework.stereotype.Component;

import com.yueny.rapid.lang.util.UuidUtil;

/**
 * 个人分类类目code生成器
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2017年6月6日 下午10:09:41
 * @since
 */
@Component
public class OwenerTagCodeGenerate implements ISequenceNoStrategy<String> {

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.yueny.blog.service.uid.ISequenceNoStrategy#getOps(java.lang.Object[])
	 */
	@Override
	public String getOps(final Object... avgs) {
		final String originId = UuidUtil.getUUIDForNumber10X("OT");

		return originId;
	}

}
