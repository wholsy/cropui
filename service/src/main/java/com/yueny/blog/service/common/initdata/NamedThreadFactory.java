package com.yueny.blog.service.common.initdata;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * InternalThreadFactory.
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年3月16日 上午3:35:48
 *
 */
public class NamedThreadFactory implements ThreadFactory {
	private static final AtomicInteger POOL_SEQ = new AtomicInteger(1);

	private final boolean mDaemo;

	private final ThreadGroup mGroup;

	private final String mPrefix;

	private final AtomicInteger mThreadNum = new AtomicInteger(1);

	public NamedThreadFactory() {
		this("pool-" + POOL_SEQ.getAndIncrement(), false);
	}

	public NamedThreadFactory(final String prefix) {
		this(prefix, false);
	}

	public NamedThreadFactory(final String prefix, final boolean daemo) {
		mPrefix = prefix + "-thread-";
		mDaemo = daemo;
		final SecurityManager s = System.getSecurityManager();
		mGroup = (s == null) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
	}

	public ThreadGroup getThreadGroup() {
		return mGroup;
	}

	@Override
	public Thread newThread(final Runnable runnable) {
		final String name = mPrefix + mThreadNum.getAndIncrement();
		final Thread ret = new Thread(mGroup, runnable, name, 0);
		ret.setDaemon(mDaemo);

		// 生成该线程的MDC上下文
		// .
		return ret;
	}
}