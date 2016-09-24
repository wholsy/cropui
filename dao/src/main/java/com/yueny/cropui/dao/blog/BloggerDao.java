package com.yueny.cropui.dao.blog;

import com.yueny.cropui.entry.blog.Blogger;

/**
 * ����Dao�ӿ�
 * 
 * @author java1234_С��
 *
 */
public interface BloggerDao {

	/**
	 * ��ѯ������Ϣ
	 * 
	 * @return
	 */
	public Blogger find();

	/**
	 * ͨ���û�����ѯ�û�
	 * 
	 * @param userName
	 * @return
	 */
	public Blogger getByUserName(final String userName);

	/**
	 * ���²�����Ϣ
	 * 
	 * @param blogger
	 * @return
	 */
	public Integer update(final Blogger blogger);
}
