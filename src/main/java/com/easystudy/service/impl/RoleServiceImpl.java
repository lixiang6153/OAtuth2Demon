package com.easystudy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easystudy.mapper.RoleMapper;
import com.easystudy.model.Role;
import com.easystudy.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	protected RoleMapper roleMapper;

	@Override
	public void add(Role o) {
		roleMapper.insertSelective(o);
	}

	@Override
	public List<Role> findAll() {
		return roleMapper.selectAll();
	}
	
	@Override
	public Role findById(Long id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Role> findByUserId(Long userId) {
		return roleMapper.selectByUserId(userId);
	}

	@Override
	public List<Role> findByUserName(String userName) {
		return roleMapper.selectByUserName(userName);
	}

	@Override
	public void update(Role o) {
		roleMapper.updateByPrimaryKeySelective(o);
	}

	@Override
	public void delete(Role o) {
		roleMapper.deleteByPrimaryKey(o.getId());
	}

	@Override
	public Role findById(Object id) {
		return roleMapper.selectByPrimaryKey((Long)id);
	}

	@Override
	public List<Role> findByAttributes(Long pageIndex, Long pageSize) {
		return roleMapper.selectByPage(pageIndex, pageSize);
	}

	@Override
	public List<Role> findByAttributes(String roleName, String relativeId, Long userType, String createUser, Long pageIndex, Long pageSize) {
		return roleMapper.selectByAttributes(roleName, relativeId, userType, createUser, pageIndex, pageSize);
	}

	@Override
	public Long findMaxByAttributes(String roleName, String relativeId, Long userType, String createUser) {
		return roleMapper.selectMaxByAttributes(roleName, relativeId, userType, createUser);
	}	
}
