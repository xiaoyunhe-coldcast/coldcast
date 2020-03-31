package com.coldcast.netty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.coldcast.util.logUtil;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * Title : Netty服务端
 * @{author} Administrator
 * @{date} 2020年3月31日
 * @{description} 
 */
@Service
public class NettyServer {
	
    //boss事件轮询线程组 ,处理连接事件
    private EventLoopGroup bossGroup = new NioEventLoopGroup();
    //worker事件轮询线程组, 用于数据处理
    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    @Autowired
    private NettyServerInitializer nettyServerInitializer;

    @Value("${netty.port}")
    private Integer port;

    /**
     * 开启Netty服务
     *
     * @return
     */
    public void start() {
        try {
            //启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //设置参数，组配置
            serverBootstrap.group(bossGroup, workerGroup)
                    //socket参数，当服务器请求处理程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。
                    // 如果未设置或所设置的值小于1，Java将使用默认值50。
                    //
                    // 服务端可连接队列数,对应TCP/IP协议listen函数中backlog参数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 设置TCP长连接,一般如果两个小时内没有数据的通信时,TCP会自动发送一个活动探测数据报文
                    // 建议长连接的时候打开，心跳检测
                    // .childOption(ChannelOption.SO_KEEPALIVE, true)

                    // 构造channel通道工厂//bossGroup的通道，只是负责连接
                    .channel(NioServerSocketChannel.class)
                    // 设置通道处理者ChannelHandler////workerGroup的处理器
                    .childHandler(nettyServerInitializer);
            // 绑定端口,开始接收进来的连接
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            logUtil.info("netty服务启动: [port:" + port + "]");
            // 等待服务器socket关闭
            // 应用程序会一直等待，直到channel关闭
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
        	logUtil.error("netty服务启动异常-" + e.getMessage());
        } finally {
            // 优雅的关闭服务端
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

