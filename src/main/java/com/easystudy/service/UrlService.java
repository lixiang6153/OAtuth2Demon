package com.easystudy.service;

import java.util.List;

import com.easystudy.model.SysUrl;

public interface UrlService extends BaseService<SysUrl>{
	/**
	 * @功    能: 查询用户名接口列表
	 * @作    者： lixx2048@163.com
	 * @日    期： 2020年5月21日
	 * @说    明：
	 * @历    史：lixx2048@163.com 1.0
	 */
	public List<SysUrl> findByUserId(Long userId);
}
