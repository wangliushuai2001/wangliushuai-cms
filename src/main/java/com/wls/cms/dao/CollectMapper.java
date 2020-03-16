package com.wls.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wls.cms.domain.Collect;

/**
 * 
 * @ClassName: CollectMapper 
 * @Description: 文章收藏
 * @author: charles
 * @date: 2020年2月15日 上午8:47:38
 */
public interface CollectMapper {

	 //收藏
	int insert(Collect collect);
	 
	 //取消收藏
	int delete(Integer id);
	
	 //显示我的收藏夹
	List<Collect> selects(Integer userId);
	
	 //根据title和userid 查询此文章是否被该用户收藏过
	Collect selectByTitleAndUserId(@Param("title")String title,@Param("userId")Integer userId);
	

}
