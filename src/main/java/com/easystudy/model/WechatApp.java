package com.easystudy.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @文件名称: WechatApp.java
 * @功能描述: 微信APP实体类型：指定app登录类型
 * @版权信息： www.dondown.com
 * @编写作者： lixx2048@163.com
 * @开发日期： 2020年4月16日
 * @历史版本： V1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(Include.NON_NULL)
public class WechatApp extends Extend implements Serializable{
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String username;
    private String openId;
    private Byte type;				// OpenIdType
    
    public WechatApp() {
    	
    }
    public WechatApp(Integer id) {
    	this.id = id;
    }
	public WechatApp(String username, String openId2, int intValue) {
		this.username = username;
		this.openId = openId2;
		this.type = (byte)intValue;
	}
}