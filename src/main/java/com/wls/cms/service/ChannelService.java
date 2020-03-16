package com.wls.cms.service;

import java.util.List;

import com.wls.cms.domain.Category;
import com.wls.cms.domain.Channel;

public interface ChannelService {
	
	 List<Channel> selects();
	 
	 List<Category> selectsByChannelId(Integer channelId);
}
