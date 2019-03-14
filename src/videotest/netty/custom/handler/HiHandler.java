package src.videotest.netty.custom.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import src.videotest.netty.custom.model.Response;
import src.videotest.netty.custom.module.Resource;

public class HiHandler extends SimpleChannelHandler {


    /**
     * 接收消息
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        Response message = (Response)e.getMessage();

        if(message.getModule() == 1){
            if(message.getCmd() == 1){
                // 第一个模块的第一个命令
                Resource resource = new Resource();
                resource.readFromBytes(message.getData());
                System.out.println("gold:" + resource.getGold());
            }else if(message.getCmd() == 2){
            }
        }else if (message.getModule() == 1){
        }
    }
}
