package com.sun.vip;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: rpcserver
 * @description: 暴露服务- 代理类
 * @author: fz
 * @create: 2020-01-12 15:28
 */
public class RpcProxyServer {
    private int port;
    private Object object;

    public RpcProxyServer(int port, Object object) {
        this.port = port;
        this.object = object;
    }

    ExecutorService executorService = Executors.newCachedThreadPool();

    public void publish(int port,Object object){
        ServerSocket serverSocket  =null;

        try {
            serverSocket = new ServerSocket(port);
            while (true){

                Socket socket = serverSocket.accept();
                executorService.execute(new ProcesorHandler(socket,object));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket!=null){

                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}