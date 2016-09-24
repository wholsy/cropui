package com.yueny.cropui.entry.blog;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * ����ʵ��
 * 
 * @author Administrator
 *
 */
public class Blog {

	private Integer blogCount; // �������� �ǲ���ʵ�����ԣ���Ҫ��
								// ���ݷ������ڹ鵵��ѯ����������
	private BlogType blogType; // ��������
	private Integer clickHit; // �鿴����
	private String content; // ��������
	private String contentNoTag; // �������� ����ҳ��ǩ Lucene�ִ���
	private Integer id; // ���
	private List<String> imagesList = new LinkedList<String>(); // ��������ڵ�ͼƬ
																// ��Ҫ�����б�չʾ��ʾ����ͼ
	private String keyWord; // �ؼ��� �ո����
	private Date releaseDate; // ��������

	private String releaseDateStr; // ���������ַ��� ֻȡ�����
	private Integer replyHit; // �ظ�����
	private String summary; // ժҪ

	private String title; // ���ͱ���

	public Integer getBlogCount() {
		return blogCount;
	}

	public BlogType getBlogType() {
		return blogType;
	}

	public Integer getClickHit() {
		return clickHit;
	}

	public String getContent() {
		return content;
	}

	public String getContentNoTag() {
		return contentNoTag;
	}

	public Integer getId() {
		return id;
	}

	public List<String> getImagesList() {
		return imagesList;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public String getReleaseDateStr() {
		return releaseDateStr;
	}

	public Integer getReplyHit() {
		return replyHit;
	}

	public String getSummary() {
		return summary;
	}

	public String getTitle() {
		return title;
	}

	public void setBlogCount(final Integer blogCount) {
		this.blogCount = blogCount;
	}

	public void setBlogType(final BlogType blogType) {
		this.blogType = blogType;
	}

	public void setClickHit(final Integer clickHit) {
		this.clickHit = clickHit;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public void setContentNoTag(final String contentNoTag) {
		this.contentNoTag = contentNoTag;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public void setImagesList(final List<String> imagesList) {
		this.imagesList = imagesList;
	}

	public void setKeyWord(final String keyWord) {
		this.keyWord = keyWord;
	}

	public void setReleaseDate(final Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setReleaseDateStr(final String releaseDateStr) {
		this.releaseDateStr = releaseDateStr;
	}

	public void setReplyHit(final Integer replyHit) {
		this.replyHit = replyHit;
	}

	public void setSummary(final String summary) {
		this.summary = summary;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

}
