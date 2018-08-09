package com.yueny.blog.service;

import java.util.List;

import com.yueny.blog.bo.ViewerRecordBo;

/**
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年8月2日 下午1:16:20
 * @since
 */
public interface IViewerRecordService {
	/**
	 * 新增
	 *
	 * @return 新增主键
	 */
	Long insert(ViewerRecordBo bo);

	/**
	 */
	List<ViewerRecordBo> queryAll();

}
