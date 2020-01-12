package com.sun.vip;

/**
 * @program: rpcserver
 * @description: api 接口实现
 * @author: fz
 * @create: 2020-01-12 15:23
 */
public class HelloSeriveImpl implements IHelloService {
    @Override
    public String sayHello(String contant) {
        System.out.println("requset in ："+contant);
        return "Say hello:"+contant;
    }

    @Override
    public String saveUser(User user) {
        System.out.println("requset in  user："+user);

        return "success";
    }
}