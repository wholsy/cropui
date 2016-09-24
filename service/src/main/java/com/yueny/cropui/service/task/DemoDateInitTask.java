package com.yueny.cropui.service.task;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * autoReload
 *
 * @author <a href="mailto:yueny09@126.com"> 袁洋 2014年12月29日 下午1:14:00
 *
 * @category tag
 */
@Service
public class DemoDateInitTask {
	/** 日志 */
	private static final Logger logger = LoggerFactory
			.getLogger(DemoDateInitTask.class);

	/**
	 * 定时初始化任务
	 */
	public void autoReload() {
		logger.info("【demo】定时初始化任务-->开始触发，并每隔一分钟触发一次。开始初始化时间:{}", new Date());
	}

}
