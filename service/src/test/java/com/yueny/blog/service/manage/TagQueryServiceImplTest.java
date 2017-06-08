package com.yueny.blog.service.manage;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yueny.blog.bo.model.document.ChartData;
import com.yueny.blog.service.BaseBizTest;
import com.yueny.blog.service.manager.ITagQueryManageService;
import com.yueny.rapid.lang.exception.DataVerifyAnomalyException;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月12日 下午10:20:52
 *
 */
public class TagQueryServiceImplTest extends BaseBizTest {
	@Autowired
	private ITagQueryManageService tagQueryService;

	@Test
	public void testGetChartData() {
		try {
			final ChartData chartData = tagQueryService.getChartData();
			System.out.println(chartData);
		} catch (final DataVerifyAnomalyException e) {
			e.printStackTrace();
		}
	}
}
