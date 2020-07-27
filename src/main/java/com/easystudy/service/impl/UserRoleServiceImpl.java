package com.easystudy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easystudy.mapper.UserRoleMapper;
import com.easystudy.model.UserRole;
import com.easystudy.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

	@Override
	public UserRole findByUsername(String userName) {
		return userRoleMapper.selectByUsername(userName);
	}

	@Override
	public List<UserRole> findByRoleId(Long roleId) {
		return userRoleMapper.selectByRoleId(roleId);
	}

	@Override
	public int deleteByRoleId(Long roleId) {
		return userRoleMapper.deleteByRoleId(roleId);
	}

	@Override
	public List<UserRole> findByUserId(Long id) {
		return userRoleMapper.selectByUserId(id);
	}

	@Override
	public List<UserRole> findByAttributes(Long pageIndex, Long pageSize) {
		return userRoleMapper.selectByPage(pageIndex, pageSize);
	}

	@Override
	public void add(UserRole o) {
		userRoleMapper.insertSelective(o);
	}

	@Override
	public void delete(UserRole o) {
		userRoleMapper.deleteByPrimaryKey(o.getId());
	}

	@Override
	public void update(UserRole o) {
		userRoleMapper.updateByPrimaryKeySelective(o);
	}

	@Override
	public UserRole findById(Object id) {
		return userRoleMapper.selectByPrimaryKey((Long)id);
	}

	@Override
	public void deleteByUserRoleId(Long userId, Long roleId) {
		userRoleMapper.deleteByUserRoleId(userId, roleId);
	}
}
