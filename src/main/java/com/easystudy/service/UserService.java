package com.easystudy.service;

import java.util.List;

import com.easystudy.exception.AccsException;
import com.easystudy.model.User;
import com.easystudy.model.WechatApp;

/**
 * 提供用户专职的服务接口
 * @author Administrator
 */
public interface UserService extends BaseService<User>{
	/**
	 * 用户用户名获取用户信息
	 * @param username
	 * @return
	 */
	public User findByUsername(String userName);
	
	/**
	 * 通过手机号获取用户信息
	 * @param cellPhone
	 * @param userType:见枚举UserType
	 * @return
	 */
	public User findByCellphone(String cellPhone, Long userType);
	
	/**
	 * 通过用户id获取用户信息
	 * @param username
	 * @return
	 */
	public User findByUserId(long userId);
	
	/**
	 * 通过用户openId获取用户信息
	 * @param weChat
	 * @param phone
	 * @return
	 */
	public User findByOpenId(Long userType, String openId, Long openIdType);
	
	/**
	 * @功能描述: 查询用户最大属性
	 * @编写作者： lixx2048@163.com
	 * @开发日期： 2020年4月8日
	 * @历史版本： V1.0  
	 * @参数说明：
	 */
	public Long findMaxByAttributes(String username, String phone, Long userType, String relativeId, String ignoreUser, Long admin, String createUser);
	
	/**
	 * @功能描述: 通过属性查找所有用户信息
	 * @编写作者： lixx2048@163.com
	 * @开发日期： 2020年4月8日
	 * @历史版本： V1.0  
	 * @参数说明：
	 */
	public List<User> findByAttributes(String username, String phone, Long userType, String relativeId,
			String ignoreUser, Long admin, String createUser, String orderProp, String order, Long pageIndex, Long pageSize);
	
	/**
	 * 通过用户名删除用户
	 * @param userName
	 * @return
	 */
	public void deleteByUserName(String userName);

	/**@功能描述: 查找拥有某角色的用户
	 * @编写作者： lixx2048@163.com
	 * @开发日期： 2020年3月27日
	 * @历史版本： V1.0  
	 * @参数说明：
	 */
	public List<User> findByRoleId(Long roleId);
	
	/**
	 * @功能描述: 通过用户名删除用户
	 * @编写作者： lixx2048@163.com
	 * @开发日期： 2020年3月27日
	 * @历史版本： V1.0  
	 * @参数说明：
	 */
	public void deleteByUsername(String username);
	
	/**
	 * @功能描述: 通过用户名删除用户
	 * @编写作者： lixx2048@163.com
	 * @开发日期： 2020年3月27日
	 * @历史版本： V1.0  
	 * @参数说明：
	 */
	public void createUserAndAssignRole(User user, String roleName) throws AccsException;
	
	/**
	 * @功能描述: 创建用户并分配角色及添加用户app信息
	 * @编写作者： lixx2048@163.com
	 * @开发日期： 2020年3月27日
	 * @历史版本： V1.0  
	 * @参数说明：
	 */
	public void createUserRoleAndAddWechatApp(User user, String roleName, WechatApp app) throws AccsException;


	/**@功能描述: 根据用户关联id删除所有用户
	 * @编写作者： lixx2048@163.com
	 * @开发日期： 2020年4月15日
	 * @历史版本： V1.0  
	 * @参数说明：
	 */
	public void deleteByRelativeId(String relativeId);

	/**  
	 * @功    能: 通过手机号和用户类型删除教师
	 * @作    者： lixx2048@163.com
	 * @日    期： 2020年5月16日
	 * @说    明：
	 * @历    史：lixx2048@163.com 1.0
	 */
	public void deleteByCellphoneAndType(String phone, Long userType) throws Exception;
}