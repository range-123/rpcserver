package com.sun.vip;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @program: rpcserver
 * @description:
 * @author: fz
 * @create: 2020-01-12 15:34
 */
public class ProcesorHandler implements Runnable {

    private Socket socket;
    private Object object;

    public ProcesorHandler(Socket socket, Object object) {
        this.socket = socket;
        this.object = object;
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois  = new ObjectInputStream(socket.getInputStream());
            //接受对象
            RequstObject requstObject = (RequstObject) ois.readObject();
            //反射调用
           Object os = invoke(requstObject);

           oos = new ObjectOutputStream(socket.getOutputStream());

           oos.writeObject(os);

           oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(ois!=null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(oos!=null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private Object invoke(RequstObject requstObject) {
    //得到参数
        Object[] args = requstObject.getParametes();
        Class<?>[]  types= new Class<?>[args.length];
        for (int i = 0; i <args.length ; i++) {
            types[i] = args[i].getClass();
        }
        try {
            Class clazz = Class.forName(requstObject.getClassName());
            Method method = clazz.getMethod(requstObject.getMethodName(),types);
            Object o =method.invoke(object,args);
            return o;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

          return null;
    }
}