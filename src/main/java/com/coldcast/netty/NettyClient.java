package com.coldcast.netty;


import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.NoArgsConstructor;


/**
 * Title : 客户端
 * @{author} Administrator
 * @{date} 2020年3月31日
 * @{description} 
 */
@NoArgsConstructor
public class NettyClient {

    private EventLoopGroup group = new NioEventLoopGroup();

    // 定时线程池
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

    /**
     * 连接方法
     */
    public void connect(String ip,int port) {
        Channel channel = null;
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class);
            // 将小的数据包包装成更大的帧进行传送，提高网络的负载,即TCP延迟传输
            bootstrap.option(ChannelOption.TCP_NODELAY, true);

            // 设置TCP的长连接，默认的 keepalive的心跳时间是两个小时
            // bootstrap.option(ChannelOption.SO_KEEPALIVE, true);

            bootstrap.handler(new NettyClientInitializer());
            channel = bootstrap.connect(ip, port).sync().channel();
            // 处理
            handler(channel);
            // 应用程序会一直等待，直到channel关闭
            channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    /**
     * 业务逻辑
     *
     * @param channel
     */
    private void handler(Channel channel) {
        //如果任务里面执行的时间大于 period 的时间，下一次的任务会推迟执行。
        //本次任务执行完后下次的任务还需要延迟period时间后再执行
        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("====定时任务开始====");
                // 发送json字符串
                LocalDate now = LocalDate.now();
                String msg = "{\"time\":\""+now.toString()+"\",\"name\":\"admin\",\"age\":27}\n";
                channel.writeAndFlush(msg);
            }
        }, 2, 10, TimeUnit.SECONDS);

    }

    /**
     * 主动关闭
     */
    public void close() {
        group.shutdownGracefully();
    }

    /**
     * 测试入口
     *
     * @param args
     */
    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient();
        nettyClient.connect("127.0.0.1",8090);
    }
}