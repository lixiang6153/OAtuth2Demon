package com.easystudy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @文件名称: WebMvcConfig.java
 * @功能描述: 视图配置类
 * @版权信息： www.easystudy.com
 * @技术交流： 961179337(QQ群)
 * @编写作者： lixx2048@163.com
 * @个人信息：941415509
 * @开发日期： 2020年7月27日
 * @历史版本： V1.0
 */
@SuppressWarnings("deprecation")
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 前面是url路径，后面是视图路径，添加thymeleaf后自动配置prefix为/templates,suffix为.html
        registry.addViewController("/login").setViewName("/login");
        registry.addViewController("/home").setViewName("/home");
        registry.addViewController("/admin").setViewName("/admin");
    }
}
