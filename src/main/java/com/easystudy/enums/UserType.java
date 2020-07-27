package com.easystudy.enums;

/**
 * @文件名称: UserType.java
 * @功能描述: 用户类型定义
 * @版权信息： www.easystudy.com
 * @技术交流： 961179337(QQ群)
 * @编写作者： lixx2048@163.com
 * @联系方式： 941415509(QQ)
 * @开发日期： 2020年7月26日
 * @历史版本： V1.0
 */
public enum UserType {
	USER_TYPE_SERVICE(1, "运营商用户");

	private int value;
	private String description;

	private UserType(int value, String description) {
		this.value = value;
		this.description = description;
	}

	public int getValue() {
		return this.value;
	}

	public String getDescription() {
		return this.description;
	}
}
