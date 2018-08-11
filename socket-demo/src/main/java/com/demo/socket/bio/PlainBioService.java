package com.demo.socket.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author yushaobo
 * @create 2018-08-11 17:18
 **/
public class PlainBioService {

    //服务器线程处理
    //和本线程相关的socket
    Socket socket = null;
    //============================================

    //
    public PlainBioService(Socket socket) {
        this.socket = socket;
    }

    public void start(){
        OutputStream out;
        try {
            //打印客户端上来的消息
            InputStream inputStream = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String info = null;
            while ((info = br.readLine())!= null){
                System.out.println("我是服务器，客户端说：" + info);
            }
            Thread.sleep(5000);
            //发送消息
            out = socket.getOutputStream();
            out.write("Hi!\r\n".getBytes(Charset.forName("UTF-8")));                            //4
            out.flush();
            socket.close();                //5

        } catch (IOException e) {
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException ex) {
                // ignore on close
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    //服务器代码
        try {
            ServerSocket serverSocket = new ServerSocket(10086);
            Socket socket = null;
            int count = 0;//记录客户端的数量

            while(true){

                socket = serverSocket.accept();
                PlainBioService plainBioService = new PlainBioService(socket);
                plainBioService.start();
                count++;
                System.out.println("客户端连接的数量："+count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
