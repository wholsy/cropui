package com.yueny.blog.service.disruptor.event;

import com.yueny.blog.service.disruptor.api.SyntonyHandlerFunction;

import lombok.Getter;
import lombok.Setter;

/**
 * 文章缓存删除事件<br>
 * 在 Disruptor 的语义中，生产者和消费者之间进行交换的数据被称为事件(Event)。它不是一个被 Disruptor 定义的特定类型，而是由
 * Disruptor 的使用者定义并指定。
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年12月20日 上午10:02:41
 *
 */
public class ArticleCacheRemoveEvent implements IEvent {
	// @Getter
	// @Setter
	// private String logData;

	/**
	 * 执行逻辑体
	 */
	@Getter
	@Setter
	private SyntonyHandlerFunction syntonyExecute;

}
