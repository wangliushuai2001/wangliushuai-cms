package com.wls.cms.dao;

import java.util.List;

import com.wls.cms.domain.Article;

public interface ArticleMapper {
  
	   /**
	    * 增加文章
	    */
	  int insert (Article article);
	  
	  //文章列表
	  List<Article> selects(Article article);

	  /**
	   * 根据id 查询单个文章
	   */
	  Article select(Integer id);

	   int  update(Article article);
}
