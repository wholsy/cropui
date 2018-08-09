package com.yueny.blog.service.comp.uid;

import org.springframework.stereotype.Component;

import com.yueny.rapid.lang.util.UuidUtil;

/**
 * 文章对外ID生成器
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2017年6月6日 下午10:09:41
 * @since
 */
@Component
public class ArticleBlogIdGeneraterService implements ISequenceNoStrategy<String> {

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.yueny.blog.service.uid.ISequenceNoStrategy#getOps(java.lang.Object[])
	 */
	@Override
	public String getOps(Object... avgs) {
		final StringBuilder unId = new StringBuilder();

		final String originId = UuidUtil.getUUIDForNumber12();
		unId.append("1");
		unId.append(originId);

		return unId.toString();
	}

}
