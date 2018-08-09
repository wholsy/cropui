package com.yueny.blog.service.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yueny.blog.bo.ViewerRecordBo;
import com.yueny.blog.dao.more.IViewerRecordDao;
import com.yueny.blog.entry.ViewerRecordEntry;
import com.yueny.blog.service.BaseBiz;
import com.yueny.blog.service.IViewerRecordService;
import com.yueny.blog.service.disruptor.api.SyntonyHandlerFunction;
import com.yueny.blog.service.listener.DefaultMsgPusher;
import com.yueny.blog.service.listener.MsgQuene;
import com.yueny.rapid.topic.profiler.ProfilerLog;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年8月2日 下午1:16:59
 * @since
 */
@Service
public class ViewerRecordServiceImpl extends BaseBiz implements IViewerRecordService {
	@Autowired
	private DefaultMsgPusher defaultMsgPusher;
	@Autowired
	private IViewerRecordDao viewerRecordDao;

	@Override
	@ProfilerLog
	public Long insert(final ViewerRecordBo bo) {
		final SyntonyHandlerFunction<Long> syntonyExecuteInstance = new SyntonyHandlerFunction<Long>() {
			@Override
			public Long execute() {
				final ViewerRecordEntry entry = map(bo, ViewerRecordEntry.class);
				return viewerRecordDao.insert(entry);
			}
		};

		defaultMsgPusher.push(MsgQuene.builder().syntonyExecuteInstance(syntonyExecuteInstance).build());

		return 1L;
	}

	@Override
	public List<ViewerRecordBo> queryAll() {
		final List<ViewerRecordEntry> entrys = viewerRecordDao.queryAll();
		if (CollectionUtils.isEmpty(entrys)) {
			return Collections.emptyList();
		}

		final List<ViewerRecordBo> list = map(entrys, ViewerRecordBo.class);
		return list;
	}

}
