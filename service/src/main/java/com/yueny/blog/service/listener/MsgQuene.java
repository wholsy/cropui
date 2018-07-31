package com.yueny.blog.service.listener;

import com.yueny.blog.service.disruptor.api.SyntonyHandlerFunction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 消息队列
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年7月27日 下午7:03:07
 * @since
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MsgQuene {

	/**
	 * 异步执行类实例，异常会被 com.yueny.blog.service.listener.wrapper.MsgWrapper#
	 * init$processExecutor$consumeMsg 处理
	 */
	@Getter
	private SyntonyHandlerFunction<?> syntonyExecuteInstance;

	/**
	 * 销毁操作
	 */
	public void destory() {
		syntonyExecuteInstance = null;
	}

}
