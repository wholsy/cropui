package com.yueny.cropui.entry.blog;

/**
 * ��ҳModel��
 * 
 * @author
 *
 */
public class PageBean {

	private int page; // �ڼ�ҳ
	private int pageSize; // ÿҳ��¼��
	private int start; // ��ʼҳ

	public PageBean(final int page, final int pageSize) {
		super();
		this.page = page;
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getStart() {
		return (page - 1) * pageSize;
	}

	public void setPage(final int page) {
		this.page = page;
	}

	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
	}

}
