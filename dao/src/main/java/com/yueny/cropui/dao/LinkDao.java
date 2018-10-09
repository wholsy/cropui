package com.yueny.cropui.dao;

import java.util.List;
import java.util.Map;

import com.yueny.cropui.entry.blog.Link;

/**
 * ��������Dao�ӿ�
 * 
 * @author Administrator
 *
 */
public interface LinkDao {

	/**
	 * �����������
	 * 
	 * @param link
	 * @return
	 */
	public int add(final Link link);

	/**
	 * �޸���������
	 * 
	 * @param link
	 * @return
	 */
	public int update(final Link link);

	/**
	 * ��������������Ϣ
	 * 
	 * @param map
	 * @return
	 */
	public List<Link> list(final Map<String, Object> map);

	/**
	 * ��ȡ�ܼ�¼��
	 * 
	 * @param map
	 * @return
	 */
	public Long getTotal(final Map<String, Object> map);

	/**
	 * ɾ����������
	 * 
	 * @param id
	 * @return
	 */
	public Integer delete(final Integer id);
}
