package src.videotest.netty.custom.coder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
import src.videotest.netty.custom.constant.ConstantValue;
import src.videotest.netty.custom.model.Response;


/**
 * 返回编码器
 * +——----——+——-----——+——----——+——----——+——-----——+——-----——+
 * | 包头   | 模块号   | 命令号  |  状态码   |   长度  |  数据  |
 * +——----——+——-----——+——----——+——----——+——-----——+——-----——+
 * | 4字节  | 2字节    | 2字节  |  4字节  |   4字节   |  数据   |
 * +——----——+——-----——+——----——+——----——+——-----——+——-----——+
 */
public class ResponseEncoder extends OneToOneEncoder{


    @Override
    protected Object encode(ChannelHandlerContext channelHandlerContext, Channel channel, Object o) throws Exception {

        Response response = (Response) o;
        ChannelBuffer channelBuffer = ChannelBuffers.dynamicBuffer();
        channelBuffer.writeInt(ConstantValue.FLAG);
        channelBuffer.writeShort(response.getModule());
        channelBuffer.writeShort(response.getCmd());
        channelBuffer.writeInt(response.getStateCode());
        channelBuffer.writeInt(response.getDataLength());
        if (response.getData() != null) {
            channelBuffer.writeBytes(response.getData());
        }
        return channelBuffer;
    }
}
