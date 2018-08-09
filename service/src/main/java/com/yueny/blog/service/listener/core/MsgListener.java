package com.yueny.blog.service.listener.core;

import com.yueny.blog.service.disruptor.api.SyntonyHandlerFunction;
import com.yueny.blog.service.listener.MsgQuene;

/**
 * 单消息处理
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年7月27日 下午3:32:23
 * @since
 */
public class MsgListener extends AbstractMsgListener {
	/**
	 * @return
	 */
	@Override
	public boolean onMsg(MsgQuene msgQuene) {
		logger.info("处理 onMsg:{}.", msgQuene);
		SyntonyHandlerFunction<?> syntonyHandlerFunctionInstance = msgQuene.getSyntonyExecuteInstance();
		Object obj = syntonyHandlerFunctionInstance.execute();
		logger.info("处理 onMsg结果 :{}.", obj.toString());

		return true;
	}

}
