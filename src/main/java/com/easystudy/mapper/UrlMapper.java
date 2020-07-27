package com.easystudy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.easystudy.model.SysUrl;

public interface UrlMapper {
    int deleteByPrimaryKey(Long id);
    
    int insert(SysUrl record);
    
    int insertSelective(SysUrl record);
    
    SysUrl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUrl record);

    int updateByPrimaryKey(SysUrl record);

    List<SysUrl> selectByPage(@Param("pageIndex") Long pageIndex, @Param("pageSize")Long pageSize);
    
    SysUrl selectByUrl(@Param("url")String url);

	List<SysUrl> selectByUserId(@Param("userId")Long userId);
}