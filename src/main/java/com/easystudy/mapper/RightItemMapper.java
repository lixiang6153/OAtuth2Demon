package com.easystudy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.easystudy.model.RightItem;

public interface RightItemMapper {
	/**
	 * @功能描述: TODO(通过权限项目明细id删除权限项目)
	 * @编写作者： lixx2048@163.com
	 * @开发日期： 2020年3月28日
	 * @历史版本： V1.0  
	 * @参数说明：
	 */
    int deleteByPrimaryKey(Long id);

    /**
     * @功能描述: TODO(添加权限项目明细信息-全部列)
     * @编写作者： lixx2048@163.com
     * @开发日期： 2020年3月28日
     * @历史版本： V1.0  
     * @参数说明：
     */
    int insert(RightItem record);

    /**
     * @功能描述: TODO(添加权限项目明细信息-非空列)
     * @编写作者： lixx2048@163.com
     * @开发日期： 2020年3月28日
     * @历史版本： V1.0  
     * @参数说明：
     */
    int insertSelective(RightItem record);

    /**
     * @功能描述: TODO(通过主键搜索)
     * @编写作者： lixx2048@163.com
     * @开发日期： 2020年3月28日
     * @历史版本： V1.0  
     * @参数说明：
     */
    RightItem selectByPrimaryKey(Long id);
    
    /**
     * @功    能: 通过角色id搜索角色权限项目
     * @作    者： lixx2048@163.com
     * @日    期： 2020年5月19日
     * @说    明：
     * @历    史：lixx2048@163.com 1.0
     */
    List<RightItem> selectByRoleId(@Param("roleId")Long roleId);
    
    /**
     * @功    能: 通过用户id或用户名获取用户权限项目
     * @作    者： lixx2048@163.com
     * @日    期： 2020年5月19日
     * @说    明：
     * @历    史：lixx2048@163.com 1.0
     */
    List<RightItem> selectByUserIdOrName(@Param("userId")Long userId, @Param("username")String username);

    /**
     * @功能描述: TODO(通过主键更新非空列)
     * @编写作者： lixx2048@163.com
     * @开发日期： 2020年3月28日
     * @历史版本： V1.0  
     * @参数说明：
     */
    int updateByPrimaryKeySelective(RightItem record);

    /**
     * @功能描述: TODO(通过主键更新所有列)
     * @编写作者： lixx2048@163.com
     * @开发日期： 2020年3月28日
     * @历史版本： V1.0  
     * @参数说明：
     */
    int updateByPrimaryKey(RightItem record);
    
    /**
     * @功能描述: TODO(分页搜索所有权限项目信息)
     * @编写作者： lixx2048@163.com
     * @开发日期： 2020年3月28日
     * @历史版本： V1.0  
     * @参数说明：
     */
    List<RightItem> selectByPage(@Param("pageIndex") Long pageIndex, @Param("pageSize")Long pageSize);
    
    /**
     * @功能描述: TODO(通过权限项目URL搜索)
     * @编写作者： lixx2048@163.com
     * @开发日期： 2020年3月28日
     * @历史版本： V1.0  
     * @参数说明：
     */
    RightItem selectByUrl(@Param("url")String url);
    
    /**
     * @功能描述: TODO(搜索权限项目的所权限项目明细)
     * @编写作者： lixx2048@163.com
     * @开发日期： 2020年3月28日
     * @历史版本： V1.0  
     * @参数说明：
     */
    List<RightItem> selectByRightId(Long rightId);
}