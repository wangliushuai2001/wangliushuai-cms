package com.wls.cms.service;

import com.github.pagehelper.PageInfo;
import com.wls.cms.domain.Article;
import com.wls.cms.domain.Comment;

public interface CommentService {
	
	//根据文章评论数量排序
	 PageInfo<Article> selectsByCommentNum(Integer page,Integer pageSize);
   
	 //增加评论
	 int insert(Comment comment);
	 
	 //根据文章查询问文章评论
	 PageInfo<Comment> selects(Article article,Integer page,Integer pageSize);
	 
	 

}
