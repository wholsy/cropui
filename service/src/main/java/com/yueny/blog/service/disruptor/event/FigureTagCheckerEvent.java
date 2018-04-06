package com.yueny.blog.service.disruptor.event;

import com.yueny.blog.service.table.IArticleBlogService;
import com.yueny.blog.service.table.IOwenerTagService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 标签完整性检查事件<br>
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年1月21日 上午12:03:49
 *
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FigureTagCheckerEvent implements IEvent {
	/**
	 *
	 */
	@Setter
	@Getter
	private IArticleBlogService articleBlogService;
	/**
	 *
	 */
	@Setter
	@Getter
	private IOwenerTagService owenerTagService;
	/**
	 * 待检查标签拥有者 uid
	 */
	@Setter
	@Getter
	private String uid;

}
