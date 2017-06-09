package com.yueny.blog.service.integration;

import java.util.List;

import com.yueny.cms.api.enums.SystemParameterType;
import com.yueny.cms.api.response.ro.FunctionOpenRo;
import com.yueny.cms.api.response.ro.SubSystemRo;
import com.yueny.cms.api.response.ro.SystemParameterRo;

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
	 * 查询系统的功能开放配置
	 *
	 * @param functionCode
	 *            功能编号
	 * @return 功能开放配置
	 */
	FunctionOpenRo queryFunctionOpenByCode(String functionCode);

	/**
	 * 查询系统的所有功能开放配置
	 */
	List<FunctionOpenRo> queryFunctionOpens();

	/**
	 * 查询指定子系统信息
	 *
	 * @return 系统信息
	 */
	SubSystemRo querySubSystem();

	/**
	 * 查询子系统信息列表
	 *
	 * @return 系统信息列表
	 */
	List<SubSystemRo> querySubSystemList();

	/**
	 * 指定的系统参数配置
	 *
	 * @param systemParameterCode
	 *            系统参数编号
	 * @return
	 */
	SystemParameterRo querySystemParameterByCode(SystemParameterType systemParameterType);

}
