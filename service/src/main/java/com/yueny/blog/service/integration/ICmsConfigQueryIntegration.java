package com.yueny.blog.service.integration;

import java.util.List;

import com.yueny.cms.api.response.ro.FunctionOpenRo;
import com.yueny.cms.api.response.ro.SubSystemRo;

/**
 * 外部服务
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2017年1月24日 上午11:04:57
 * @since
 */
public interface ICmsConfigQueryIntegration {
	/**
	 * 查询系统的所有功能开放配置
	 */
	List<FunctionOpenRo> queryFunctionOpens();

	/**
	 * 查询子系统信息列表
	 *
	 * @return 系统信息列表
	 */
	List<SubSystemRo> querySubSystemList();

}
