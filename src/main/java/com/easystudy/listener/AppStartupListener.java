package com.easystudy.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.easystudy.enums.UserType;
import com.easystudy.model.Right;
import com.easystudy.model.RightItem;
import com.easystudy.model.Role;
import com.easystudy.model.RoleRight;
import com.easystudy.model.SysUrl;
import com.easystudy.model.User;
import com.easystudy.model.UserRole;
import com.easystudy.service.RightItemService;
import com.easystudy.service.RightService;
import com.easystudy.service.RoleRightService;
import com.easystudy.service.RoleService;
import com.easystudy.service.UrlService;
import com.easystudy.service.UserRoleService;
import com.easystudy.service.UserService;
import com.easystudy.util.CheckUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @文件名称: AppStartupListener.java
 * @功能描述: 系统初始化，用于系统初始化工作
 * @版权信息： www.easystudy.com
 * @技术交流： 961179337(QQ群)
 * @编写作者： lixx2048@163.com
 * @联系方式： 941415509(QQ)
 * @开发日期： 2020年7月26日
 * @历史版本： V1.0
 */
@Slf4j
@WebListener
public class AppStartupListener implements ServletContextListener{
	@Autowired
    private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UrlService urlService;
	@Autowired
	private RightService rightService;
	@Autowired
	private RightItemService rightItemService;
	@Autowired
	private RoleRightService roleRightService;
	@Autowired
	private UserRoleService userRoleService;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("授权中心正在初始化...");		
		try{
			// 初始化管理员
			initAdmin();
		}catch(Exception e){
			log.info(e.getMessage());
		}
		
		log.info("授权中心完成初始化！");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("授权中心正在退出...");
		
		log.info("授权中心退出完成！");
	}
	
	private void initAdmin() {
		// 角色管理
		Role role = new Role();
		role.setName("ROLE_SUPER_ADMIN");
		role.setDescription("超级管理员角色");
		role.setUserType((long)UserType.USER_TYPE_SERVICE.getValue());
		List<Role> roles = roleService.findByAttributes("ROLE_SUPER_ADMIN", null, (long)UserType.USER_TYPE_SERVICE.getValue(), null, 0L, 1L);
		if (CheckUtil.isNull(roles)) {
			roleService.add(role);
		} else {
			role.setId(roles.get(0).getId());
		}
		
		// 添加超级管理员
		User user = new User();
		user.setUsername("admin");
		user.setPassword(new BCryptPasswordEncoder().encode("123456"));
		user.setUserType((byte)UserType.USER_TYPE_SERVICE.getValue());
		user.setAdmin((byte)1);
		// 默认值字段赋默认值
		user.setEnabled((byte)1);
		user.setExpired((byte)0);
		user.setLocked((byte)0);
		User u = userService.findByUsername("admin");
		if (CheckUtil.isNull(u)) {
			userService.add(user);
		} else {
			user.setId(u.getId());
		}
		
		// 增加用户角色信息
		try {
			UserRole userRole = new UserRole();
			userRole.setUserId(user.getId());
			userRole.setRoleId(role.getId());
			userRoleService.add(userRole);
		} catch (Exception e) {
			if (!(e instanceof DuplicateKeyException)) {
				e.printStackTrace();
				log.error("增加用户角色异常:" + e.getMessage());
			}
		}
		
		// 添加接口地址
		try {
			SysUrl url = new SysUrl();
			url.setId(0L);
			url.setDescription("所有接口");
			url.setModify(-1);
			url.setUrl("/**");
			urlService.add(url);
		} catch (Exception e) {
			if (!(e instanceof DuplicateKeyException)) {
				e.printStackTrace();
				log.error("增加管理员接口异常:" + e.getMessage());
			}
		}
		
		// 添加权限
		try {
			Right right = new Right();
			right.setId(0L);
			right.setName("超级管理员权限");
			right.setDescription("所有权限");
			rightService.add(right);
		} catch (Exception e) {
			if (!(e instanceof DuplicateKeyException)) {
				e.printStackTrace();
				log.error("增加管理员接口异常:" + e.getMessage());
			}
		}
		
		// 角色权限
		try {
			RoleRight rr = new RoleRight();
			rr.setRoleId(role.getId());
			rr.setRightId(0L);
			roleRightService.add(rr);
		} catch (Exception e) {
			if (!(e instanceof DuplicateKeyException)) {
				e.printStackTrace();
				log.error("增加角色权限异常:" + e.getMessage());
			}
		}
		
		// 添加权限接口明细
		try {
			RightItem item = new RightItem();
			item.setRightId(0L);
			item.setUrlId(0L);
			rightItemService.add(item);
		} catch (Exception e) {
			if (!(e instanceof DuplicateKeyException)) {
				e.printStackTrace();
				log.error("增加管理员权限接口异常:" + e.getMessage());
			}
		}
	}
}
