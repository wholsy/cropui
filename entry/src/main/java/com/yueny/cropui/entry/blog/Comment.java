package com.yueny.cropui.entry.blog;

import java.util.Date;

/**
 * ����ʵ��
 * 
 * @author Administrator
 *
 */
public class Comment {

	private Blog blog; // �����۵Ĳ���
	private Date commentDate; // ��������
	private String content; // ��������
	private Integer id; // ���
	private Integer state; // ���״̬ 0 ����� 1 ���ͨ�� 2 ���δͨ��
	private String userIp; // �û�IP

	public Blog getBlog() {
		return blog;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public String getContent() {
		return content;
	}

	public Integer getId() {
		return id;
	}

	public Integer getState() {
		return state;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setBlog(final Blog blog) {
		this.blog = blog;
	}

	public void setCommentDate(final Date commentDate) {
		this.commentDate = commentDate;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public void setState(final Integer state) {
		this.state = state;
	}

	public void setUserIp(final String userIp) {
		this.userIp = userIp;
	}

}
