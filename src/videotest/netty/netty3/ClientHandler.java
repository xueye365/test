package src.videotest.netty.netty3;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.*;

public class ClientHandler extends SimpleChannelHandler{

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelConnected");
        super.channelConnected(ctx, e);
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        System.out.println("messageReceived");
        super.messageReceived(ctx, e);
         ChannelBuffer buffer = (ChannelBuffer)e.getMessage();

        byte[] array = buffer.array();
        String message = new String(array);
        System.out.println("handler1:" + message);

        //传递三个事件
        ctx.sendUpstream(new UpstreamMessageEvent(ctx.getChannel(), "abc", e.getRemoteAddress()));
        ctx.sendUpstream(new UpstreamMessageEvent(ctx.getChannel(), "efg", e.getRemoteAddress()));
        ctx.sendUpstream(new UpstreamMessageEvent(ctx.getChannel(), "message", e.getRemoteAddress()));

    }
    // 管道的传递

//  ChannelPipeline pipeline = Channels.pipeline();
//	pipeline.addLast("handler1", new MyHandler1());
//	pipeline.addLast("handler2", new MyHandler2());
//	return pipeline;

//    传递三个事件到第二个handler2，这个方法写到里面
//    @Override
//    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
//        String message = (String)e.getMessage();
//        System.out.println("handler2:" + message);
//    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        System.out.println("exceptionCaught");
        super.exceptionCaught(ctx, e);
    }


    /**
     * 必须是链接已经建立，关闭通道的时候才会触发
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelDisconnected");
        super.channelDisconnected(ctx, e);
    }

    /**
     * channel关闭的时候触发
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelClosed");
        super.channelClosed(ctx, e);
    }
}
