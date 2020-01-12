package com.sun.vip;

import java.io.Serializable;

/**
 * @program: rpcserver
 * @description: 请求对象
 * @author: fz
 * @create: 2020-01-12 15:42
 */
public class RequstObject implements Serializable {
    private  String className;
    private  String methodName;
    private Object[] parametes;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParametes() {
        return parametes;
    }

    public void setParametes(Object[] parametes) {
        this.parametes = parametes;
    }
}