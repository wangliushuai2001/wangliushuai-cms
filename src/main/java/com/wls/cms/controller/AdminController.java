package com.wls.cms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.wls.cms.domain.Article;
import com.wls.cms.domain.User;
import com.wls.cms.service.ArticleService;
import com.wls.cms.service.UserService;

@RequestMapping("admin")
@Controller
public class AdminController {
      
	  @Resource
	  private ArticleService  articleService;
	  @Resource
	  private UserService userService;
	 
	  @RequestMapping(value={"","/","index"})
	  public String index(){
		    
		   return "admin/index";
	  }
	  
	  //查询用户列表
	  @RequestMapping("users")
	  public String users(Model m,User user,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="4")Integer pageSize){
		  PageInfo<User> info = userService.selects(user, page, pageSize);
		  m.addAttribute("info", info);
		  m.addAttribute("user", user);
		  return "admin/users";
	  }
	  
	  @ResponseBody
	  @RequestMapping("updateUser")
	  public boolean updateUser(User user){
		   
		  return userService.update(user)>0;
	  }
	  
	  
	  
	  //进到文章审核页面
	  @RequestMapping("articles")
	  public String articles(Model m,Article article,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="4")Integer pageSize){
		  PageInfo<Article> info = articleService.selects(article, page, pageSize);
		  m.addAttribute("info", info);
		  m.addAttribute("article", article);
		  return "admin/articles";
	  }
	  
	  
	  //修改文章热门状态
	  @ResponseBody
	  @RequestMapping("update")
	  public boolean update(Article article){
		  int i = articleService.update(article);
		  return i>0; 
	  }
	  
	  
	     /**
		  * 根据id 查询单个文章   1个文章  详情
		  */
		 @ResponseBody
		 @RequestMapping("articleDetail")
		 public  Article articleDetail(Integer id){
			 return articleService.select(id);
		 }
}
