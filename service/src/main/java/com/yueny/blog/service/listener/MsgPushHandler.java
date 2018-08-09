package com.yueny.blog.service.listener;

import com.yueny.blog.service.listener.core.MsgListener;
import com.yueny.blog.service.listener.core.MsgQueneFactory;
import com.yueny.blog.service.listener.wrapper.MsgWrapper;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年7月27日 下午7:03:07
 * @since
 */
public class MsgPushHandler {
	private final MsgWrapper msgWrapper;

	/**
	 * step 1
	 */
	public MsgPushHandler(MsgQueneFactory factory) {
		msgWrapper = new MsgWrapper(factory, new MsgListener(), false);
	}

	/**
	 * 初始化之前
	 */
	protected void beforeInit() {
		// .
	}

	public void init() throws Exception {
		beforeInit();

		msgWrapper.init();
	}

}
