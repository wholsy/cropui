package com.yueny.blog.service.table.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yueny.blog.bo.console.user.UserInfoBo;
import com.yueny.blog.dao.console.user.IUserInfoDao;
import com.yueny.blog.entry.console.user.UserInfoEntry;
import com.yueny.blog.service.BaseBiz;
import com.yueny.blog.service.table.IUserInfoService;

/**
 * 管理员查询服务
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年11月30日 下午5:02:37
 *
 */
@Service
public class UserInfoServiceImpl extends BaseBiz implements IUserInfoService {
	@Autowired
	private IUserInfoDao userInfoDao;

	@Override
	public UserInfoBo queryByLoginName(final String loginName) {
		final UserInfoEntry entry = userInfoDao.queryByLoginName(loginName);
		if (entry == null) {
			return null;
		}

		return map(entry, UserInfoBo.class);
	}

}
