package com.yueny.blog.service.listener.core;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import com.yueny.blog.service.listener.MsgQuene;

import lombok.extern.slf4j.Slf4j;

/**
 * 消息队列工厂，Spring 下实例应该唯一。 eny on 2018/7/30 0030.
 */
@Slf4j
public class MsgQueneFactory {
    /**
     * 阻塞队列大小, 默认200
     */
    public final static int defaultCapacity = 20;

    /**
     * 获取Channel内部维护的缓冲队列<br>
     * 数据消费通过Sender.getChannel().getChannelBlockingQueue() 操纵, Channel内部只负责堆积
     */
    private final BlockingQueue<MsgQuene> queue;

    public MsgQueneFactory() {
        this(defaultCapacity);
    }

    /**
     * @param capacity
     *            阻塞队列大小, 默认200
     */
    public MsgQueneFactory(int capacity) {
        this.queue = new ArrayBlockingQueue<>(capacity);
    }

    /**
     * 获取队列数据
     *
     * @param sleepTimeoutifAbsent 如果未取到数据的休眠时间，默认 3。单位秒
     *
     * @return MsgQuene
     * @throws InterruptedException
     */
    public MsgQuene getMsg(long sleepTimeoutifAbsent) {
        MsgQuene msgQuene = null;
        try {
            msgQuene = this.queue.take();
            if (msgQuene == null) {
                // 休息3s
                try {
                    TimeUnit.SECONDS.sleep(sleepTimeoutifAbsent);
                } catch (final Exception e) {
                    log.error("等待过程中出现异常！", e);
                }
            }
        } catch (final Exception e) {
            log.error("防御式异常！", e);
        }

        return msgQuene;
    }

    /**
     * 如果队列是满的话，会阻塞当前线程
     *
     * @param data
     */
    public boolean putMsg(MsgQuene data) throws InterruptedException {
        try {
            // 如果队列是满的话，会阻塞当前线程
            this.queue.put(data);

            return true;
        } catch (final InterruptedException e) {
            log.error("异常!", e);
            throw e;
        }
    }

}
