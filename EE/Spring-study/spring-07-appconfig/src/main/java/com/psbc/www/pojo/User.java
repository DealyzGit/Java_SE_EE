package com.psbc.www.pojo;

import org.springframework.beans.factory.annotation.Value;

public class User {

    public String getName() {
        return name;
    }

    @Value("tong")
    public void setName(String name) {
        this.name = name;
    }

    public String name;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
