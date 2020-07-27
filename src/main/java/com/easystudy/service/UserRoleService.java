package com.easystudy.service;

import java.util.List;

import com.easystudy.model.UserRole;

/**
 * 提供用户角色服务接口
 * @author Administrator
 */
public interface UserRoleService extends BaseService<UserRole>{
	/**
	 * 通过用户ID查找用户角色信息
	 * @param id
	 * @return
	 */
    List<UserRole> findByUserId(Long id);
    
    /**
     * 通过用户名查找用户角色信息
     * @param userName
     * @return
     */
    UserRole findByUsername(String userName);
    
    /**
     * 通过角色ID获取用户角色信息
     * @param roleId
     * @return
     */
    List<UserRole> findByRoleId(Long roleId);
    
    /**
     * 通过角色Id删除用户信息
     * @param roleId
     * @return
     */
    int deleteByRoleId(Long roleId);
    
    /**
     * 分页获取用户角色信息
     */
    List<UserRole> findByAttributes(Long pageIndex, Long pageSize);

	/**  
	 * @功    能: 通过用户id和角色id删除用户角色
	 * @作    者： lixx2048@163.com
	 * @日    期： 2020年5月13日
	 * @说    明：
	 * @历    史：lixx2048@163.com 1.0
	 */
	void deleteByUserRoleId(Long userId, Long roleId);
}