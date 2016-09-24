/**
 *
 */
package com.yueny.blog.bo.model.configuration;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.yueny.rapid.lang.util.mask.pojo.instance.AbstractMaskBo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月22日 下午5:19:28
 *
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class UploadMethodTypeData extends AbstractMaskBo {
	/**
	 * 扩展名类型
	 */
	@XmlElementWrapper(name = "extTypes")
	@XmlElement(name = "value")
	private List<String> extTypes;
	/**
	 * 上传名称
	 */
	@XmlElement(name = "name")
	private String name;
	/**
	 * 上传类型,如image
	 */
	@XmlElement(name = "type")
	private String type;
}
