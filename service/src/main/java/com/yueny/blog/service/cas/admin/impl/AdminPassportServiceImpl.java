package com.yueny.blog.service.cas.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yueny.blog.dao.cas.admin.IAdminDao;
import com.yueny.blog.dao.cas.admin.IAdminPassportDao;
import com.yueny.blog.service.BaseBiz;
import com.yueny.blog.service.cas.admin.IAdminPassportService;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年11月30日 下午5:02:37
 *
 */
@Service
public class AdminPassportServiceImpl extends BaseBiz implements IAdminPassportService {
	@Autowired
	private IAdminDao adminDao;
	@Autowired
	private IAdminPassportDao passportDao;

	@Override
	public boolean getMatch(final String loginName, final String originalPassword) {
		return true;

		// final String staticPassword =
		// passportDao.queryPwdByLoginName(loginName);
		// // TODO 数据库存储密文和表单明文验证
		// return StringUtils.equals(originalPassword, staticPassword);
	}

	@Override
	public boolean modifyPassPort(final Long loginUserId, final String md5Passport) {
		// final AdminEntry entry = adminDao.queryByID(loginUserId);
		// entry.setPassport(md5Passport);
		//
		// return passportDao.modifyPassPort(entry);
		return false;
	}

}
