package com.easystudy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.easystudy.error.ErrorCode;
import com.easystudy.exception.AccsException;
import com.easystudy.mapper.RoleMapper;
import com.easystudy.mapper.UserMapper;
import com.easystudy.mapper.UserRoleMapper;
import com.easystudy.mapper.WechatAppMapper;
import com.easystudy.model.Role;
import com.easystudy.model.User;
import com.easystudy.model.UserRole;
import com.easystudy.model.WechatApp;
import com.easystudy.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private WechatAppMapper wechatAppMapper;

	@Override
	public void add(User o) { 
		userMapper.insertSelective(o);
	}

	@Override
	public User findByUsername(String username) {
		return userMapper.selectByUsername(username);
	}

	@Override
	public void update(User o) {
		// TODO Auto-generated method stub
		userMapper.updateByPrimaryKeySelective(o);
	}
	
	@Override
	public User findByUserId(long userId){
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public User findByCellphone(String cellPhone, Long userType) {
		return userMapper.selectCellphone(cellPhone, userType);
	}

	@Override
	public void delete(User o) {
		userMapper.deleteByPrimaryKey(o.getId());
	}

	@Override
	public User findById(Object id) {
		return userMapper.selectByPrimaryKey((Long)id);
	}

	@Override
	public List<User> findByAttributes(Long pageIndex, Long pageSize) {
		return userMapper.selectByPage(pageIndex,pageSize);
	}

	@Override
	public void deleteByUserName(String username) {
		userMapper.deleteByUserName(username);
	}

	@Override
	public void deleteByUsername(String username) {
		userMapper.deleteByUserName(username);
	}

	@Override
	public List<User> findByRoleId(Long roleId) {
		return userMapper.selectByRoleId(roleId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createUserAndAssignRole(User user, String roleName) throws AccsException {
		// 增加用户
		userMapper.insertSelective(user);
		// 查询对应角色
		Role role = roleMapper.selectByRoleName(roleName);
		if (null == role) {
			throw new AccsException(ErrorCode.ERROR_NOT_FOUND.getError(), "角色不存在：" + roleName);
		}
		// 用户角色信息
		UserRole userRole = new UserRole();
		userRole.setUserId(user.getId());
		userRole.setRoleId(role.getId());
		userRoleMapper.insertSelective(userRole);
	}

	@Override
	public Long findMaxByAttributes(String username, String phone, Long userType, String relativeId, String ignoreUser, Long admin, String createUser) {
		return userMapper.selectMaxByAttributes(username, phone, userType, relativeId, ignoreUser, admin, createUser);
	}

	@Override
	public List<User> findByAttributes(String username, String phone, Long userType, String relativeId, String ignoreUser, 
			Long admin, String createUser, String orderProp, String order, Long pageIndex, Long pageSize) {
		return userMapper.selectByAttributes(username, phone, userType, relativeId, ignoreUser, admin, createUser, orderProp, order, pageIndex, pageSize);
	}

	@Override
	public void deleteByRelativeId(String relativeId) {
		userMapper.deleteByRelativeId(relativeId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createUserRoleAndAddWechatApp(User user, String roleName, WechatApp app) throws AccsException {
		// 增加用户
		userMapper.insertSelective(user);
		// 查询对应角色
		Role role = roleMapper.selectByRoleName(roleName);
		if (null == role) {
			throw new AccsException(ErrorCode.ERROR_NOT_FOUND.getError(), "角色不存在：" + roleName);
		}
		// 用户角色信息
		UserRole userRole = new UserRole();
		userRole.setUserId(user.getId());
		userRole.setRoleId(role.getId());
		userRoleMapper.insertSelective(userRole);
		// 添加用户app信息
		if(app != null) {
			wechatAppMapper.insertSelective(app);
		}
	}

	@Override
	public User findByOpenId(Long userType, String openId, Long openIdType) {
		return userMapper.selectByOpenId(userType, openId, openIdType);
	}

	@Override
	public void deleteByCellphoneAndType(String phone, Long userType) throws Exception {
		userMapper.deleteByCellphoneAndType(phone, userType);
	}
}
