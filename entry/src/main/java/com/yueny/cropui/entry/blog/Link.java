package com.yueny.cropui.entry.blog;

/**
 * ��������ʵ��
 * 
 * @author Administrator
 *
 */
public class Link {

	private Integer id; // ���
	private String linkName; // ��������
	private String linkUrl; // ���ӵ�ַ
	private Integer orderNo; // ������� ��С��������

	public Integer getId() {
		return id;
	}

	public String getLinkName() {
		return linkName;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public void setLinkName(final String linkName) {
		this.linkName = linkName;
	}

	public void setLinkUrl(final String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public void setOrderNo(final Integer orderNo) {
		this.orderNo = orderNo;
	}

}
