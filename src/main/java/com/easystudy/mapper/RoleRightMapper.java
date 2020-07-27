package com.easystudy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.easystudy.model.RoleRight;

public interface RoleRightMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoleRight record);
    int insertBatch(List<RoleRight> records);

    int insertSelective(RoleRight record);

    RoleRight selectByPrimaryKey(Long id);
    
    List<RoleRight> selectByRoleId(Long roleId);
    int deleteByRoleId(Long roleId);
    
    List<RoleRight> selectByRightId(Long rightId);
    
    RoleRight selectByRoleRightId(@Param("roleId")Long roleId, @Param("rightId")Long rightId);
    
    List<RoleRight> selectAll();

    int updateByPrimaryKeySelective(RoleRight record);

    int updateByPrimaryKey(RoleRight record);

	List<RoleRight> selectByPage(@Param("pageIndex")Long pageIndex, @Param("pageSize")Long pageSize);

	List<RoleRight> selectByAttributes(@Param("roleId")Long roleId, @Param("rightId")Long rightId, @Param("pageIndex")Long pageIndex, @Param("pageSize")Long pageSize);
	
	List<RoleRight> selectByRoleName(@Param("roleName")String roleName);

	List<RoleRight> selectMaxByAttributes(@Param("roleId")Long roleId, @Param("rightId")Long rightId);
}