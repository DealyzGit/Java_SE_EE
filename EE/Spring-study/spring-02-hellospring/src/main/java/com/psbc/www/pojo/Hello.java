package com.psbc.www.pojo;

public class Hello {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String show(){
        return "Hello:"+name;
    }
}
