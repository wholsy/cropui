package com.yueny.blog.service.manage;

import com.yueny.blog.bo.model.condition.ArticlePublishedCondition;
import com.yueny.rapid.lang.agent.UserAgentResource;
import com.yueny.rapid.lang.exception.DataVerifyAnomalyException;

/**
 * 文章管理服务
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月1日 下午6:50:38
 *
 */
public interface IArticleManageService {

	/**
	 * 新增博文
	 *
	 * @param uid
	 * @param condition
	 * @param agent
	 *            浏览器信息
	 * @return 文章对外ID
	 * @throws DataVerifyAnomalyException
	 */
	String addBlog(String uid, final ArticlePublishedCondition condition, UserAgentResource agent)
			throws DataVerifyAnomalyException;

	/**
	 * 删除博文
	 *
	 * @param articleBlogId
	 *            文章对外ID
	 */
	boolean delBlog(String articleBlogId) throws DataVerifyAnomalyException;

	/**
	 * 修改博文
	 *
	 * @param uid
	 * @param condition
	 * @param agent
	 *            浏览器信息
	 * @return 文章对外ID
	 * @throws DataVerifyAnomalyException
	 */
	String editBlog(String uid, final ArticlePublishedCondition condition, UserAgentResource agent)
			throws DataVerifyAnomalyException;

}
