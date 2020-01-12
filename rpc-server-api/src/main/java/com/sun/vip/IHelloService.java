package com.sun.vip;

import com.sun.vip.User;

/**
 * @program: rpcserver
 * @description: 定义api 接口
 * @author: fz
 * @create: 2020-01-12 15:16
 */
public interface IHelloService {
    String sayHello(String contant);

    String saveUser(User user);
}