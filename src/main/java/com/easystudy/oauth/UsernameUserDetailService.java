package com.easystudy.oauth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easystudy.service.UserService;

/**
 * @文件名称: UsernameUserDetailService.java
 * @功能描述: 通过用户名查询用户信息
 * @版权信息： www.easystudy.com
 * @技术交流： 961179337(QQ群)
 * @编写作者： lixx2048@163.com
 * @联系方式： 941415509(QQ)
 * @开发日期： 2020年7月26日
 * @历史版本： V1.0
 */
@Service
public class UsernameUserDetailService extends BaseUserDetailService {

    @Autowired
    private UserService userService;

    @Override
    protected com.easystudy.model.User getUser(String userName) {
        return userService.findByUsername(userName);
    }
}
