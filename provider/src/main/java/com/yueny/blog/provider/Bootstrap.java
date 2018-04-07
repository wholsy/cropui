package com.yueny.blog.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Quick start
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年4月7日 下午9:17:18
 * @since
 */
@SpringBootApplication
@ImportResource(locations = { "classpath:/config/applicationContext-blog.xml" })
@EnableScheduling
// @ComponentScan(basePackages = { "com.miz.alchemist.micros.job",
// "com.miz.alchemist.common.spring.context.config" })
public class Bootstrap {
	/**
	 * 日志记录器
	 */
	private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);

	public static void main(final String[] args) {
		try {
			SpringApplication.run(Bootstrap.class, args);
		} catch (final Exception e) {
			logger.error("服务启动异常:", e);
			e.printStackTrace();
		}
	}
}
