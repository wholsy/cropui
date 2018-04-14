package com.yueny.blog.service.admin.manager.impl;

import com.yueny.blog.bo.console.user.UserInfoBo;
import com.yueny.blog.service.common.IPasswdService;
import com.yueny.blog.service.table.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yueny.blog.service.admin.manager.ILoginManager;
import com.yueny.blog.service.table.IUserPassportService;
import com.yueny.rapid.lang.exception.invalid.InvalidException;

/**
 * 登录管理
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2017年6月8日 下午1:29:51
 * @since
 */
@Service
public class LoginManagerImpl implements ILoginManager {
	@Autowired
	private IUserPassportService userPassportService;
	@Autowired
	private IPasswdService passwdService;
	@Autowired
	private IUserInfoService userInfoService;

	@Override
	public boolean login(final String loginName, final String originalPassword) throws InvalidException {
		// 获取盐值
		//		UserInfoBo userInfo = userInfoService.queryByLoginName(loginName);
//		userInfo.getsalt

		// 数据库存储密文和表单转密文后验证
		String cryptPassword = passwdService.getEncodeData(originalPassword, "");

		return userPassportService.getMatch(loginName, cryptPassword);
	}

}
