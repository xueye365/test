package videotest.netty.custom.coder;


import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
import videotest.netty.custom.constant.ConstantValue;
import videotest.netty.custom.model.Request;

/**
 * 请求编码器
 * +——----——+——-----——+——----——+——----——+——-----——+
 * | 包头   | 模块号   | 命令号  |  长度   |   数据  |
 * +——----——+——-----——+——----——+——----——+——-----——+
 * | 4字节  | 2字节    | 2字节  |  4字节  |   数据   |
 * +——----——+——-----——+——----——+——----——+——-----——+
 *
 */
public class RequestEncoder extends OneToOneEncoder{


    @Override
    protected Object encode(ChannelHandlerContext channelHandlerContext, Channel channel, Object o) throws Exception {
        Request request = (Request) o;
        ChannelBuffer channelBuffer = ChannelBuffers.dynamicBuffer();
        channelBuffer.writeInt(ConstantValue.FLAG);
        channelBuffer.writeShort(request.getModule());
        channelBuffer.writeShort(request.getCmd());
        channelBuffer.writeInt(request.getDataLength());
        if (request.getData() != null) {
            channelBuffer.writeBytes(request.getData());
        }
        return channelBuffer;
    }
}
