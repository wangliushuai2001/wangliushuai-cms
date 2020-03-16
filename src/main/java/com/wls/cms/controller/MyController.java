package com.wls.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.javassist.bytecode.analysis.MultiArrayType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.wls.cms.domain.Article;
import com.wls.cms.domain.Collect;
import com.wls.cms.domain.User;
import com.wls.cms.service.ArticleService;
import com.wls.cms.service.CollectService;

/**
 * 
 * @ClassName: MyController 
 * @Description: 个人中心
 * @author: MECHREVO
 * @date: 2020年3月4日 上午10:51:11
 */
@RequestMapping("my")
@Controller
public class MyController {
      
	   @Resource
	   private ArticleService  articleService;
	
	   @Resource
	   private CollectService  collectService;
	
	 /**
	  * 
	  * @Title: index 
	  * @Description: 进入个人中心的首页
	  * @return
	  * @return: String
	  */
	 @RequestMapping(value={"","/","index"})
	 public String index(){
		 
		 return "my/index";	
     }
	 
	 /**
	  * 根据id 查询单个文章
	  */
	 @ResponseBody
	 @RequestMapping("articleDetail")
	 public  Article articleDetail(Integer id){
		 return articleService.select(id);
	 }
	 
	 
	 //我的文章
	 @RequestMapping("articles")
	 public String articles(Model m,HttpSession session,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="4")Integer pageSize){
		 Article article = new Article();
		 User user = (User) session.getAttribute("user");
		 article.setUserId(user.getId());//只显示当前登陆人的文章
		 PageInfo<Article> info = articleService.selects(article, page, pageSize);
		 m.addAttribute("info", info);
		 return "my/articles";
	 }
	 //发布文章
	 @GetMapping("publish")
	 public String publish(){
		 
		 return "my/publish";
	 }
	 
	 /**
	  * 发布文章
	  */
	 @ResponseBody
	 @PostMapping("publish")
	 public boolean publish(MultipartFile file,Article article,HttpSession session){
		 //文件上传
		 if(null!=file &&!file.isEmpty()){
			 
			 String path="d:/pic/";
			 //文件的原始名称 1.jpg
			 String filename = file.getOriginalFilename();
			//为了防止文件重名需要改变文件的名字
			 String newFilename= UUID.randomUUID()+filename.substring(filename.lastIndexOf("."));
			 
			 File f = new File(path,newFilename);
			 
			 try {
				 //把文件写入硬盘
				file.transferTo(f);
				article.setPicture(newFilename);//文件的名称
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		 }
		 //文章的初始化数据
		 User user = (User) session.getAttribute("user");
		 article.setUserId(user.getId());
		 article.setCreated(new Date());
		 article.setHits(0);//点击量 默认 0
		 article.setDeleted(0);//默认未删除
		 article.setHot(0);//是否热门  默认非热门4
		 article.setStatus(0);//默认待审核
		 
		 return articleService.insert(article)>0;
		  
	 }
	 
	 //查询我的收藏的文章
	 @RequestMapping("myCollect")
	 public String myCollect(Model m,HttpSession session,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="4")Integer pageSize){
		 User user = (User) session.getAttribute("user");
		  PageInfo<Collect> info = collectService.selects(user.getId(), page, pageSize);
		 m.addAttribute("info", info);
		return "my/myCollect";
	 }
		 
}
