package com.easystudy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.easystudy.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);
    
    int deleteByUserName(String username);
    
    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> selectAll();
    
    User selectByUsername(@Param("username")String username);
    
    List<User> findByRoleId(Long roleId);
    
    int deleteByRoleId(Long roleId);

	User selectCellphone(@Param("cellPhone")String cellPhone, @Param("userType")Long userType);

	User selectByWeChat(@Param("weChat")String weChat, @Param("phone")String phone);

	List<User> selectByPage(@Param("pageIndex")Long pageIndex, @Param("pageSize")Long pageSize);   
	
	Long selectMax();
	
	List<User> selectByRoleId(@Param("roleId")Long roleId);

	Long selectMaxByAttributes(@Param("username")String username, 
			@Param("phone")String phone,
			@Param("userType")Long userType, 
			@Param("relativeId")String relativeId,
			@Param("ignoreUser")String ignoreUser,
			@Param("admin")Long admin,
			@Param("createUser")String createUser);

	List<User> selectByAttributes(@Param("username")String username, 
			@Param("phone")String phone, 
			@Param("userType")Long userType, 
			@Param("relativeId")String relativeId,
			@Param("ignoreUser")String ignoreUser,
			@Param("admin")Long admin, 
			@Param("createUser")String createUser, 
			@Param("orderProp")String orderProp, @Param("order")String order, 
			@Param("pageIndex")Long pageIndex, @Param("pageSize")Long pageSize);

	void deleteByRelativeId(@Param("relativeId")String relativeId);  
	
	User selectByOpenId(@Param("userType")Long userType, @Param("openId")String openId, @Param("openIdType")Long openIdType);

	void deleteByCellphoneAndType(@Param("phone")String phone, @Param("userType")Long userType);
}