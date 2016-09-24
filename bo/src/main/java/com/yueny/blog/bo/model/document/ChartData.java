package com.yueny.blog.bo.model.document;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.yueny.superclub.api.pojo.instance.AbstractBo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 标签数据
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月12日 下午8:59:37
 *
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChartData extends AbstractBo {
	/**
	 *
	 */
	private static final long serialVersionUID = 5193556557452059331L;
	/**
	 * 子节点
	 */
	@JsonProperty(value = "children")
	private final List<ChartData> children = Lists.newArrayList();
	@Getter
	@Setter
	@JsonIgnore
	// @JsonInclude(value = Include.NON_NULL)
	private String code;
	/**
	 * id
	 */
	@Getter
	@Setter
	@JsonProperty(value = "id")
	private Long id;
	/**
	 * name
	 */
	@Getter
	@Setter
	@JsonProperty(value = "name")
	private String name;

	/**
	 * 添加子节点
	 */
	public void addChildrenNodes(final ChartData chartData) {
		children.add(chartData);
	}

	/**
	 * 添加子节点
	 */
	public void addChildrenNodes(final List<ChartData> chartDatas) {
		children.addAll(chartDatas);
	}

	public List<ChartData> getChildren() {
		return Collections.unmodifiableList(children);
	}
}
