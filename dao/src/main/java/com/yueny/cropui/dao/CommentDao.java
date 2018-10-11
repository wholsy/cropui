package com.yueny.cropui.dao;

import java.util.List;
import java.util.Map;

import com.yueny.cropui.entry.blog.Comment;

/**
 * ����Dao�ӿ�
 *
 * @author Administrator
 *
 */
public interface CommentDao {

	/**
	 * �������
	 *
	 * @param comment
	 * @return
	 */
	public int add(final Comment comment);

	/**
	 * �޸�����
	 *
	 * @param comment
	 * @return
	 */
	public int update(final Comment comment);

	/**
	 * �����û�������Ϣ
	 *
	 * @param map
	 * @return
	 */
	public List<Comment> list(final Map<String, Object> map);

	/**
	 * ��ȡ�ܼ�¼��
	 *
	 * @param map
	 * @return
	 */
	public Long getTotal(final Map<String, Object> map);

	/**
	 * ɾ��������Ϣ
	 *
	 * @param id
	 * @return
	 */
	public Integer delete(final Integer id);

}
