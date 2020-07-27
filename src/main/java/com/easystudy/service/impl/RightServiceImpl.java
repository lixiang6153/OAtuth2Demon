package com.easystudy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easystudy.mapper.RightMapper;
import com.easystudy.model.Right;
import com.easystudy.service.RightService;


@Service
public class RightServiceImpl implements RightService{
	@Autowired
	protected RightMapper rightMapper;

	@Override
	public void add(Right o) {
		rightMapper.insertSelective(o);
	}

	@Override
	public void delete(Right o) {
		rightMapper.deleteByPrimaryKey(o.getId());
	}
	
	@Override
	public Right findById(Long id) {
		return rightMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<Right> findByRoleId(Long id) {
		return rightMapper.selectByRoleId(id);
	}

	@Override
	public Right findByName(String name) {
		return rightMapper.selectByName(name);
	}

	@Override
	public List<Right> findByUsername(String userName) {
		return rightMapper.selectByUsername(userName);
	}
	
	@Override
	public void update(Right o) {
		rightMapper.updateByPrimaryKeySelective(o);
	}

	@Override
	public Right findById(Object id) {
		return rightMapper.selectByPrimaryKey((Long)id);
	}

	@Override
	public List<Right> findByAttributes(Long pageIndex, Long pageSize) {
		return rightMapper.selectByPage(pageIndex, pageSize);
	}

	@Override
	public Long findMaxByAttributes() {
		return rightMapper.selectMaxByAttributes();
	}
}
