package com.yueny.blog.service.listener.wrapper;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yueny.blog.service.listener.MsgQuene;
import com.yueny.blog.service.listener.api.IMsgListener;
import com.yueny.blog.service.listener.core.MsgQueneFactory;
import com.yueny.rapid.lang.thread.factory.NamedThreadFactory;
import com.yueny.rapid.lang.util.hook.ShutdownHookService;

import lombok.Getter;
import lombok.Setter;

/**
 * SingleMsgListener 注册监听
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年7月27日 下午3:34:24
 * @since
 */
public class MsgWrapper {
	private static final Logger log = LoggerFactory.getLogger(MsgWrapper.class);

	// 定时器
	private ScheduledFuture<?> channelFuture;

	/**
	 * 保证任务由一个线程串行执行
	 */
	private final ExecutorService executorService = Executors.newCachedThreadPool();
	/**
	 * 消息队列工厂实例
	 */
	private final MsgQueneFactory factory;

	private final IMsgListener msgListener;
	private final boolean notify;

	/**
	 * 监控周期, 默认30秒
	 */
	@Setter
	@Getter
	private long period = 30L;

	/**
	 * 定时任务执行器
	 */
	private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1,
			new NamedThreadFactory("msgWrapperMonitorTimer", true));

	public MsgWrapper(MsgQueneFactory factory, IMsgListener msgListener, boolean notify) {
		// 验证启动参数
		// checkInitArguments();

		this.factory = factory;

		this.msgListener = msgListener;

		this.notify = notify;

		// 循环设置notify属性
		final Class<?> listenerClass = msgListener.getClass().getSuperclass();
		final Field[] fields = listenerClass.getDeclaredFields();
		for (final Field field : fields) {
			if ("notify".equals(field.getName())) {
				field.setAccessible(true);
				try {
					field.setBoolean(msgListener, notify);
				} catch (final IllegalAccessException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}

	/**
	 * @return 消费
	 */
	private boolean consumeMsg(MsgQuene msgQuene) {
		final IMsgListener listener = this.msgListener;

		/**
		 * 判断是否成功
		 */
		boolean success = false;
		try {
			success = listener.onMsg(msgQuene);
		} catch (final Exception e) {
			log.error("An exception occurred when consuming a message.", e.fillInStackTrace());
		}

		/**
		 * 如果消费失败，则进入错误处理逻辑
		 */
		if (!success) {
			listener.onFailure();
			log.error("消息接收失败。");
			return false;
		}

		// 销毁
		msgQuene.destory();

		return success;
	}

	/**
	 * 服务初始化
	 *
	 * @throws Exception
	 */
	public void init() throws Exception {
		// 在1秒后执行此任务,每次间隔1秒执行一次
		channelFuture = scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					log.info("MsgHandler 任务健康检查！");
				} catch (final Throwable t) { // 防御性容错
					System.out.println(t.getMessage());
					log.error("Unexpected error occur at channel monitor, cause: " + t.getMessage(), t);
				}
			}
		}, 1000L, period * 1000, TimeUnit.MILLISECONDS);

		// 数据组装任务
		processExecutor();

		ShutdownHookService.register(executorService);
		ShutdownHookService.register(scheduledExecutorService);
	}

	/**
	 * 任务初始化 step 2.2
	 */
	private final void processExecutor() {
		final Long sleepTimeoutifAbsent = 3L;
		executorService.submit(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						final MsgQuene msgQuene = factory.getMsg(sleepTimeoutifAbsent);
						if (msgQuene == null) {
							continue;
						}

						log.info("收到消息:{}.", msgQuene);
						consumeMsg(msgQuene);
					}
				} finally {
					// .
				}
			}
		});
	}

}
