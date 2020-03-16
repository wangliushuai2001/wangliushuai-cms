package com.wls.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wls.cms.domain.Article;

public interface ArticleService {
  
	  /**
	    * 增加文章
	    */
	  int insert (Article article);
	  
	  //文章列表
	  PageInfo<Article> selects(Article article,Integer page,Integer pageSize);

	  //根据id 查询单个文章
	  Article select(Integer id);
	  
	  //修改文章的热门状态  和审核状态
	  int update(Article article);
} 
