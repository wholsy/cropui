package com.yueny.blog.bo.model.document;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.yueny.blog.bo.article.ArticleSimpleBlogBo;
import com.yueny.blog.bo.tag.OwenerTagBo;
import com.yueny.superclub.api.pojo.instance.AbstractBo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户的标签数据对象
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月10日 下午4:28:20
 *
 */
@ToString
@NoArgsConstructor
public class OwenerTagsData extends AbstractBo {
	@Data
	@EqualsAndHashCode(callSuper = false)
	@AllArgsConstructor
	public class OwenerTagSimpleBlogData extends AbstractBo {
		/**
		 *
		 */
		private static final long serialVersionUID = -269549551485353147L;
		/**
		 * 年内的月度简要博文对象<br>
		 */
		private Set<ArticleSimpleBlogBo> list;
		/**
		 * 私有标签
		 */
		private String owenerTagName;
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 8881639347810001234L;

	/**
	 * 用户所拥有的owenerTags<br>
	 */
	@Getter
	@Setter
	private List<OwenerTagBo> owenerTags;
	/**
	 *
	 */
	private final Map<String, Set<ArticleSimpleBlogBo>> simpleBlogMaps = Maps.newHashMap();
	/**
	 * 用户uid
	 */
	@Getter
	@Setter
	private String uid;

	public void addSimpleBlog(final String owenerTagName, final ArticleSimpleBlogBo simpleBlog) {
		if (simpleBlogMaps.containsKey(owenerTagName)) {
			simpleBlogMaps.get(owenerTagName).add(simpleBlog);
		} else {
			simpleBlogMaps.put(owenerTagName, Sets.newHashSet(simpleBlog));
		}
	}

	/**
	 * @return 获取用户标签下的博客概要信息
	 */
	public List<OwenerTagSimpleBlogData> getTagSimpleBlogs() {
		final List<OwenerTagSimpleBlogData> lists = Lists.newArrayList();
		for (final Map.Entry<String, Set<ArticleSimpleBlogBo>> entry : simpleBlogMaps.entrySet()) {
			lists.add(new OwenerTagSimpleBlogData(entry.getValue(), entry.getKey()));
		}
		return lists;
	}
}
