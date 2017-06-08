package com.yueny.blog.service.uid;

/**
 * 序列号生成策略
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2017年6月6日 下午10:11:27
 * @since
 */
public interface ISequenceNoStrategy<R> {
	/**
	 * @param avgs
	 *            协议入参
	 * @return 序列号
	 */
	R getOps(final Object... avgs);

}
