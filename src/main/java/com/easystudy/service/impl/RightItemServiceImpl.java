package com.easystudy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easystudy.mapper.RightItemMapper;
import com.easystudy.model.RightItem;
import com.easystudy.service.RightItemService;


@Service
public class RightItemServiceImpl implements RightItemService{
	@Autowired
	protected RightItemMapper RightItemMapper;

	@Override
	public void add(RightItem o) {
		RightItemMapper.insertSelective(o);
	}

	@Override
	public void delete(RightItem o) {
		RightItemMapper.deleteByPrimaryKey(o.getId());
	}
	
	@Override
	public void update(RightItem o) {
		RightItemMapper.updateByPrimaryKeySelective(o);
	}


	@Override
	public List<RightItem> findByAttributes(Long pageIndex, Long pageSize) {
		return RightItemMapper.selectByPage(pageIndex, pageSize);
	}

	@Override
	public RightItem findById(Object id) {
		return RightItemMapper.selectByPrimaryKey((Long)id);
	}
}
