package com.easystudy.service;

import java.util.List;

import com.easystudy.model.Role;

/**
 * 角色专门的服务接口
 * @author Administrator
 *
 */
public interface RoleService  extends BaseService<Role>{
	/**
	 * 通过id获取角色信息
	 * @param id
	 * @return
	 */
	public Role findById(Long id);
	
	/**
	 * 通过用户id获取用户角色列表
	 * @param userName
	 * @return
	 */
	public List<Role> findByUserId(Long userId);
	
	/**
	 * 通过用户名获取用户角色列表
	 * @param userName
	 * @return
	 */
	public List<Role> findByUserName(String userName);
	
	/**
	 * 查找所有角色
	 * @return
	 */
	public List<Role> findAll();
	

	/**@功能描述: 通过角色名获取角色信息
	 * @编写作者： lixx2048@163.com
	 * @开发日期： 2020年4月7日
	 * @历史版本： V1.0  
	 * @参数说明：
	 */
	public List<Role> findByAttributes(String roleName, String relativeId, Long userType, String createUser, Long pageIndex, Long pageSize);

	/**@功能描述: 查询数量
	 * @编写作者： lixx2048@163.com
	 * @开发日期： 2020年4月8日
	 * @历史版本： V1.0  
	 * @参数说明：
	 */
	public Long findMaxByAttributes(String roleName, String relativeId, Long userType, String createUser);
}
