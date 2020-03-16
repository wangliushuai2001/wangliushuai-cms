package com.wls.cms.dao;

import java.util.List;

import com.wls.cms.domain.Article;
import com.wls.cms.domain.Comment;

public interface CommentMapper {
  
	 //增加评论
	 int insert(Comment comment);
	 
	 //根据文章查询问文章评论
	 List<Comment> selects(Article article);
	 
	//根据文章评论数量排序
	 List<Article> selectsByCommentNum();
	 
	// 修改评论数 让评论数+1
	 int updateArticle(Integer articleId);
}
