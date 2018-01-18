package com.yueny.blog.service.admin.manager;

import com.yueny.blog.console.vo.user.UserDisplayVo;

/**
 * 用户展示服务
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2017年6月8日 上午11:54:35
 * @since 1.0.0
 */
public interface IUserDisplayManager {
	/**
	 * @param loginName
	 *            登录用户名
	 * @return 用户显示信息
	 */
	UserDisplayVo display(final String loginName);

}
