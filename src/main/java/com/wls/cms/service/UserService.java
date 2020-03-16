package com.wls.cms.service;


import com.github.pagehelper.PageInfo;
import com.wls.cms.domain.User;

public interface UserService {
  
	     //查询用户的列表
		 PageInfo<User> selects(User user,Integer page,Integer pageSize);
		 
		//更新用户的状态 正常 禁用
		 int update(User user);
		 
		//注册用户
		 int insert(User user);
		 
		 //根据用户名查询用户是否存在
		 User selectByUsername(String username);
		 
		 //登陆
		 User login(User user);
}
