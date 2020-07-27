package com.easystudy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easystudy.mapper.WechatAppMapper;
import com.easystudy.model.WechatApp;
import com.easystudy.service.WechatAppService;

@Service
public class WechatAppServiceImpl implements WechatAppService {
    @Autowired
    private WechatAppMapper WechatAppMapper;

	@Override
	public void add(WechatApp o) { 
		WechatAppMapper.insertSelective(o);
	}

	@Override
	public void update(WechatApp o) {
		WechatAppMapper.updateByPrimaryKeySelective(o);
	}

	@Override
	public void delete(WechatApp o) {
		WechatAppMapper.deleteByPrimaryKey(o.getId());
	}

	@Override
	public WechatApp findById(Object id) {
		return WechatAppMapper.selectByPrimaryKey((Integer)id);
	}

	@Override
	public List<WechatApp> findByAttributes(Long pageIndex, Long pageSize) {
		return null;
	}

	@Override
	public List<WechatApp> findByUsernameOrOpenId(String username, String openId, Long type) {
		return WechatAppMapper.selectByUsernameOrOpenId(username, openId, type);
	}

	@Override
	public List<WechatApp> findByAttributes(Long pageIndex, Long pageSize, String username, String openId, Long type) {
		return WechatAppMapper.selectByAttributes(pageIndex, pageSize, username, openId, type);
	}
}
