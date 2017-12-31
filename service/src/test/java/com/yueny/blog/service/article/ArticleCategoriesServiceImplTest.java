package com.yueny.blog.service.article;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yueny.blog.bo.tag.CategoriesTagBo;
import com.yueny.blog.service.BaseBizTest;
import com.yueny.blog.service.categories.ICategoriesTagService;

/**
 * 文章分类类目服务测试用例
 *
 * @author 袁洋 2015年8月24日 下午4:32:04
 *
 */
public class ArticleCategoriesServiceImplTest extends BaseBizTest {
	@Autowired
	private ICategoriesTagService articleCategoriesService;

	/**
	 * Test for method {@link ICategoriesTagService#findArticleCategoriesTree()}
	 *
	 */
	@Test
	public void testArticleCategoriesTree() {
		final List<CategoriesTagBo> articleCategoriesTree = articleCategoriesService
				.findArticleCategoriesTree();

		System.out.println(articleCategoriesTree);
	}

}
