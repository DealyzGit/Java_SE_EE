package com.psbc.www.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class User {
    @Value("tong")
    public void setName(String name) {
        this.name = name;
    }


    public String name;

}
