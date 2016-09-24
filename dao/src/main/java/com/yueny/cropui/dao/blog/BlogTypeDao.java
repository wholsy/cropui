package com.yueny.cropui.dao.blog;

import java.util.List;
import java.util.Map;

import com.yueny.cropui.entry.blog.BlogType;

/**
 * ��������Dao�ӿ�
 * 
 * @author Administrator
 *
 */
public interface BlogTypeDao {

	/**
	 * ��ѯ���в������� �Լ���Ӧ�Ĳ�������
	 * 
	 * @return
	 */
	public List<BlogType> countList();

	/**
	 * ͨ��id��ѯ��������
	 * 
	 * @param id
	 * @return
	 */
	public BlogType findById(final Integer id);

	/**
	 * ��ҳ��ѯ���������Ϣ
	 * 
	 * @param map
	 * @return
	 */
	public List<BlogType> list(final Map<String, Object> map);

	/**
	 * ��ȡ�ܼ�¼��
	 * 
	 * @param map
	 * @return
	 */
	public Long getTotal(final Map<String, Object> map);

	/**
	 * ��Ӳ��������Ϣ
	 * 
	 * @param blogType
	 * @return
	 */
	public Integer add(final BlogType blogType);

	/**
	 * �޸Ĳ��������Ϣ
	 * 
	 * @param blogType
	 * @return
	 */
	public Integer update(final BlogType blogType);

	/**
	 * ɾ�����������Ϣ
	 * 
	 * @param id
	 * @return
	 */
	public Integer delete(final Integer id);
}
