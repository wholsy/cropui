package com.yueny.cropui.controller.upload;

import java.io.Serializable;

import com.yueny.rapid.lang.util.mask.pojo.instance.AbstractMaskBo;

import lombok.AllArgsConstructor;
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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UploadResponse extends AbstractMaskBo implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -7149522705294143510L;
	/** 结果码,0为正常,1为错误 */
	private int error = 0;
	/** 结果描述 */
	private String message = "";
	/**
	 * 单文件上传的url地址
	 */
	private String url;
}
