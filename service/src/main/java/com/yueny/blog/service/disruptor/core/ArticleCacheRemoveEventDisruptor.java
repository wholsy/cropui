/**
*
*/
package com.yueny.blog.service.disruptor.core;

import org.springframework.stereotype.Service;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.yueny.blog.service.disruptor.event.ArticleCacheRemoveEvent;
import com.yueny.blog.service.disruptor.factory.ArticleCacheRemoveEventFactory;
import com.yueny.blog.service.disruptor.handler.ArticleCacheRemoveEventHandler;

/**
* 文章缓存删除
*
* @author yueny09 <deep_blue_yang@163.com>
*
* @DATE 2018年1月11日 下午9:15:01
*
*/
@Service
public class ArticleCacheRemoveEventDisruptor extends AbstractEventDisruptor<ArticleCacheRemoveEvent> {
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.yueny.blog.service.disruptor.core.AbstractEventDisruptor#getEventFactory(
	 * )
	 */
	@Override
	EventFactory<ArticleCacheRemoveEvent> getEventFactory() {
		return new ArticleCacheRemoveEventFactory();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.yueny.blog.service.disruptor.core.AbstractEventDisruptor#getEventHandler(
	 * )
	 */
	@Override
	EventHandler<ArticleCacheRemoveEvent> getEventHandler() {
		return new ArticleCacheRemoveEventHandler();
	}

}
