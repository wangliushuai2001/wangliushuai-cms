package com.wls.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wls.cms.dao.CollectMapper;
import com.wls.cms.domain.Collect;
import com.wls.cms.domain.User;
import com.wls.cms.service.CollectService;
import com.wls.cms.util.CMSException;
import com.wls.common.utils.StringUtil;

@Service
public class CollectServiceImpl implements CollectService {
    
	@Resource
	private CollectMapper collectMapper;
	 
	@Override
	public int insert(Collect collect) {
		if(!StringUtil.isHttpUrl(collect.getUrl())){
		    throw new CMSException("不是合法的url")	;
		}
		return collectMapper.insert(collect);
	}

	@Override
	public int delete(Integer id) {
		
		return collectMapper.delete(id);
	}

	@Override
	public PageInfo<Collect> selects(Integer userId,Integer page,Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Collect> list = collectMapper.selects(userId);
		return new PageInfo<>(list);
	}

	@Override
	public Collect selectByTitleAndUserId(String title, Integer userId) {
		
		return collectMapper.selectByTitleAndUserId(title, userId);
	}

}
