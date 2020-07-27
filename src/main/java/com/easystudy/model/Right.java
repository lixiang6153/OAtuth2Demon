package com.easystudy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @文件名称: Right.java
 * @功能描述: 权限实体类(VO|PO|DTO一个权限包含多个权限)
 * @版权信息：www.easystudy.com
 * @技术交流： 961179337(QQ群)
 * @编写作者： lixx2048@163.com
 * @联系方式： 941415509(QQ)
 * @开发日期： 2020年7月26日
 * @历史版本： V1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class Right extends Extend implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
    private String name;
    private String description;
    private String details;
    
	public List<Integer> getUrlList(){
		List<Integer> list = new ArrayList<Integer>();
		if (details != null) {
			String rl[] = details.trim().split(",");
			for (int i = 0; i < rl.length; i++) {
				String[] numbers = rl[i].trim().split("-");
				try {
					int start = Integer.valueOf(numbers[0]);
					int end = Integer.valueOf(numbers[numbers.length-1]);
					for (int j = start; j <= end; j++) {
						list.add(j);
					}					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
}