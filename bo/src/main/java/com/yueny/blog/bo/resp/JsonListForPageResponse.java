package com.yueny.blog.bo.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class JsonListForPageResponse<T> extends JsonListResponse<T> {
	/**
	 *
	 */
	private static final long serialVersionUID = 9163369797333712558L;
	/**
	 * 分页 html
	 */
	@Getter
	@Setter
	@JsonProperty(value = "pages")
	private String pages;

}
