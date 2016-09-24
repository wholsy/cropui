package com.yueny.blog.service.help.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.yueny.blog.bo.help.AboutUsInfoBo;
import com.yueny.blog.dao.help.IAboutUsInfoDao;
import com.yueny.blog.entry.help.AboutUsInfoEntry;
import com.yueny.blog.service.CacheBaseBiz;
import com.yueny.blog.service.help.IAboutUsInfoService;
import com.yueny.rapid.lang.util.StringUtil;
import com.yueny.rapid.lang.util.collect.ArrayUtil;
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
public class AboutUsInfoServiceImpl extends CacheBaseBiz<AboutUsInfoBo> implements IAboutUsInfoService {
	@Autowired
	private IAboutUsInfoDao aboutUsInfoDao;

	@Override
	public List<AboutUsInfoBo> queryAll() {
		return this.cacheList(ArrayUtil.newArray("queryAll"), new ICacheExecutor<List<AboutUsInfoBo>>() {
			@Override
			public List<AboutUsInfoBo> execute() {
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
		}, 10, TimeUnit.MINUTES);
	}

}
