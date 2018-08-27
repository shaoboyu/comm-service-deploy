package com.demo.socket.httpNetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @author yushaobo
 * <p>
 *     注意每一次对话都会建立一个channel，并且一个ChannelInboundHandler一般是不会同时去处理多个Channel的。
 *     使用FullHttpRequest进行处理
 * </p>
 * @create 2018-08-27 22:19
 **/
public class HttpNettyServer {
    private final int port;

    public HttpNettyServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {

        System.err.println(
                "Usage: " + HttpNettyServer.class.getSimpleName() +
                        " <port>");
        int port = 8081;
        new HttpNettyServer(port).start();
    }

    /**
     * 使用Netty封装的HttpServer
     * @throws Exception
     */
    public void start() throws Exception {
        ServerBootstrap b = new ServerBootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        b.group(group)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch)
                            throws Exception {
                        System.out.println("initChannel ch:" + ch);
                        ch.pipeline()
                                .addLast("decoder", new HttpRequestDecoder())   // 1
                                .addLast("encoder", new HttpResponseEncoder())  // 2
                                //HttpObjectAggregator(512 * 1024)的参数含义是消息合并的数据大小，如此代表聚合的消息内容长度不超过512kb。
                                .addLast("aggregator", new HttpObjectAggregator(512 * 1024))    // 3
                                .addLast("handler", new HttpHandler());        // 4
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128) // determining the number of connections queued
                .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE);

        b.bind(port).sync();
    }
}
