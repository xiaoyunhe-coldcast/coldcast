package com.coldcast.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


/**
 * Title : 客户端处理
 * @{author} Administrator
 * @{date} 2020年3月31日
 * @{description} 
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<String> {
	
    /**
     *接受服务端信息
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("收到服务端消息: " + msg);
    }

    /**
     *异常处理
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
    
     /**
     *激活后发送客户端信息给服务端
     */
    @Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
	  System.out.println("这里是与服务端建立连接后执行的方法(发送数据)----channelActive");
	}
}

