package com.yueny.cropui.entry.blog;

/**
 * ����ʵ��
 * 
 * @author java1234_С��
 *
 */
public class Blogger {

	private Integer id; // ���
	private String imageName; // ����ͷ��
	private String nickName; // �ǳ�
	private String password; // ����
	private String proFile; // ���˼��
	private String sign; // ����ǩ��
	private String userName; // �û���

	public Integer getId() {
		return id;
	}

	public String getImageName() {
		return imageName;
	}

	public String getNickName() {
		return nickName;
	}

	public String getPassword() {
		return password;
	}

	public String getProFile() {
		return proFile;
	}

	public String getSign() {
		return sign;
	}

	public String getUserName() {
		return userName;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public void setImageName(final String imageName) {
		this.imageName = imageName;
	}

	public void setNickName(final String nickName) {
		this.nickName = nickName;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public void setProFile(final String proFile) {
		this.proFile = proFile;
	}

	public void setSign(final String sign) {
		this.sign = sign;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

}
