package com.wls.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wls.cms.dao.CommentMapper;
import com.wls.cms.domain.Article;
import com.wls.cms.domain.Comment;
import com.wls.cms.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
  
	@Resource
	private CommentMapper commentMapper;
	@Override
	public int insert(Comment comment) {
		try {
			
			//增加评论
			commentMapper.insert(comment);
			//让文章评论数量+1
			commentMapper.updateArticle(comment.getArticleId());
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
		
	}

	@Override
	public PageInfo<Comment> selects(Article article, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Comment> list = commentMapper.selects(article);
		return new PageInfo<>(list);
	}

	@Override
	public PageInfo<Article> selectsByCommentNum(Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Article> num = commentMapper.selectsByCommentNum();
		return new PageInfo<>(num);
	}


}
