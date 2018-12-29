package com.function.ipandweb;

import java.net.InetAddress;

/**
 * description:获取本机ip
 * @author wangqiang
 */
public class getIpText {
    public static void main(String[] args) {
        try{
            InetAddress myip= InetAddress.getLocalHost();
            //InetAddress address = InetAddress.getLoopbackAddress();
            System.out.println("你的IP地址是："+myip.getHostAddress());
            System.out.println("主机名为："+myip.getHostName()+"。");
            //System.out.println("回环地址："+address);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}