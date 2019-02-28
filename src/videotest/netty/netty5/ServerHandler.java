package videotest.netty.netty5;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println(s);
        // 俩是一样的
//        channelHandlerContext.channel().writeAndFlush("bbb");
        channelHandlerContext.writeAndFlush("bbb");
    }
}
