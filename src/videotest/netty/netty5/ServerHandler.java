package videotest.netty.netty5;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class ServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println(s);
        // 俩是一样的
//        channelHandlerContext.channel().writeAndFlush("bbb");
        channelHandlerContext.writeAndFlush("bbb");
    }
    // 心跳检测
    // 用户事件触发。用于心跳检测
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        if (evt instanceof IdleStateEvent) {
            if (IdleState.ALL_IDLE == ((IdleStateEvent)evt).state()) {
                // 关闭会话,踢玩家下线
                ChannelFuture write = ctx.channel().write("you will close");
                write.addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        ctx.channel().close();
                    }
                });
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }


        super.userEventTriggered(ctx, evt);
    }
}
