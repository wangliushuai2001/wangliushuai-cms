package com.wls.cms.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.wls.cms.domain.Collect;
import com.wls.cms.domain.User;

public interface CollectService {
  
	    //收藏
		int insert(Collect collect);
		 
		 //取消收藏
		int delete(Integer id);
		
		 //显示我的收藏夹
		PageInfo<Collect> selects(Integer userId,Integer page,Integer pageSize);
		
		 //根据title和userid 查询此文章是否被该用户收藏过
		Collect selectByTitleAndUserId(@Param("title")String title,@Param("userId")Integer userId);
}
