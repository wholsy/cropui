package com.yueny.blog.service.listener.api;

import com.yueny.blog.service.listener.MsgQuene;

/**
 * 消息监听者
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年7月27日 下午3:30:40
 * @since
 */
public interface IMsgListener {
	/**
	 * 失败 默认打印失败日志 业务端可以重写该方法，重写时建议先调用super.onFailure()
	 */
	void onFailure();

	/**
	 * @return 消息处理
	 */
	boolean onMsg(MsgQuene msgQuene);

}
