package com.easystudy.service;

import java.util.List;

import com.easystudy.model.Right;

public interface RightService extends BaseService<Right>{
	/**
	 * 通过权限值名获取权限详情
	 * @param name
	 * @return
	 */
	public Right findByName(String name);
	
	/**
	 * 通过ID获取权限详情
	 * @param rightValue
	 * @return
	 */
	public Right findById(Long id);
	
	/**
	 * 通过角色ID获取权限详情
	 * @param rightValue
	 * @return
	 */
	public List<Right> findByRoleId(Long roleId);
	
	/**
	 * 通过用户名查询用户所有权限
	 * @param username
	 * @return
	 */
    public List<Right> findByUsername(String userName);

	/**@功能描述: 获取权限总数
	 * @编写作者： lixx2048@163.com
	 * @开发日期： 2020年4月9日
	 * @历史版本： V1.0  
	 * @参数说明：
	 */
	public Long findMaxByAttributes();
}
