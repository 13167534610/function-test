package com.function.socket;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * description:
 *
 * @author wangqiang
 */
public class ReceiveThread extends Thread {

    private ServerSocket server;
    private ChatWindow window;

    public ReceiveThread(ChatWindow window) {
        //获取前台对象并赋值
        this.window = window;
        try {
            //1.创建ServerSocket
            server = new ServerSocket(0);
            //
            window.setLocalPort(server.getLocalPort());
            //启动线程，到就绪状态
            start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            window.printError("连接出错");
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(true){
            Socket socket;
            try {
                socket = server.accept();
                InputStreamReader reader = new InputStreamReader(socket.getInputStream());
                int c;
                StringBuilder sb = new StringBuilder();
                while((c = reader.read()) != -1){
                    sb.append((char)c);
                }
                window.setReceive(sb.toString());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println("线程将接受到的数据写入对话框出错");
            }
        }
    }
}
