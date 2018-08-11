//package com.demo.socket.netty;
//
//import org.jboss.netty.bootstrap.ServerBootstrap;
//import org.jboss.netty.channel.ChannelFuture;
//import org.jboss.netty.channel.socket.SocketChannel;
//
//import java.net.InetSocketAddress;
//import java.nio.charset.Charset;
//
///**
// * @author yushaobo
// * @create 2018-08-11 17:56
// **/
//public class NettyOioServer {
//    public void server(int port) throws Exception {
//        final ByteBuf buf = Unpooled.unreleasableBuffer(
//                Unpooled.copiedBuffer("Hi!\r\n", Charset.forName("UTF-8")));
//        EventLoopGroup group = new OioEventLoopGroup();
//        try {
//            ServerBootstrap b = new ServerBootstrap();        //1
//
//            b.group(group)                                    //2
//                    .channel(OioServerSocketChannel.class)
//                    .localAddress(new InetSocketAddress(port))
//                    .childHandler(new ChannelInitializer<SocketChannel>() {//3
//                        @Override
//                        public void initChannel(SocketChannel ch)
//                                throws Exception {
//                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {            //4
//                                @Override
//                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
//                                    ctx.writeAndFlush(buf.duplicate()).addListener(ChannelFutureListener.CLOSE);//5
//                                }
//                            });
//                        }
//                    });
//            ChannelFuture f = b.bind().sync();  //6
//            f.channel().closeFuture().sync();
//        } finally {
//            group.shutdownGracefully().sync();        //7
//        }
//    }
//}
