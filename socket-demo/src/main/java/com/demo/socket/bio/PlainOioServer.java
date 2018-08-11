package com.demo.socket.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author yushaobo
 * @create 2018-08-11 16:23
 **/
public class PlainOioServer {
    public void serve(int port) throws IOException {

        //1.创建ServerSocket对象，绑定监听端口
        final ServerSocket socket = new ServerSocket(port);
        try {
            for (;;) {
                //2.通过accept()方法监听客户端请求
                final Socket clientSocket = socket.accept();
                System.out.println("Accepted connection from " + clientSocket);

                new Thread(new Runnable() {                        //3
                    @Override
                    public void run() {
                        OutputStream out;
                        try {
                            //打印客户端上来的消息
                            InputStream inputStream = clientSocket.getInputStream();
                            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                            String info = null;
                            while ((info = br.readLine())!= null){
                                System.out.println("我是服务器，客户端说：" + info);
                            }
                            Thread.sleep(5000);
                            //发送消息
                            out = clientSocket.getOutputStream();
                            out.write("Hi!\r\n".getBytes(Charset.forName("UTF-8")));                            //4
                            out.flush();
                            clientSocket.close();                //5

                        } catch (IOException e) {
                            e.printStackTrace();
                            try {
                                clientSocket.close();
                            } catch (IOException ex) {
                                // ignore on close
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();                                        //6
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PlainOioServer plainOioServer = new PlainOioServer();
        try {
            plainOioServer.serve(10086);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
