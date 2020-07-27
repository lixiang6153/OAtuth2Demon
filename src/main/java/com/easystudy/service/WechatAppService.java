package com.easystudy.service;

import java.util.List;

import com.easystudy.model.WechatApp;

/**
 * @文件名称: WechatAppService.java
 * @功能描述: 提供微信账号登录验证服务
 * @版权信息： www.dondown.com
 * @编写作者： lixx2048@163.com
 * @开发日期： 2020年4月16日
 * @历史版本： V1.0
 */
public interface WechatAppService extends BaseService<WechatApp>{
	/**
	 * @功能描述: 通过手机号和openid及类型验证用户
	 * @编写作者： lixx2048@163.com
	 * @开发日期： 2020年4月16日
	 * @历史版本： V1.0  
	 * @参数说明：
	 */
	public List<WechatApp> findByUsernameOrOpenId(String username, String openId, Long type);
	
	/**
	 * @功能描述: 通过属性分页获取
	 * @编写作者： lixx2048@163.com
	 * @开发日期： 2020年4月23日
	 * @历史版本： V1.0  
	 * @参数说明：
	 */
	public List<WechatApp> findByAttributes(Long pageIndex, Long pageSize, String username, String openId, Long type);
}