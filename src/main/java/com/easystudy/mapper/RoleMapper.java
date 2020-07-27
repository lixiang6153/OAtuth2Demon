package com.easystudy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.easystudy.model.Role;

public interface RoleMapper {
	/**
	 * 通过主键删除
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入记录
     * @param record
     * @return
     */
    int insert(Role record);

    /**
     * 插入记录[非空列]
     * @param record
     * @return
     */
    int insertSelective(Role record);

    /**
     * 通过主键检索
     * @param id
     * @return
     */
    Role selectByPrimaryKey(Long id);

    /**
     * 通过主键更新非空列
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     * 通过主键更新记录
     * @param record
     * @return
     */
    int updateByPrimaryKey(Role record);
    
    /**
     * 检索所有记录
     * @return
     */
    List<Role> selectAll();
    
    /**
     * 通过名称检索
     * @param roleName
     * @return
     */
    Role selectByRoleName(@Param("name")String roleName);
    
    /**
     * 通过用户id获取角色列表
     * @param userId
     * @return
     */
    List<Role> selectByUserId(Long userId);
    
    /**
     * 通过用户名获取角色列表
     * @param userName
     * @return
     */
    List<Role> selectByUserName(String userName);

    /**
     * 分页查询角色信息
     * @param pageIndex
     * @param pageSize
     * @return
     */
	List<Role> selectByPage(@Param("pageIndex")Long pageIndex, @Param("pageSize")Long pageSize);
	
    /**
     * 查询角色数量
     * @return
     */
	Long selectMax();

	/**@功能描述: 通过属性查找角色信息
	 * @编写作者： lixx2048@163.com
	 * @开发日期： 2020年4月7日
	 * @历史版本： V1.0  
	 * @参数说明：
	 */
	List<Role> selectByAttributes(@Param("name")String name, @Param("relativeId")String relativeId, 
								  @Param("userType")Long userType, 
								  @Param("createUser")String createUser, 
			                      @Param("pageIndex")Long pageIndex, @Param("pageSize")Long pageSize);

	/**@功能描述: 通过属性获取角色数量
	 * @编写作者： lixx2048@163.com
	 * @开发日期： 2020年4月8日
	 * @历史版本： V1.0  
	 * @参数说明：
	 */
	Long selectMaxByAttributes(@Param("roleName")String roleName, @Param("relativeId")String relativeId, 
							   @Param("userType")Long userType, @Param("createUser")String createUser);
}