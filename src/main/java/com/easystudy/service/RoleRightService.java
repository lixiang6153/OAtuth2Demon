package com.easystudy.service;

import java.util.List;

import com.easystudy.model.RoleRight;

public interface RoleRightService extends BaseService<RoleRight>{
	/**
	 * 重置用户权限
	 * @param rightValue
	 * @return
	 */
	public int resetRoleRights(Long roleId, List<RoleRight> records);
	
	/**
	 * 查询角色对应权限
	 * @param rightValue
	 * @return
	 */
	public List<RoleRight> findByRoleId(Long roleId);
	
	/**
	 * 通过权限ID查询有改权限的角色ID
	 * @param rightId
	 * @return
	 */
    public List<RoleRight> findByRightId(Long rightId);
    
    /**
     * 通过角色名称查找角色权限信息
     * @param rightId
     * @return
     */
    public List<RoleRight> findByRoleName(String roleName);
    
	/**
	 * 通过权限ID查询有改权限的角色ID
	 * @param roleId
	 * @param rightId
	 * @return
	 */
    public RoleRight findByRoleRightId(Long roleId, Long rightId);

	/**@功能描述: 通过属性查找角色权限信息
	 * @编写作者： lixx2048@163.com
	 * @开发日期： 2020年4月7日
	 * @历史版本： V1.0  
	 * @参数说明：
	 */
	public List<RoleRight> findByAttributes(Long roleId, Long rightId, Long pageIndex, Long pageSize);

	/**@功能描述: 获取角色权限数量
	 * @编写作者： lixx2048@163.com
	 * @开发日期： 2020年4月9日
	 * @历史版本： V1.0  
	 * @参数说明：
	 */
	public List<RoleRight> findMaxByAttributes(Long roleId, Long rightId);
}
