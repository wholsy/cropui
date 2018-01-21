package com.yueny.blog.service.table.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yueny.blog.dao.console.user.IUserInfoDao;
import com.yueny.blog.dao.console.user.IUserInfoPassportDao;
import com.yueny.blog.entry.console.user.UserInfoEntry;
import com.yueny.blog.service.BaseBiz;
import com.yueny.blog.service.table.IUserPassportService;
import com.yueny.rapid.lang.exception.invalid.InvalidException;
import com.yueny.rapid.lang.util.StringUtil;
import com.yueny.superclub.util.web.security.exception.enums.UserSecurityResultCodeType;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年11月30日 下午5:02:37
 *
 */
@Service
public class UserPassportServiceImpl extends BaseBiz implements IUserPassportService {
	@Autowired
	private IUserInfoDao userInfoDao;
	@Autowired
	private IUserInfoPassportDao userInfoPassportDao;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.yueny.blog.service.cas.user.IUserPassportService#getMatch(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public boolean getMatch(final String loginName, final String cryptPassword) throws InvalidException {
		final String ps = userInfoPassportDao.queryPwdByLoginName(loginName);
		if (StringUtil.isEmpty(ps)) {
			throw new InvalidException(UserSecurityResultCodeType.USER_OR_PASS_ERROR);
		}

		if (!StringUtils.equals(ps, cryptPassword)) {
			throw new InvalidException(UserSecurityResultCodeType.USER_OR_PASS_ERROR);
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.yueny.blog.service.cas.user.IUserPassportService#modifyPassPort(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public boolean modifyPassPort(final String uid, final String passport) throws InvalidException {
		final UserInfoEntry ue = userInfoDao.queryByUid(uid);
		if (ue == null) {
			throw new InvalidException(UserSecurityResultCodeType.USER_NOT_EXIST);
		}

		final String md5Passport = passport;
		ue.setPassport(md5Passport);
		return userInfoPassportDao.modifyPassPort(ue);
	}

}
