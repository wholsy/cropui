package com.yueny.blog.service.cas.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yueny.blog.bo.cas.user.UserInfoBo;
import com.yueny.blog.dao.cas.user.IUserBaseInfoDao;
import com.yueny.blog.dao.cas.user.IUserBaseInfoExpDao;
import com.yueny.blog.dao.cas.user.IUserInfoDao;
import com.yueny.blog.dao.cas.user.IUserInfoPassportDao;
import com.yueny.blog.entry.cas.user.UserInfoEntry;
import com.yueny.blog.service.BaseBiz;
import com.yueny.blog.service.cas.user.IUserService;

/**
 * 管理员查询服务
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年11月30日 下午5:02:37
 *
 */
@Service
public class UserServiceImpl extends BaseBiz implements IUserService {
	@Autowired
	private IUserBaseInfoDao userBaseInfoDao;
	@Autowired
	private IUserBaseInfoExpDao userBaseInfoExpDao;
	@Autowired
	private IUserInfoDao userInfoDao;
	@Autowired
	private IUserInfoPassportDao userInfoPassportDao;

	@Override
	public UserInfoBo queryByLoginName(final String loginName) {
		final UserInfoEntry entry = userInfoDao.queryByLoginName(loginName);
		if (entry == null) {
			return null;
		}

		return map(entry, UserInfoBo.class);
	}

}
