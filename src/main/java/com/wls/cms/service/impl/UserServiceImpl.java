package com.wls.cms.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wls.cms.dao.UserMapper;
import com.wls.cms.domain.User;
import com.wls.cms.service.UserService;
import com.wls.cms.util.CMSException;
import com.wls.cms.util.Md5Util;
import com.wls.common.utils.StringUtil;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;
	@Override
	public PageInfo<User> selects(User user, Integer page, Integer pageSize) {
		  PageHelper.startPage(page, pageSize);
		  List<User> list = userMapper.selects(user);
		return new PageInfo<>(list);
	}
	@Override
	public int update(User user) {
		
		return userMapper.update(user);
	}
	
	@Override
	public int insert(User user) {
		
		//int i =10/0;
		//用户名校验
		if(!StringUtil.hasText(user.getUsername()))	
			throw new CMSException("用户名不能为空");
		if(!(user.getUsername().length()>=2&&user.getUsername().length()<=10))	
			throw new CMSException("用户名的长度在2-10之间");
		//用户名不能重复
		User findUser = this.selectByUsername(user.getUsername());
		if(null!=findUser){
			throw new CMSException("用户名已被注册");
		}
		//密码校验
		if(!StringUtil.hasText(user.getPassword()))	
			throw new CMSException("密码不能为空");
		if(!(user.getPassword().length()>=6&&user.getPassword().length()<=10))	
			throw new CMSException("密码的长度在6-10之间");
		
		// 确认密码 两次面输入必须一致
		if(!StringUtil.hasText(user.getRepassword()))	
			throw new CMSException("确认密码不能为空");
		if(!user.getPassword().equals(user.getRepassword()))
			throw new CMSException("两次密码不一致");
		//对用户进行加密处理
		user.setPassword(Md5Util.encode(user.getPassword())); 
		user.setCreated(new Date());
		user.setNickname(user.getNickname());
		user.setLocked("0");
		return userMapper.insert(user);
	}
	
	@Override
	public User selectByUsername(String username) {
		
		return userMapper.selectByUsername(username);
	}
	
	public User login(User user){
		//检查用户名不能为空
		if(!StringUtil.hasText(user.getUsername()))	
			throw new CMSException("用户名不能为空！");
		if(!StringUtil.hasText(user.getPassword()))	
			throw new CMSException("密码不能为空！");
		//检查用户名是否存在
		User u = this.selectByUsername(user.getUsername());
		if(u==null){
			throw new CMSException("该用户不存在！");
		}
		//判断密码是否一致  数据库存储的是加密后的密码
		if(!Md5Util.encode(user.getPassword()).equals(u.getPassword())){
			throw new CMSException("密码不正确,请重新输入！");
		}
		if(u.getLocked().equals("1")){
			throw new CMSException("当前账户被禁用");
		}
		return u;
	}
	
	

}
