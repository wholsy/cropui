package com.yueny.cropui.entry.blog;

/**
 * ��������ʵ��
 * 
 * @author Administrator
 *
 */
public class BlogType {

	private Integer blogCount; // ����
	private Integer id; // ���
	private Integer orderNo; // ���� ��С����������ʾ
	private String typeName; // ������������

	public Integer getBlogCount() {
		return blogCount;
	}

	public Integer getId() {
		return id;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setBlogCount(final Integer blogCount) {
		this.blogCount = blogCount;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public void setOrderNo(final Integer orderNo) {
		this.orderNo = orderNo;
	}

	public void setTypeName(final String typeName) {
		this.typeName = typeName;
	}

}
