package com.coldcast.netty;

import java.net.InetSocketAddress;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.coldcast.util.logUtil;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;


/**
 * Title : 服务端处理器
 * @{author} Administrator
 * @{date} 2020年3月31日
 * @{description} 
 */
@Component
// 这个注解适用于标注一个channel handler可以被多个channel安全地共享
// 也可以使用new NettyServerHandler()方式解决
@ChannelHandler.Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * String 也可以是Object类型
     *
     * @param ctx
     * @param msg
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        logUtil.info("接收到客户端的消息:", msg);
        StringBuilder sb = null;
        Map<String, Object> result = null;
        try {
            // 报文解析处理
            sb = new StringBuilder();
            result = JSON.parseObject(msg);
            sb.append(result);
            sb.append("解析成功");
            sb.append("\n");
            //给客户端回消息
            ctx.writeAndFlush(sb);
        } catch (Exception e) {
            String errorCode = "-1\n";
            ctx.writeAndFlush(errorCode);
            logUtil.error("报文解析失败: " + e.getMessage());
        }
    }

    /**
     * 客户端去和服务端连接成功时触发
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = insocket.getAddress().getHostAddress();
        logUtil.info("收到客户端[ip:" + clientIp + "]连接");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    	logUtil.error("出现异常，断开连接！");
    	// 当出现异常就关闭连接
        ctx.close();
        //把客户端的通道关闭
        ctx.channel().close();
    }
    
}
