package com.easystudy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easystudy.mapper.UrlMapper;
import com.easystudy.model.SysUrl;
import com.easystudy.service.UrlService;

@Service
public class UrlServiceImpl implements UrlService{
	@Autowired
	protected UrlMapper rightDetailMapper;

	@Override
	public void add(SysUrl o) {
		rightDetailMapper.insertSelective(o);
	}

	@Override
	public void delete(SysUrl o) {
		rightDetailMapper.deleteByPrimaryKey(o.getId());
	}

	@Override
	public void update(SysUrl o) {
		rightDetailMapper.updateByPrimaryKeySelective(o);
	}

	@Override
	public SysUrl findById(Object id) {
		return rightDetailMapper.selectByPrimaryKey((Long)id);
	}

	@Override
	public List<SysUrl> findByAttributes(Long pageIndex, Long pageSize) {
		return rightDetailMapper.selectByPage(pageIndex, pageSize);
	}

	@Override
	public List<SysUrl> findByUserId(Long userId) {
		return rightDetailMapper.selectByUserId(userId);
	}
}
