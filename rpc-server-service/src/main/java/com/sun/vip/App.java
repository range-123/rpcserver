package com.sun.vip;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        IHelloService iHelloService = new HelloSeriveImpl();
        RpcProxyServer proxyServer =new RpcProxyServer( 8080,iHelloService);
        proxyServer.publish(8080,iHelloService);
    }
}
