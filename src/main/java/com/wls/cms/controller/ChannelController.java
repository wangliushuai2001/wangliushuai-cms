package com.wls.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wls.cms.domain.Category;
import com.wls.cms.domain.Channel;
import com.wls.cms.service.ChannelService;

@RequestMapping("channel")
@Controller
public class ChannelController {
      
	 @Resource
	 private ChannelService  channelService;
	  
	  @ResponseBody
	  @RequestMapping("channels")
	  public List<Channel> channels(){
		  return channelService.selects();
	  }
	  
	  
	  @ResponseBody
	  @RequestMapping("selectsByChannelId")
	  public List<Category> selectsByChannelId(Integer channelId){
		  
		  return channelService.selectsByChannelId(channelId);
	  }
}
