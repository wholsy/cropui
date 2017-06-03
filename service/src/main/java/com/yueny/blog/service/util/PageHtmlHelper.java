package com.yueny.blog.service.util;

import com.yueny.blog.bo.utils.PagerUT;

public class PageHtmlHelper {
	// equals PageCond#DEFAULT_PAGE_SIZE_PER_PAGE
	protected static final int DEFAULT_PAGE_SIZE = 10;

	public static String getPageHtmlByPage(Long allcount, int pageno) {
		return getPageHtmlByPage(allcount, "", pageno, DEFAULT_PAGE_SIZE);
	}

	public static String getPageHtmlByPage(Long allcount, int pageno, int pagesize) {
		return getPageHtmlByPage(allcount, "", pageno, pagesize);
	}

	public static String getPageHtmlByPage(Long allcount, String args, int pageno) {
		return getPageHtmlByPage(allcount, args, pageno, DEFAULT_PAGE_SIZE);
	}

	/**
	 * @param allcount
	 *            eg: 200
	 * @param args
	 *            eg: ...
	 * @param pageno
	 *            eg: 2
	 * @param pagesize
	 *            eg: 10
	 * @return
	 */
	public static String getPageHtmlByPage(Long allcount, String args, int pageno, int pagesize) {
		return PagerUT.pages(pagesize, allcount, pageno, "javascript:page(%s,'" + args + "');", 3);
	}

}
