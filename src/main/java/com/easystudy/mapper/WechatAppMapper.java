package com.easystudy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.easystudy.model.WechatApp;

public interface WechatAppMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WechatApp record);

    int insertSelective(WechatApp record);

    WechatApp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WechatApp record);

    int updateByPrimaryKey(WechatApp record);
    
    List<WechatApp> selectByUsernameOrOpenId(@Param("username")String username, @Param("openId")String openId, @Param("type")Long type);

	List<WechatApp> selectByAttributes(@Param("pageIndex")Long pageIndex, @Param("pageSize")Long pageSize, 
			@Param("username")String username, @Param("openId")String openId, @Param("type")Long type);
}