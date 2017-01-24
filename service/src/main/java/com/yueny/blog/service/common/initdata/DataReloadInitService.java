package com.yueny.blog.service.common.initdata;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.yueny.blog.service.integration.ICmsConfigQueryIntegration;
import com.yueny.cms.api.response.ro.FunctionOpenRo;
import com.yueny.cms.api.response.ro.SubSystemRo;
import com.yueny.rapid.lang.util.enums.EnableType;

/**
 * 数据初始化
 *
 * @author yueny09
 *
 */
@Service
public final class DataReloadInitService extends AbstractDataInitService {
	@Autowired
	private ICmsConfigQueryIntegration cmsConfigQueryIntegration;
	/**
	 * 延迟轮询时间
	 */
	@Value("${app.system.retry.period.seconds}")
	private String retryPeriod;

	@Override
	protected Long retryPeriod() {
		try {
			return Long.parseLong(retryPeriod);
		} catch (final Exception e) {
			logger.error("延迟轮询时间获取失败！", e);
		}
		return super.retryPeriod();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.yueny.cropui.service.initdata.AbstractDataInitService#selectData()
	 */
	@Override
	public Map<String, Object> selectData() {
		final Map<String, Object> pushData = Maps.newHashMap();

		try {
			final List<SubSystemRo> allSubSystem = cmsConfigQueryIntegration.querySubSystemList();
			for (final SubSystemRo ro : allSubSystem) {
				pushData.put(ro.getSystemCode(), ro.getDomainUrl());
			}

			/* 功能开放状态配置 */
			final List<FunctionOpenRo> functionOpens = cmsConfigQueryIntegration.queryFunctionOpens();
			for (final FunctionOpenRo ro : functionOpens) {
				final EnableType enableType = EnableType.byCode(ro.getStatus());
				if (enableType == null) {
					continue;
				}
				pushData.put(ro.getFunctionCode(), enableType.enable());
			}
		} catch (final Exception e) {
			logger.error("配置数据初始化异常！", e);
		}
		return pushData;
	}

}
