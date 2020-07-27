package com.easystudy.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.easystudy.error.ErrorCode;
import com.easystudy.error.ReturnValue;
import com.easystudy.model.User;
import com.easystudy.service.UserService;
import com.easystudy.util.CheckUtil;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * @文件名称: UserController.java
 * @功能描述: 用户管理控制器
 * @版权信息： www.easystudy.com
 * @技术交流： 961179337(QQ群)
 * @编写作者： lixx2048@163.com
 * @个人信息： 941415509
 * @开发日期： 2020年7月27日
 * @历史版本： V1.0
 */
@Api(tags = "用户管理接口列表", value = "提供用户管理相关接口")
@RestController
@RequestMapping("/oauth/user")
@Slf4j
public class UserController {
	@Autowired
	protected UserService userService;
	@Autowired
	protected PasswordEncoder passwordEncoder;
	@Autowired
	protected ResourceServerTokenServices resourceServerTokenServices;

	/**
	 * @功能描述: 当前用户认证信息
	 * @编写作者： lixx2048@163.com
	 * @开发日期： 2020年7月27日
	 * @历史版本： V1.0  
	 * @参数说明：
	 */
	@GetMapping(value = "/me")
    public Principal me(Principal principal) {
        log.info("资源服务获取用户信息：" + principal);
        return principal;
    }
	
	/**
	 * @功能描述: 添加一个用户
	 * @编写作者： lixx2048@163.com
	 * @开发日期： 2020年7月27日
	 * @历史版本： V1.0  
	 * @参数说明：
	 */
	@PostMapping(value = "/add")
	public ReturnValue<Long> add( @RequestHeader(name="current_user",required=false) String current_user,
								  @RequestHeader(name="current_user_type",required=false) String current_user_type,
								  @RequestBody User user){
		try {
			// 同名检测
			User temp = userService.findByUsername(user.getUsername());
			if (!CheckUtil.isNull(temp)) {
				return new ReturnValue<Long>(ErrorCode.ERROR_OBJECT_EXIST, "该用户名已存在!");
			}
			// 手机号检测
			temp = userService.findByCellphone(user.getMobile(), (long)user.getUserType());
			if (!CheckUtil.isNull(temp)) {
				return new ReturnValue<Long>(ErrorCode.ERROR_OBJECT_EXIST, "该类型用户手机号已存在!");
			}
			// 加密password
			user.setCreateUser(current_user);
			user.setPassword("888888");
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			// 默认值字段赋默认值
			user.setAdmin((byte)0);
			user.setEnabled((byte)1);
			user.setExpired((byte)0);
			user.setLocked((byte)0);
			userService.add(user);
			return new ReturnValue<Long>(user.getId());
		}catch (Exception e) {
			log.error(e.getCause().getMessage());
			return new ReturnValue<Long>(ErrorCode.ERROR_SERVER_ERROR, "添加用户失败");
		}
	}
	
	/**
	 * @功能描述: 修改用户信息
	 * @编写作者： lixx2048@163.com
	 * @开发日期： 2020年7月27日
	 * @历史版本： V1.0  
	 * @参数说明：
	 */
  	@PutMapping(value = "/update")
	public ReturnValue<String> update(@RequestBody User user){
		try{
			// 加密password
			if (user.getPassword() != null && !user.getPassword().startsWith("$2a$10$")) {
				user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			}
			userService.update(user);
			return new ReturnValue<String>();
		}catch(Exception e){
			e.printStackTrace();
			return new ReturnValue<String>(ErrorCode.ERROR_SERVER_ERROR, "更新用户失败");
		}
	}
	
