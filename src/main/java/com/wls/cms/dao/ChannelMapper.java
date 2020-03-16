package com.wls.cms.dao;

import java.util.List;

import com.wls.cms.domain.Category;
import com.wls.cms.domain.Channel;

public interface ChannelMapper {
  
	 /**
	  * 查询所有的栏目
	  */
	List<Channel> selects();
	
	//根据栏目id查询分类
	List<Category> selectsByChannelId(Integer channelId);
}
