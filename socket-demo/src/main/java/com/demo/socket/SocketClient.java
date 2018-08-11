package com.demo.socket;

import java.io.*;
import java.net.Socket;

/**
 * @author yushaobo
 * @create 2018-08-11 16:49
 **/
public class SocketClient {

    public void sendMessage(){
        //客户端
        //1、创建客户端Socket，指定服务器地址和端口
        Socket socket = null;
        try {
            socket = new Socket("localhost", 10086);

            //2、获取输出流，向服务器端发送信息
            OutputStream os = socket.getOutputStream();//字节输出流
            PrintWriter pw = new PrintWriter(os);//将输出流包装成打印流
            pw.write("用户名：admin；密码：123");
            pw.flush();
            socket.shutdownOutput();
            //3、获取输入流，并读取服务器端的响应信息
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String info = null;
            while ((info = br.readLine())!= null){
                System.out.println("我是客户端，服务器说：" + info);
            }

            //4、关闭资源
            br.close();
            is.close();
            pw.close();
            os.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    new SocketClient().sendMessage();
                }
            }).start();
        }
    }
}
