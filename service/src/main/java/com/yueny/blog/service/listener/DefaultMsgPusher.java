package com.yueny.blog.service.listener;

import com.yueny.blog.service.listener.MsgPushHandler;
import com.yueny.blog.service.listener.core.MsgQueneFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 推送消息
 */
@Component
@Slf4j
public class DefaultMsgPusher implements InitializingBean {
    private MsgQueneFactory factory;
    private MsgPushHandler pushHandler;

    @Override
    public void afterPropertiesSet() throws Exception {
        factory = new MsgQueneFactory();

        pushHandler = new MsgPushHandler(factory);
        pushHandler.init();
    }

    /**
     * 增加推送消息
     *
     * @param data
     */
    public boolean push(MsgQuene data) {
        try {
            // 如果队列是满的话，会阻塞当前线程
            factory.putMsg(data);

            return true;
        } catch (final InterruptedException e) {
            log.error("发送消息发生异常!", e);
        }

        return false;
    }

}
