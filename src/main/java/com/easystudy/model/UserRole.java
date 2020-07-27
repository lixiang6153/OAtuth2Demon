package com.easystudy.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @文件名称: UserRole.java
 * @功能描述: 用户角色信息实体类
 * @版权信息：www.easystudy.com
 * @技术交流： 961179337(QQ群)
 * @编写作者： lixx2048@163.com
 * @联系方式： 941415509(QQ)
 * @开发日期： 2020年3月28日
 * @历史版本： V1.0
 */
@Data
@Builder
@EqualsAndHashCode(callSuper=false)
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class UserRole extends Extend implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
    private Long userId;
    private Long roleId;

    public UserRole(Long id) {
    	this.id = id;
    }
    public UserRole(Long userId, Long roleId) {
    	this.userId = userId;
    	this.roleId = roleId;
    }
}