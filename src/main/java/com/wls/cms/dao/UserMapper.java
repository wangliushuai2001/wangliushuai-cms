package com.wls.cms.dao;

import java.util.List;

import com.wls.cms.domain.User;

public interface UserMapper {
    
	 //查询用户的列表
	 List<User> selects(User user);
	 
	 //更新用户的状态 正常 禁用
	 int update(User user);
	 
	 //注册用户
	 int insert(User user);
	 
	 //根据用户名查询用户是否存在
	 User selectByUsername(String username);
}
