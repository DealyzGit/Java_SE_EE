package com.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class DruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean StatViewServlet(){

        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        HashMap<String, String> initParameters = new HashMap<>();

        initParameters.put("LoginUsername","admin");
        initParameters.put("LoginPassword","123");

        initParameters.put("allow","");


        bean.setInitParameters(initParameters);

        return bean;

    }

    @Bean
    public FilterRegistrationBean webStartFilter(){

        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());


        HashMap<String, String> initParameters = new HashMap<>();

        initParameters.put("exclusions","*.js,*.css,/druid/*");

        bean.setInitParameters(initParameters);


        return bean;


    }

}
