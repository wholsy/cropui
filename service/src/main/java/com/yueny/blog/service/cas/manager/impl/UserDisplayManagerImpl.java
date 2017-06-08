package com.yueny.blog.service.cas.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yueny.blog.bo.cas.user.UserBaseInfoBo;
import com.yueny.blog.bo.cas.user.UserBaseInfoExpBo;
import com.yueny.blog.bo.cas.user.UserInfoBo;
import com.yueny.blog.service.cas.manager.IUserDisplayManager;
import com.yueny.blog.service.cas.user.IUserService;
import com.yueny.blog.vo.article.cas.user.UserDisplayVo;
import com.yueny.blog.vo.article.cas.user.UserInfoVo;
import com.yueny.rapid.lang.mask.MaskInfoUtil;

/**
 * 用户展示服务
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2017年6月8日 上午11:55:44
 * @since 1.0.0
 */
@Service
public class UserDisplayManagerImpl implements IUserDisplayManager {
	@Autowired
	private IUserService userService;

	@Override
	public UserDisplayVo display(String loginName) {
		final UserInfoBo userInfoBo = userService.queryByLoginName(loginName);

		final String displayName = MaskInfoUtil.mask(loginName);

		final UserBaseInfoBo base = new UserBaseInfoBo();

		final UserBaseInfoExpBo baseExp = new UserBaseInfoExpBo();

		final UserInfoVo user = new UserInfoVo();
		user.setLaterLoginTime(userInfoBo.getLaterLoginTime());

		return UserDisplayVo.builder().displayName(displayName).userBaseInfo(base).userBaseInfoExp(baseExp)
				.userInfo(user).build();
	}

}
