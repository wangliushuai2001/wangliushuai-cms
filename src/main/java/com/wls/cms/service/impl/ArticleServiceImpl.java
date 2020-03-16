package com.wls.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wls.cms.dao.ArticleMapper;
import com.wls.cms.domain.Article;
import com.wls.cms.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {
  
	 @Autowired
	 private ArticleMapper articleMapper;
	@Override
	public int insert(Article article) {
		// TODO Auto-generated method stub
		return articleMapper.insert(article);
	}

	@Override
	public PageInfo<Article> selects(Article article, Integer page, Integer pageSize) {
		 PageHelper.startPage(page, pageSize);
		 List<Article> list = articleMapper.selects(article);
		return new PageInfo<Article>(list);
	}

	@Override
	public Article select(Integer id) {
		
		return articleMapper.select(id);
	}

	@Override
	public int update(Article article) {
		
		return articleMapper.update(article);
	}

	

}
