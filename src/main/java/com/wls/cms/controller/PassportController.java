package com.wls.cms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wls.cms.domain.User;
import com.wls.cms.service.UserService;
import com.wls.cms.util.CMSException;
import com.wls.cms.util.Result;

//注册登陆入口
@RequestMapping("passport")
@Controller
public class PassportController {
    
	@Resource
	private UserService userService;
	
	 @GetMapping("reg")
	 public String reg(){
		 
		 return "/passport/reg";
	 }
	 //执行注册
	 @PostMapping("reg")
	 @ResponseBody
	 public Result<User> reg(User user){
		 Result<User> result = new Result<User>();
		 try {
			if(userService.insert(user)>0){//如果注册成功
				 result.setCode(200);//成功
			     result.setMsg("注册成功！");
			}
		} catch (CMSException e) {//如果是自定义异常
			 // e.printStackTrace();
			  result.setCode(300);//注册失败
			  result.setMsg("注册失败:"+e.getMessage());
		} catch (Exception e) {
			  e.printStackTrace();//把异常消息在控制台打印
			  result.setCode(500);//注册失败,不可预知的异常
			 result.setMsg("注册失败，系统出现不可预知异常，请联系管理员");//给用户看的 
		}
		 
		 return result;
	 }
	 
	 //登陆
	 @GetMapping("login")
	 public String login(){
		 
		 return "/passport/login";
	 }
	 
	 //执行登录
	 @PostMapping("login")
	 @ResponseBody
	 public  Result<User> login(User formUser,Model m,HttpSession session){
		 Result<User> result = new Result<User>();
		 try {
			 //去登陆 ,如果成功则返回用户的基本信息
			 User user = userService.login(formUser);
			 
			 if(null!=user){
				  result.setCode(200);
				  result.setMsg("登陆成功！");
				  if(user.getRole()==0){
					  session.setAttribute("user", user);//登陆成功，把用户信息 存入session
				  }else{
					  session.setAttribute("admin", user);
				  }
			 }
		}catch (CMSException e) {//如果是自定义异常
			  //e.printStackTrace();
			  result.setCode(300);//登陆失败
			  result.setMsg("登陆失败:"+e.getMessage());
		} catch (Exception e) {
			  e.printStackTrace();//把异常消息在控制台打印
			  result.setCode(500);//登陆失败,不可预知的异常
			 result.setMsg("登陆失败，系统出现不可预知异常，请联系管理员");//给用户看的 
		}
		 return result;
	 }
	 
	 
	   
		 
	     //登陆  -管理员账户
		 @GetMapping("admin/login")
		 public String adminLogin(){
			 
			 return "/passport/adminLogin";
		 }
		 
	 
	 
	 //注销用户
	 @GetMapping("logout")
	 private String logout(HttpSession session){
		 
		 //清楚session的信息
		 session.invalidate();
		 
		 return "redirect:/";//回到首页
	 }
	 
	 //检查注册用户是否存在
	 @ResponseBody
	 @PostMapping("checkname")
	 public boolean checkname(String username){
		 return userService.selectByUsername(username)==null;
	 }
}
