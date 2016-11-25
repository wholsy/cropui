package com.yueny.cropui.controller.upload;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yueny.rapid.lang.mask.pojo.instance.AbstractMaskBo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 文件上传响应对象
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月21日 下午2:07:01
 *
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(value = { "serialVersionUID" })
public class UploadManagerJsonResponse extends AbstractMaskBo implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -7149522705294143510L;

	/**
	 * currentDirPath
	 */
	@Getter
	@Setter
	// TODO @JsonProperty不生效
	@JsonProperty(value = "current_dir_path")
	private String current_dir_path;
	/**
	 * currentUrl
	 */
	@Getter
	@Setter
	@JsonProperty(value = "current_url")
	private String current_url;
	/** 结果码,0为正常,1为错误 */
	@Getter
	@Setter
	private int error = 0;
	/**
	 * 文件列表
	 */
	@Getter
	@JsonProperty(value = "file_list")
	private List<Hashtable<String, Object>> file_list;
	/** 结果描述 */
	@Getter
	@Setter
	private String message = "";
	/**
	 * moveupDirPath
	 */
	@Getter
	@Setter
	@JsonProperty(value = "moveup_dir_path")
	private String moveup_dir_path;
	/**
	 * demo
	 */
	@Getter
	@Setter
	@JsonProperty(value = "o_name")
	private String name;
	/**
	 * 文件数目
	 */
	@Getter
	@Setter
	@JsonProperty(value = "total_count")
	private int total_count = 0;

	public void setFile_list(final List<Hashtable<String, Object>> list) {
		if (list == null) {
			return;
		}

		file_list = list;
		total_count = list.size();
	}
}
