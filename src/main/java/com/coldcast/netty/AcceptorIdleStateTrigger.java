package com.coldcast.netty;

import org.springframework.stereotype.Component;

import com.coldcast.util.logUtil;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * Title : 服务端心跳检查
 * @{author} Administrator
 * @{date} 2020年3月31日
 * @{description} 
 */
@Component
@ChannelHandler.Sharable
public class AcceptorIdleStateTrigger extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (idleStateEvent.state() == IdleState.ALL_IDLE) {
                logUtil.info("已经5秒未收到客户端的消息了！");
                //向服务端送心跳包
                String heartbeat = "{\"msg\":\"server heart beat\"}\n";
                //发送心跳消息，并在发送失败时关闭该连接
                ctx.writeAndFlush(heartbeat)
                .addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    /*
        //备注 ， 状态
        switch (event.state()){
        case READER_IDLE:
            eventType = "读空闲";
            break;
        case WRITER_IDLE:
            eventType = "写空闲";
            break;
        case ALL_IDLE:
            eventType ="读写空闲";
            break;
    }*/
}
