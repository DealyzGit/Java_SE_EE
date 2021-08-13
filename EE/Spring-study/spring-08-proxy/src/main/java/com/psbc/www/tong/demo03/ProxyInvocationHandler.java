package com.psbc.www.tong.demo03;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
//import java.lang.reflect.Proxy;

public class ProxyInvocationHandler implements InvocationHandler {

    private Object object;

    //被处理的接口
    public void setRent(Object object) {
        this.object = object;
    }

    //生成得到代理类
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
    }

    //处理代理实例，并返回结果
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

    //动态代理的本质是使用反射机制实现
        Object result = method.invoke(object, args);
        return result;
    }
}
