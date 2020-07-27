package com.easystudy.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @文件名称: UserRight.java
 * @功能描述: 用户权限VO类
 * @版权信息： www.easystudy.com
 * @技术交流： 961179337(QQ群)
 * @编写作者： lixx2048@163.com
 * @联系方式： 941415509(QQ)
 * @开发日期： 2020年7月26日
 * @历史版本： V1.0
 */
@Data
@Builder
@EqualsAndHashCode(callSuper=false)
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class UserRight extends Extend implements Serializable{
	private static final long serialVersionUID = 1L;
	private String token;
	private Long userId;
	private String userName;
	private long userType;
	private String mpOpenId;			// 如果有公众号则填写公众号id
	private List<SysUrl> urls;
	
	public UserRight(String token) {
		this.token = token;
	}
}
