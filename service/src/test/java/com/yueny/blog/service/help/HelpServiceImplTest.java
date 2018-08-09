package com.yueny.blog.service.help;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yueny.blog.bo.model.statistics.month.HisDevReportMonthData;
import com.yueny.blog.service.BaseBizTest;
import com.yueny.blog.service.IHelpService;
import com.yueny.blog.service.impl.HelpServiceImpl;

/**
 * @author 袁洋 2015年8月10日 下午4:32:02
 *
 */
public class HelpServiceImplTest extends BaseBizTest {
	@Autowired
	private IHelpService helpService;

	/**
	 * Test for method {@link HelpServiceImpl#queryHisDevReportsMonth()}
	 *
	 */
	@Test
	public void testQueryHisDevReportsMonth() {
		final HisDevReportMonthData hisDevReportMonthData = helpService
				.queryHisDevReportsMonth();

		System.out.println(hisDevReportMonthData);
	}

}
