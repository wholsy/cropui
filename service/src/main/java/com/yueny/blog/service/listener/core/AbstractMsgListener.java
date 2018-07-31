package com.yueny.blog.service.listener.core;

import com.yueny.blog.service.listener.MsgQuene;
import com.yueny.rapid.lang.thread.factory.NamedThreadFactory;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yueny.blog.service.listener.api.IMsgListener;

import java.util.concurrent.*;

public abstract class AbstractMsgListener implements IMsgListener {
	/**
	 * Logger
	 */
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void onFailure() {
		logger.error("失败！");
	}

	public abstract boolean onMsg(MsgQuene msgQuene);

}
