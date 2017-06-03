package com.yueny.blog.service.cas.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yueny.blog.bo.cas.admin.AdminBo;
import com.yueny.blog.dao.cas.admin.IAdminDao;
import com.yueny.blog.entry.cas.admin.AdminEntry;
import com.yueny.blog.service.BaseBiz;
import com.yueny.blog.service.cas.admin.IAdminService;

/**
 * 管理员查询服务
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年11月30日 下午5:02:37
 *
 */
@Service
public class AdminServiceImpl extends BaseBiz implements IAdminService {
	@Autowired
	private IAdminDao adminDao;

	@Override
	public AdminBo queryByLoginName(final String loginName) {
		final AdminEntry entry = null;// adminDao.queryByLoginName(loginName);
		if (entry == null) {
			return null;
		}

		return map(entry, AdminBo.class);
	}

}
