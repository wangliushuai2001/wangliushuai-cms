package com.wls.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wls.cms.dao.ChannelMapper;
import com.wls.cms.domain.Category;
import com.wls.cms.domain.Channel;
import com.wls.cms.service.ChannelService;

@Service
public class ChannelServiceImpl implements ChannelService {
 
	@Resource
	private ChannelMapper channelMapper;
	@Override
	public List<Channel> selects() {
		
		return channelMapper.selects();
	}
	@Override
	public List<Category> selectsByChannelId(Integer channelId) {
		// TODO Auto-generated method stub
		return channelMapper.selectsByChannelId(channelId);
	}

}
