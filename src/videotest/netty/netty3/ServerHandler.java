package videotest.netty.netty3;

import org.jboss.netty.channel.*;
import org.jboss.netty.handler.timeout.IdleState;
import org.jboss.netty.handler.timeout.IdleStateEvent;


// 第一个是检测心跳的时候继承的
//public class ServerHandler extends IdleStateAwareChannelHandler{
// 这个时普通handler继承的
public class ServerHandler extends SimpleChannelHandler {

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelConnected");
        super.channelConnected(ctx, e);
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        System.out.println("messageReceived");
        // 接受数据
        /*
        ChannelBuffer message = (ChannelBuffer)e.getMessage();
        String result = new String(message.array());
        System.out.println(result);
         */
        System.out.println(e.getMessage());

        // 回写数据
        /*(需要用ChannelBuffer包起来)
        ChannelBuffer channelBuffer = ChannelBuffers.copiedBuffer("hi".getBytes());
        ctx.getChannel().write(channelBuffer);
        */
//        ctx.getChannel().write("bbb");
//        super.messageReceived(ctx, e);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        System.out.println("exceptionCaught");
        super.exceptionCaught(ctx, e);
    }


    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelDisconnected");
        super.channelDisconnected(ctx, e);
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelClosed");
        super.channelClosed(ctx, e);
    }

    // 心跳检测
    // 当继承SimpleChannelHandler时使用这个方法
    @Override
    public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
        if (e instanceof IdleStateEvent) {
            if (IdleState.ALL_IDLE == ((IdleStateEvent)e).getState()) {
                // 关闭会话,踢玩家下线
                ChannelFuture write = ctx.getChannel().write("you will close");
                write.addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        ctx.getChannel().close();
                    }
                });
            }
        } else {
            super.handleUpstream(ctx, e);
        }
    }
//    当继承IdleStateAwareChannelHandler时使用这个方法
//    @Override
//    public void channelIdle(ChannelHandlerContext ctx, IdleStateEvent e) throws Exception {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("ss");
//        System.out.println(e.getState() + dateFormat.format(new Date()));
//    }
}
