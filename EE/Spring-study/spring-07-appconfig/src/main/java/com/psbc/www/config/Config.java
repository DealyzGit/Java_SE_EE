package com.psbc.www.config;

import com.psbc.www.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.psbc.www.pojo")
@Import(Config2.class)
//注册到容器中 代表是一个配置类和beans.xml一样  @Component
public class Config {

//    相当于一个bean标签
//    方法名字相当于id
//    返回值 相当于class
    @Bean
    public User getUser(){
//        注入的bean的对象
        return new User();
    }

}
