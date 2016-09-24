package com.yueny.blog.dao.tag;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.yueny.kapo.api.biz.ISqlMapper;

/**
 * 个人分类类目持久层操作
 *
 * @author 袁洋 2014年8月2日
 */
@Repository
public interface IOwenerTagMapper extends ISqlMapper {
	/**
	 * 文章数目增加step
	 *
	 * @param primaryId
	 *            数据主键
	 * @param step
	 *            增加数目,eg: 1,-1
	 */
	@Update("UPDATE OWENER_TAG SET CORRELA_ARTICLE_SUM=CORRELA_ARTICLE_SUM+#{step} WHERE OWENER_TAG_ID=#{id}")
	int plusCorrelaArticle(@Param("id") long primaryId, @Param("step") int step);
}
