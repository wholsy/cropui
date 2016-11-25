/**
 *
 */
package com.yueny.blog.bo.model.configuration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import com.yueny.rapid.lang.mask.pojo.instance.AbstractMaskBo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月22日 下午5:20:27
 *
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ThirdPartyCodeData extends AbstractMaskBo {
	@XmlAttribute(name = "channel")
	private String channel;

	@XmlAttribute(name = "code")
	private String code;

}
