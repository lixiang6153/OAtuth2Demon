package com.easystudy.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @文件名称: AccsException.java
 * @功能描述: 自定义异常
 * @版权信息： www.dondown.com
 * @编写作者： lixx2048@163.com
 * @开发日期： 2020年4月8日
 * @历史版本： V1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class AccsException extends Exception {
	private static final long serialVersionUID = 1L;
	private int error;
	private String message;
	
	public AccsException(int error, String message) {
		this.error = error;
		this.message = message;
	}
}
