package com.yueny.blog.bo.resp;

import com.yueny.rapid.data.resp.pojo.response.JsonListResponse;

import lombok.Getter;
import lombok.Setter;

/**
 * 回传分页信息的json 列表响应对象
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2017年6月3日 下午2:47:28
 */
public class JqGridDataJsonListForPageResponse<T> extends JsonListResponse<T> {
	/**
	 *
	 */
	private static final long serialVersionUID = 9163369797333712558L;
	
	/**
	 * 每页中的记录行数
	 */
	@Getter
	@Setter
	private Integer rows=0;
	/**
	 * 排序的方式
	 */
	@Getter
	@Setter
	private String sord;  
	
	/**
	 * 用于排序的列名
	 */
	@Getter
	@Setter
	private String sidx;        
	
	/**
	 * /是否用于查询的请求
	 */
	@Getter
	@Setter
	private String search;
	
	/**
	 * 当前页码的数据
	 */
	@Getter
	@Setter
	private Integer currentpage = 1;
	/**
	 * 页码总数的数据
	 */
	@Getter
	@Setter
	private Integer total = 0;
	/**
	 *代表数据行总数的数据
	 */
	@Getter
	@Setter
	private Long records = 0L;
	
}
