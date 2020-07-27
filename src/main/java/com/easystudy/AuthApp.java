package com.easystudy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @文件名称: AuthApp.java
 * @功能描述: 授权服务启动APP
 * @版权信息： www.easystudy.com
 * @技术交流： 961179337(QQ群)
 * @编写作者： lixx2048@163.com
 * @联系方式： 941415509(QQ)
 * @开发日期： 2020年7月26日
 * @历史版本： V1.0
 */
@MapperScan("com.easystudy.mapper")
@SpringBootApplication
@ServletComponentScan
public class AuthApp {
    public static void main(String[] args) {
        SpringApplication.run(AuthApp.class, args);
    }
}