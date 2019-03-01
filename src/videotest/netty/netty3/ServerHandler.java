package videotest.netty.netty3;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;

public class ServerHandler extends SimpleChannelHandler{

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
}
