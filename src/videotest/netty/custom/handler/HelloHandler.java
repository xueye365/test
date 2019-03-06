package videotest.netty.custom.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import videotest.netty.custom.constant.StateCode;
import videotest.netty.custom.model.Request;
import videotest.netty.custom.model.Response;
import videotest.netty.custom.module.Player;
import videotest.netty.custom.module.Resource;

/**
 * 消息接受处理类
 * @author -琴兽-
 *
 */
public class HelloHandler extends SimpleChannelHandler {

	/**
	 * 接收消息
	 */
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {

		Request message = (Request)e.getMessage();
		
		if(message.getModule() == 1){
			
			if(message.getCmd() == 1){
				
				Player fightRequest = new Player();
				fightRequest.readFromBytes(message.getData());
				
				System.out.println("PlayerId:" +fightRequest.getPlayerId() + "   " + "age:" + fightRequest.getAge());
				
				//回写数据
				Resource resource = new Resource();
				resource.setGold(9999);
				
				Response response = new Response();
				response.setModule((short) 1);
				response.setCmd((short) 1);
				response.setStateCode(StateCode.SUCCESS);
				response.setData(resource.getBytes());
				ctx.getChannel().write(response);
			}else if(message.getCmd() == 2){
				
			}
			
		}else if (message.getModule() == 1){
			
			
		}
	}

	/**
	 * 捕获异常
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		System.out.println("exceptionCaught");
		super.exceptionCaught(ctx, e);
	}

	/**
	 * 新连接
	 */
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println("channelConnected");
		super.channelConnected(ctx, e);
	}

	/**
	 * 必须是链接已经建立，关闭通道的时候才会触发
	 */
	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println("channelDisconnected");
		super.channelDisconnected(ctx, e);
	}

	/**
	 * channel关闭的时候触发
	 */
	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println("channelClosed");
		super.channelClosed(ctx, e);
	}
}
