package videotest.netty.custom.coder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import videotest.netty.custom.constant.ConstantValue;
import videotest.netty.custom.model.Request;
import videotest.netty.custom.model.Response;


public class ResponseDecoder extends FrameDecoder {

    /**
     * 数据包基本长度
     */
    public static int BASE_LENTH = 4 + 2 + 2 + 4;


    @Override
    protected Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer) throws Exception {

        if (channelBuffer.readableBytes() >= BASE_LENTH) {

            int start = channelBuffer.readerIndex();
            while (true) {
                channelBuffer.markReaderIndex();
                if (channelBuffer.readInt() == ConstantValue.FLAG) {
                    break;
                }
            }

            Response response = new Response();
            response.setModule(channelBuffer.readShort());
            response.setCmd(channelBuffer.readShort());
            response.setStateCode(channelBuffer.readInt());
            // 查看数据是否到齐
            int length = channelBuffer.readInt();
            if (channelBuffer.readableBytes() < length) {
                // 还原读指针
                channelBuffer.readerIndex(start);
                return null;
            }

            byte[] data = new byte[length];
            channelBuffer.readBytes(data);
            response.setData(data);
            return response;

        }
        // 数据包不完整等待后续包
        return null;

    }
}
