package com.yueny.blog.service.disruptor.event;

/**
 * 日志事件
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年12月20日 上午10:02:41
 *
 */
public class LogEvent {
	private String logData;

	public void set(final String logData) {
		this.logData = logData;
	}
}