	@PutMapping(value = "/updateUserCellphone")
	public ReturnValue<String> updateUserCellphone( @RequestParam(name="userType",required=true) Long userType, 
													@RequestParam(name="oldCellphone",required=true) String oldCellphone,  
													@RequestParam(name="cellphone",required=true) String cellphone){
		try{
			// 手机号相同
			if (cellphone.equals(oldCellphone)) {
				return new ReturnValue<String>(ErrorCode.ERROR_OBJECT_EXIST, "此手机号码不能与原手机号相同!");
			}
			// 手机号检测
			User u = userService.findByCellphone(cellphone, userType);
			if (!CheckUtil.isNull(u)) {
				return new ReturnValue<String>(ErrorCode.ERROR_OBJECT_EXIST, "此手机号已被占用,请更换其他手机号!");
			}
						
			List<User> users = userService.findByAttributes(null, oldCellphone, userType, null, null, null, null, null, null, 0L, 1L);
			if (CheckUtil.isNull(users)) {
				return new ReturnValue<String>(ErrorCode.ERROR_NOT_FOUND, "指定类型的用户原手机号不存在!");
			}
			
			User newUser = new User();
			newUser.setId(users.get(0).getId());
			newUser.setMobile(cellphone);			
			
			userService.update(newUser);
			return new ReturnValue<String>();
		}catch(Exception e){
			e.printStackTrace();
			return new ReturnValue<String>(ErrorCode.ERROR_SERVER_ERROR, "更新用户手机号失败");
		}
	}
	
	/**
	 * @功能描述: 通过用户名删除用户
	 * @编写作者： lixx2048@163.com
	 * @开发日期： 2020年7月27日
	 * @历史版本： V1.0  
	 * @参数说明：
	 */
	@DeleteMapping(value = "/delete/{username}")
	public ReturnValue<String> delete(@PathVariable(name="username", required=true) String username){
		try{
			userService.deleteByUsername(username);
			return new ReturnValue<String>();
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getCause().getMessage());
			return new ReturnValue<String>(ErrorCode.ERROR_SERVER_ERROR, "删除用户失败");
		}
	}
	
	/**
	 * @功能描述: 查询满足指定条件的用户数量
	 * @编写作者： lixx2048@163.com
	 * @开发日期： 2020年7月27日
	 * @历史版本： V1.0  
	 * @参数说明：
	 */
	@GetMapping("/findMaxByAttributes")
    public ReturnValue<Long> findMaxByAttributes(@RequestParam(name="username",required=false) String username,
    		                                     @RequestParam(name="phone",required=false) String phone,
												 @RequestParam(name="userType", required=false) Long userType,
												 @RequestParam(name="relativeId",required=false) String relativeId,
												 @RequestHeader(name="current_user",required=false) String current_user,
												 @RequestParam(name="ignore_self",required=false) Long ignore_self,
												 @RequestParam(name="admin",required=false) Long admin){
    	try {
    		String ignoreUser = null;
    		if(ignore_self != null && ignore_self != 0){
    			ignoreUser = current_user;
    		}
    		Long count = userService.findMaxByAttributes(username, phone, userType, relativeId, ignoreUser, admin, current_user);
    		return new ReturnValue<Long>(count);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ReturnValue<Long>(ErrorCode.ERROR_SERVER_ERROR);
		}
    }
	
	/**
	 * @功能描述: 分页查询满足指定条件的用户信息
	 * @编写作者： lixx2048@163.com
	 * @开发日期： 2020年7月27日
	 * @历史版本： V1.0  
	 * @参数说明：
	 */
	@GetMapping("/findByAttributes")
    public ReturnValue<List<User>> findByAttributes(@RequestParam(name="pageIndex") Long pageIndex, 
													@RequestParam(name="pageSize") Long pageSize,
													@RequestParam(name="orderProp", required=false) String orderProp,
													@RequestParam(name="order", required=false) String order,
													@RequestParam(name="username",required=false) String username,
	    		                                    @RequestParam(name="phone",required=false) String phone,
													@RequestParam(name="userType", required=false) Long userType,
													@RequestParam(name="relativeId",required=false) String relativeId,
													@RequestHeader(name="current_user",required=false) String current_user,
													@RequestParam(name="ignore_self",required=false) Long ignore_self,
													@RequestParam(name="admin",required=false) Long admin){
    	try {
    		String ignoreUser = null;
    		if(ignore_self != null && ignore_self != 0){
    			ignoreUser = current_user;
    		}
    		List<User> list = userService.findByAttributes(username, phone, userType, relativeId, ignoreUser, admin, current_user, orderProp, order, pageIndex, pageSize);
    		if (CheckUtil.isNull(list)) {
				return new ReturnValue<List<User>>(ErrorCode.ERROR_NOT_FOUND, "未找到用户信息!");
			}
    		return new ReturnValue<List<User>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ReturnValue<List<User>>(ErrorCode.ERROR_SERVER_ERROR);
		}
    }
}
