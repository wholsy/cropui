package com.yueny.cropui.dao.blog;

import java.util.List;
import java.util.Map;

import com.yueny.cropui.entry.blog.Blog;

/**
 * ����Dao�ӿ�
 * 
 * @author Administrator
 *
 */
public interface BlogDao {

	/**
	 * ���������·ݷ����ѯ
	 * 
	 * @return
	 */
	public List<Blog> countList();

	/**
	 * ��ҳ��ѯ����
	 * 
	 * @return
	 */
	public List<Blog> list(final Map<String, Object> map);

	/**
	 * ��ȡ�ܼ�¼��
	 * 
	 * @param map
	 * @return
	 */
	public Long getTotal(final Map<String, Object> map);

	/**
	 * ͨ��Id����ʵ��
	 * 
	 * @param id
	 * @return
	 */
	public Blog findById(final Integer id);

	/**
	 * ���²�����Ϣ
	 * 
	 * @param blog
	 * @return
	 */
	public Integer update(final Blog blog);

	/**
	 * ��ȡ��һ������
	 * 
	 * @param id
	 * @return
	 */
	public Blog getLastBlog(final Integer id);

	/**
	 * ��ȡ��һ������
	 * 
	 * @param id
	 * @return
	 */
	public Blog getNextBlog(final Integer id);

	/**
	 * ��Ӳ�����Ϣ
	 * 
	 * @param blog
	 * @return
	 */
	public Integer add(final Blog blog);

	/**
	 * ɾ��������Ϣ
	 * 
	 * @param id
	 * @return
	 */
	public Integer delete(final Integer id);

}
