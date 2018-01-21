package com.yueny.blog.service.task;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 标签完整性检查<br>
 * cron = "0 30 * * * ?" <br>
 * 每天整点的30分(一天48次)
 *
 * @author <a href="mailto:yueny09@126.com"> 袁洋 2014年12月29日 下午1:14:00
 *
 * @category tag
 */
@Service
public class FigureTagCheckerTask implements InitializingBean, DisposableBean {
	/** 日志 */
	private static final Logger logger = LoggerFactory.getLogger(FigureTagCheckerTask.class);

	@Override
	public void afterPropertiesSet() throws Exception {
		// .
	}

	@Override
	public void destroy() throws Exception {
		// .
	}

	/**
	 * 定时初始化任务,每天整点的30分
	 */
	@Scheduled(cron = "0 30 * * * ?")
	private void initData() {
		logger.info("【标签完整性检查】-->开始触发.开始初始化时间:{}", new Date());
		// .
	}

}
