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
 * @文件名称: Role.java
 * @功能描述: 系统角色实体类
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
@NoArgsConstructor
@AllArgsConstructor
public class Role extends Extend implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
    private String name;
    private String description;
    private Long userType;
    private String relativeId;
    private String createUser;

    public Role(Long id){
    	this.id = id;
    }
    
    public Role(Long id, String name, String description){
    	this.id = id;
    	this.name = name;
    	this.description = description;
    }
}