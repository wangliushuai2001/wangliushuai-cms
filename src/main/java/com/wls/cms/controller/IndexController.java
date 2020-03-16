package com.wls.cms.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.wls.cms.domain.Article;
import com.wls.cms.domain.Category;
import com.wls.cms.domain.Channel;
import com.wls.cms.domain.Collect;
import com.wls.cms.domain.Comment;
import com.wls.cms.domain.Slide;
import com.wls.cms.domain.User;
import com.wls.cms.service.ArticleService;
import com.wls.cms.service.ChannelService;
import com.wls.cms.service.CollectService;
import com.wls.cms.service.CommentService;
import com.wls.cms.service.SlideService;

@Controller
public class IndexController {
   
	 @Resource
	 private ChannelService channelService;
	 
	 @Resource
	 private ArticleService articleService;
	 
	 @Resource
	 private SlideService slideService;
	 
	 @Resource
	 private CommentService commentService;
	 
	 @Resource
	 private CollectService collectService;
	
	 @RequestMapping(value={"","/","index"})
	 public String index(Model m,Article article,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="5")Integer pageSize){
		 article.setStatus(1);//只显示审核过的文章
		 article.setDeleted(0);//只显示未删除的文章
		 m.addAttribute("article", article);//封装条件
		 //查询左侧栏目
		 List<Channel> channels = channelService.selects();
		 if(article.getChannelId()!=null){//如果栏目不为空 则查询栏目下分类的
			 List<Category> categorys = channelService.selectsByChannelId(article.getChannelId());
			 m.addAttribute("categorys", categorys);
		 }
		 if(article.getChannelId()==null){//如果栏目不为空 则查询栏目下分类的
			 article.setHot(1);
			 //查询轮播图
			 List<Slide> slides = slideService.selects();
			 m.addAttribute("slides", slides);
		 }
		 m.addAttribute("channels", channels);
		 
		 //查询所有文章
		 PageInfo<Article> info = articleService.selects(article, page, pageSize);
		 m.addAttribute("info", info);
		 
		 //在右侧显示最新的十篇文章
		 Article article2 = new Article();
		 article2.setStatus(1);
		 article2.setDeleted(0);
		 PageInfo<Article> lastArticles = articleService.selects(article2, 1, 10);
		 m.addAttribute("lastArticles", lastArticles);
		 
		   return "index/index";
        }
		 @RequestMapping("articleDetail")
		 public String articleDetail(HttpSession session,Integer id,Model m,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="5")Integer pageSize){
			 Article article = articleService.select(id);
			 m.addAttribute("article", article);
			 
			 //查询当前文章的评论信息
			 PageInfo<Comment> info = commentService.selects(article, page, pageSize);
			 m.addAttribute("info", info);
			 
			//查询所有文章的评论
			 PageInfo<Article> info2 = commentService.selectsByCommentNum(1, 10);
			 m.addAttribute("info2", info2);
			 
			 //查询该文章是否收藏过
			 User user = (User) session.getAttribute("user");
			 Collect collect=null;
			 if(user!=null){//如果用户已经登陆，则查询是否被查询过
				 collect = collectService.selectByTitleAndUserId(article.getTitle(), user.getId());
			 }
			 m.addAttribute("collect", collect);
			 return "index/articleDetail";
		 }
	 
	   //增加评论
		 @ResponseBody
		 @RequestMapping("addComment")
		 public boolean addComment(Comment comment,Integer articleId,HttpSession session){
			   User user = (User) session.getAttribute("user");
			   if(null==user){
				   return false;//没有登陆的用户不能评论
			   }
			   comment.setUserId(user.getId());
			   comment.setArticleId(articleId);
			   comment.setCreated(new Date());
			  return commentService.insert(comment)>0;
		 }
		 
		//增加收藏 
		 @ResponseBody
		 @RequestMapping("collect")
		 public boolean collect(Collect collect ,HttpSession session){
			   User user = (User) session.getAttribute("user");
			   if(null==user){
				   return false;//没有登陆的用户不能收藏
			   }
			   collect.setUser(user);
			   collect.setCreated(new Date());
			  
			  return collectService.insert(collect)>0;
		 }
		 
		//取消收藏 
		 @ResponseBody
		 @RequestMapping("deleteCollect")
		 public boolean deleteCollect(Integer id){
			  
			  return collectService.delete(id)>0;
		 }
}
