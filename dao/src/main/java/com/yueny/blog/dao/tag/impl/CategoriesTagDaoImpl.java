package com.yueny.blog.dao.tag.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.yueny.blog.dao.tag.ICategoriesTagDao;
import com.yueny.blog.entry.tag.CategoriesTagEntry;
import com.yueny.kapo.api.annnotation.DbSchemaType;
import com.yueny.kapo.core.condition.builder.QueryBuilder;
import com.yueny.kapo.core.condition.column.operand.enums.BasicSqlOperand;
import com.yueny.kapo.core.dao.SingleTableDao;

/**
 * 文章标签类目持久层操作
 *
 * @author 袁洋 2015年8月24日 下午1:46:10
 *
 */
@DbSchemaType("BLOG")
@Repository
public class CategoriesTagDaoImpl extends SingleTableDao<CategoriesTagEntry> implements ICategoriesTagDao {
	@Override
	public List<CategoriesTagEntry> queryByParentTagCode(final String categoriesParentTagCode) {
		final QueryBuilder builder = QueryBuilder.builder().where("CATEGORIES_PARENT_CODE", categoriesParentTagCode)
				.build();

		return super.queryListByColumns(builder);
	}

	@Override
	public List<CategoriesTagEntry> queryByTagCode(final Set<String> categoriesCodes) {
		final QueryBuilder builder = QueryBuilder.builder()
				.where("CATEGORIES_TAG_CODE", BasicSqlOperand.IN, categoriesCodes).build();

		return super.queryListByColumns(builder);
	}

	@Override
	public CategoriesTagEntry queryByTagCode(final String categoriesTagCode) {
		final QueryBuilder builder = QueryBuilder.builder().where("CATEGORIES_TAG_CODE", categoriesTagCode).build();
		return super.queryByColumns(builder);
	}

	@Override
	public Long queryCountByParentTagCode(final String categoriesParentCode) {
		final QueryBuilder builder = QueryBuilder.builder().where("CATEGORIES_PARENT_CODE", categoriesParentCode)
				.build();

		return super.queryCountByColumns(builder);
	}

}
