package com.easystudy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.easystudy.mapper.RoleRightMapper;
import com.easystudy.model.RoleRight;
import com.easystudy.service.RoleRightService;


@Service
public class RoleRightServiceImpl implements RoleRightService{
	@Autowired
	protected RoleRightMapper roleRightMapper;

	@Override
	public void add(RoleRight o) {
		roleRightMapper.insertSelective(o);
	}
	
	@Override
	public List<RoleRight> findByRoleId(Long roleId) {
		return roleRightMapper.selectByRoleId(roleId);
	}

	@Override
	public List<RoleRight> findByRightId(Long rightId) {
		return roleRightMapper.selectByRightId(rightId);
	}
	
	@Override
	public RoleRight findByRoleRightId(Long roleId, Long rightId) {
		return roleRightMapper.selectByRoleRightId(roleId, rightId);
	}

	@Override
	public void update(RoleRight o) {
		roleRightMapper.updateByPrimaryKeySelective(o);
	}

	@Override
	public void delete(RoleRight o) {
		roleRightMapper.deleteByPrimaryKey(o.getId());
	}

	@Override
	public RoleRight findById(Object id) {
		return roleRightMapper.selectByPrimaryKey((Long)id);
	}

	@Override
	public List<RoleRight> findByAttributes(Long pageIndex, Long pageSize) {
		return roleRightMapper.selectByPage(pageIndex, pageSize);
	}

	@Override
	public List<RoleRight> findByAttributes(Long roleId, Long rightId, Long pageIndex, Long pageSize) {
		return roleRightMapper.selectByAttributes(roleId, rightId, pageIndex, pageSize);
	}

	@Override
	public List<RoleRight> findByRoleName(String roleName) {
		return roleRightMapper.selectByRoleName(roleName);
	}

	@Override
	public List<RoleRight> findMaxByAttributes(Long roleId, Long rightId) {
		return roleRightMapper.selectMaxByAttributes(roleId, rightId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int resetRoleRights(Long roleId, List<RoleRight> records) {
		int count = roleRightMapper.deleteByRoleId(roleId);
		if(records.size() > 0){
			count = roleRightMapper.insertBatch(records);
		}
		return count;
	}
}
