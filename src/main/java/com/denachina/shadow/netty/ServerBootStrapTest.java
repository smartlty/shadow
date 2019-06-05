package com.denachina.shadow.netty;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.util.concurrent.*;


public class ServerBootStrapTest {

    public static void ehcoServer(){
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(new NioEventLoopGroup()).channel(NioServerSocketChannel.class)
                .localAddress(8080)
                .childHandler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                        String msg = byteBuf.toString(CharsetUtil.UTF_8);
                        System.out.println("msg = " + msg);
                        channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer(msg.getBytes()));
                    }
                });
        ChannelFuture future = bootstrap.bind();
        future.addListener((ChannelFutureListener) channelFuture -> {
            if (channelFuture.isSuccess()){
                System.out.println("Server Bound");
            } else {
                System.out.println("bind failed");
                future.cause().printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("demo-thread-%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(1,1,0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024),factory, new ThreadPoolExecutor.AbortPolicy());
        executorService.execute(() -> {
            System.out.println(Thread.currentThread().getName());
            ehcoServer();
        });

        executorService.shutdown();
    }
}
