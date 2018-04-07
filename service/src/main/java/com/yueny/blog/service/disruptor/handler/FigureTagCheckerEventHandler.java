package com.yueny.blog.service.disruptor.handler;

import java.util.List;

import com.yueny.blog.bo.tag.OwenerTagBo;
import com.yueny.blog.service.disruptor.event.FigureTagCheckerEvent;
import com.yueny.blog.service.table.IArticleBlogService;
import com.yueny.blog.service.table.IOwenerTagService;

/**
 * 标签完整性检查事件<br>
 *
 * Disruptor 定义的事件处理接口，由用户实现，用于处理事件，是 Consumer 的真正实现。
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年12月20日 上午10:04:48
 *
 */
public class FigureTagCheckerEventHandler extends AbstractEventHandler<FigureTagCheckerEvent> {
	/*
	 * (non-Javadoc)
	 *
	 * @see com.lmax.disruptor.EventHandler#onEvent(java.lang.Object, long,
	 * boolean)
	 */
	@Override
	public void onEvent(final FigureTagCheckerEvent event, final long sequence, final boolean endOfBatch) {
		final IArticleBlogService articleBlogService = event.getArticleBlogService();
		final IOwenerTagService owenerTagService = event.getOwenerTagService();
		final String uid = event.getUid();

		// 获取当前用户所拥有的所有有效标签
		final List<OwenerTagBo> owenerTagList = owenerTagService.queryByUid(uid);
		final int sInt = owenerTagList.parallelStream().mapToInt(owenerTagBo -> {
			try {
				// 处理每个用户标签
				final Long owenerTagId = owenerTagBo.getOwenerTagId();
				// 获取用户所拥有该标签的博文信息
				final Long ltsCount = articleBlogService.countBy(owenerTagId);

				final int step = ltsCount.intValue() - owenerTagBo.getCorrelaArticleSum();
				owenerTagService.plusCorrelaArticle(owenerTagBo.getOwenerTagId(), step);

				return 1;
			} catch (final Exception e) {
				logger.error("处理异常：", e);
			}

			return 0;
		}).sum();

		logger.info("Disruptor engine started successfully. 实际处理结果/总数目：{}/{}.", owenerTagList.size(), sInt);
	}

}
