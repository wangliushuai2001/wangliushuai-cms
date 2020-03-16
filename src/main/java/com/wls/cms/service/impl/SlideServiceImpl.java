package com.wls.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wls.cms.dao.SlideMapper;
import com.wls.cms.domain.Slide;
import com.wls.cms.service.SlideService;
@Service
public class SlideServiceImpl implements SlideService {
  
	 @Resource
	 private SlideMapper  slideMapper;
	@Override
	public List<Slide> selects() {
		
		return slideMapper.selects();
	}

}
