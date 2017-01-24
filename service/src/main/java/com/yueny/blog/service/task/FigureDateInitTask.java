package com.yueny.blog.service.task;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 当前时间<br>
 * cron = "0 30 * * * ?" <br>
 * 每天整点的30分
 *
 * @author <a href="mailto:yueny09@126.com"> 袁洋 2014年12月29日 下午1:14:00
 *
 * @category tag
 */
@Service
public class FigureDateInitTask implements InitializingBean, DisposableBean {
	/** 日志 */
	private static final Logger logger = LoggerFactory
			.getLogger(FigureDateInitTask.class);

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("【demo】afterPropertiesSet FigureDateInitTask!");
	}

	@Override
	public void destroy() throws Exception {
		logger.info("【demo】destroy FigureDateInitTask!");
	}

	/**
	 * 定时初始化任务,每天整点的30分
	 */
	@Scheduled(cron = "0 30 * * * ?")
	private void initData() {
		logger.info("【demo】定时初始化任务-->开始触发，并每隔一分钟触发一次。开始初始化时间:{}", new Date());
	}

}
