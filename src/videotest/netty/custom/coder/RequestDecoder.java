package videotest.netty.custom.coder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import videotest.netty.custom.constant.ConstantValue;
import videotest.netty.custom.model.Request;


public class RequestDecoder extends FrameDecoder {

    /**
     * 数据包基本长度
     */
    public static int BASE_LENTH = 4 + 2 + 2 + 4;


    @Override
    protected Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer) throws Exception {

        if (channelBuffer.readableBytes() >= BASE_LENTH) {

            // 防止socket字节流攻击, 跳过指定字符数
            if (channelBuffer.readableBytes() > 2048) {
                channelBuffer.skipBytes(channelBuffer.readableBytes());
            }

            int start = channelBuffer.readerIndex();
            while (true) {
                channelBuffer.markReaderIndex();
                if (channelBuffer.readInt() == ConstantValue.FLAG) {
                    break;
                }

                // 第一个不是包头的时候往后一个字节继续查看
                channelBuffer.resetReaderIndex();
                channelBuffer.readByte();

                // 长度有变得不满足了
                if (channelBuffer.readableBytes() < BASE_LENTH) {
                    return null;
                }
            }
            Request request = new Request();
            request.setModule(channelBuffer.readShort());
            request.setCmd(channelBuffer.readShort());
            // 查看数据是否到齐
            int length = channelBuffer.readInt();
            if (channelBuffer.readableBytes() < length) {
                // 还原读指针
                channelBuffer.readerIndex(start);
                return null;
            }

            byte[] data = new byte[length];
            channelBuffer.readBytes(data);
            request.setData(data);
            return request;

        }
        // 数据包不完整等待后续包
        return null;

    }
}
