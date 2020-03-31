package com.coldcast.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;


/**
 * Title : service初始化配置
 * @{author} Administrator
 * @{date} 2020年3月31日
 * @{description} 
 */
@Component
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private NettyServerHandler nettyServerHandler;

    @Autowired
    private AcceptorIdleStateTrigger idleStateTrigger;
    /**
     * 初始化channel
     */
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 分隔符解码器,处理半包。
        // maxFrameLength 表示一行最大的长度
        // Delimiters.lineDelimiter(),以/n,/r/n作为分隔符
        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        // 自定义心跳检测
        // 1）readerIdleTime：为读超时时间（即多长时间没有接受到客户端发送数据）
        // 2）writerIdleTime：为写超时时间（即多长时间没有向客户端发送数据）
        // 3）allIdleTime：所有类型的超时时间
        pipeline.addLast(new IdleStateHandler(5,0,0, TimeUnit.SECONDS));
        ch.pipeline().addLast(idleStateTrigger);

        pipeline.addLast(nettyServerHandler);
    }
    
}
