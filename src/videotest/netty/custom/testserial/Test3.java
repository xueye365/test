package videotest.netty.custom.testserial;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import java.util.Arrays;

/**
 * netty序列化协议写法
 */
public class Test3 {


    public static void main(String[] args) {
        int id = 101;
        double age = 80.1;
        ChannelBuffer channelBuffer = ChannelBuffers.dynamicBuffer();
        channelBuffer.writeInt(id);
        channelBuffer.writeDouble(age);

        byte[] bytes = new byte[channelBuffer.writerIndex()];
        channelBuffer.readBytes(bytes);
        System.out.println(Arrays.toString(bytes));


        ChannelBuffer channelBuffer1 = ChannelBuffers.wrappedBuffer(bytes);
        System.out.println(channelBuffer1.readInt());
        System.out.println(channelBuffer1.readDouble());
    }


}
