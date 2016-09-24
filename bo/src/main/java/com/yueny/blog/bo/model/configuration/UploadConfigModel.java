/**
 *
 */
package com.yueny.blog.bo.model.configuration;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Maps;
import com.yueny.rapid.lang.util.mask.pojo.instance.AbstractMaskBo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ycs
 *
 *         created by 2015年12月4日 下午9:16:53
 */
@JsonIgnoreProperties(value = { "serialVersionUID", "uploadMaps" })
@XmlRootElement(name = "settings")
@XmlAccessorType(XmlAccessType.FIELD)
public class UploadConfigModel extends AbstractMaskBo {

	private static Map<String, UploadMethodTypeData> uploadMaps;
	/**
	 * 上传根路径，可以指定绝对路径，比如 /var/www/attached/<br>
	 * 此处为文件保存根目录路径<br>
	 * 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\
	 * 文件夹中
	 */
	@Getter
	@Setter
	@XmlElement(name = "root", required = true)
	private String root;
	/**
	 * 上传目录,'upload'
	 */
	@Getter
	@Setter
	@XmlElement(name = "upload-base", required = true)
	private String uploadBase;
	/**
	 *
	 */
	@Setter
	@XmlElementWrapper(name = "uploads")
	@XmlElement(name = "upload", required = true)
	private List<UploadMethodTypeData> uploadBeans;

	/**
	 * 文件服务器uri,根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/
	 */
	@Getter
	@Setter
	@XmlElement(name = "uri", required = true)
	private String uri;

	private Map<String, UploadMethodTypeData> assemblyUploadMaps() {
		if (uploadMaps != null) {
			return uploadMaps;
		}

		final Map<String, UploadMethodTypeData> maps = Maps.newHashMap();
		for (final UploadMethodTypeData uploadMethodTypeData : uploadBeans) {
			if (uploadMethodTypeData.getExtTypes() == null) {
				uploadMethodTypeData.setExtTypes(Collections.emptyList());
			}
			maps.put(uploadMethodTypeData.getType(), uploadMethodTypeData);
		}
		return maps;
	}

	/**
	 * @param type
	 *            上传类型,如image
	 */
	public UploadMethodTypeData getUploadByType(final String type) {
		return assemblyUploadMaps().get(type);
	}
}
