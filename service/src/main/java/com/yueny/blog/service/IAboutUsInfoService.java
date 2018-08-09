package com.yueny.blog.service;

import java.util.List;

import com.yueny.blog.bo.AboutUsInfoBo;

/**
 * 关于我们信息查询服务
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月11日 上午11:12:11
 *
 */
public interface IAboutUsInfoService {
	/**
	 */
	List<AboutUsInfoBo> queryAll();
}
