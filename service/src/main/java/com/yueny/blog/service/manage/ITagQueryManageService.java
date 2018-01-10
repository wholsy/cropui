package com.yueny.blog.service.manage;

import com.yueny.blog.bo.model.document.OwenerTagsData;
import com.yueny.rapid.lang.exception.DataVerifyAnomalyException;

/**
 * 分类服务
 * 
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2017年12月26日 下午7:37:56
 * @since
 */
public interface ITagQueryManageService {
	/**
	 * 获得指定用户的标签信息
	 *
	 * @throws DataVerifyAnomalyException
	 */
	OwenerTagsData getOwenerTag(String uid) throws DataVerifyAnomalyException;

}
