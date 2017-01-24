package com.yueny.blog.service.help.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.yueny.blog.bo.help.AboutUsInfoBo;
import com.yueny.blog.dao.help.IAboutUsInfoDao;
import com.yueny.blog.entry.help.AboutUsInfoEntry;
import com.yueny.blog.service.BaseBiz;
import com.yueny.blog.service.env.CacheDataHandler;
import com.yueny.blog.service.env.CacheListService;
import com.yueny.blog.service.help.IAboutUsInfoService;
import com.yueny.rapid.lang.util.StringUtil;
import com.yueny.superclub.api.constant.Constants;

/**
 * 关于我们信息查询服务
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月11日 上午11:13:06
 *
 */
@Service
public class AboutUsInfoServiceImpl extends BaseBiz implements IAboutUsInfoService {
	@Autowired
	private IAboutUsInfoDao aboutUsInfoDao;
	@Autowired
	private CacheListService<AboutUsInfoBo> cacheListService;

	@Override
	public List<AboutUsInfoBo> queryAll() {
		return cacheListService.cache("queryAll", new CacheDataHandler<List<AboutUsInfoBo>>() {
			@Override
			public List<AboutUsInfoBo> caller() {
				final List<AboutUsInfoEntry> entrys = aboutUsInfoDao.queryAllData();
				if (CollectionUtils.isEmpty(entrys)) {
					Collections.emptyList();
				}

				final List<AboutUsInfoBo> lists = Lists.newArrayList();
				for (final AboutUsInfoEntry entry : entrys) {
					final AboutUsInfoBo bo = map(entry, AboutUsInfoBo.class);

					// 以 "|&|" 分隔
					final StringBuilder sb = new StringBuilder();
					sb.append(Constants.PIPE);
					sb.append(Constants.AND);
					sb.append(Constants.PIPE);

					if (StringUtil.isNotEmpty(entry.getResume())) {
						bo.setResumes(Arrays.asList(StringUtil.split(entry.getResume(), sb.toString())));
					}
					if (StringUtil.isNotEmpty(entry.getContactWay())) {
						bo.setContactWays(Arrays.asList(StringUtil.split(entry.getContactWay(), sb.toString())));
					}

					lists.add(bo);
				}
				return lists;
			}
		}, 10 * 60L);
	}

}
