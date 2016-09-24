package com.yueny.blog.service.util;

import java.util.Arrays;

import org.dozer.DozerBeanMapper;
import org.junit.Test;

import com.google.common.collect.Sets;
import com.yueny.blog.bo.article.ArticleBlogBo;
import com.yueny.blog.bo.enums.ArticleSelType;
import com.yueny.blog.entry.article.ArticleBlogEntry;

public class DozerConverterTest {
	@Test
	public void testConverterCollToString() {
		final DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(Arrays.asList("dozer/dozer-config.xml"));

		final ArticleBlogBo bo = new ArticleBlogBo();
		bo.setArticleAlias("文章别名（URL中使用，取代文章ID）");
		bo.setArticleContext("文章内容");
		bo.setArticleDigest("文章摘要 ");
		bo.setArticleMore("更多文章（添加关联的文章url），多个文章地址之间用“,”分隔");
		bo.setArticleTitle("文章标题");
		bo.setSelTypeCode(ArticleSelType.ORIGINAL);
		bo.setArticleId(1L);
		bo.setArticleTagIds(Sets.newHashSet(1L, 2L));
		bo.setOwenerTagIds(Sets.newHashSet(6L, 8L));
		bo.setCategoryTagCodes(Sets.newHashSet("A34567"));

		final ArticleBlogEntry entry = mapper.map(bo, ArticleBlogEntry.class);

		System.out.println(entry);
	}

	@Test
	public void testConverterStringToColl() {
		final DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(Arrays.asList("dozer/dozer-config.xml"));

		final ArticleBlogEntry entry = new ArticleBlogEntry();
		entry.setArticleAlias("文章别名（URL中使用，取代文章ID）");
		entry.setArticleContext("文章内容");
		entry.setArticleDigest("文章摘要 ");
		entry.setArticleMore("更多文章（添加关联的文章url），多个文章地址之间用“,”分隔");
		entry.setArticleTitle("文章标题");
		entry.setSelTypeCode(11);
		entry.setArticleId(1L);
		entry.setArticleTagIds("1,2");
		entry.setOwenerTagIds("8,6");
		entry.setCategoryTagCodes("A34567");

		final ArticleBlogBo bo = mapper.map(entry, ArticleBlogBo.class);

		System.out.println(bo);
	}
}
