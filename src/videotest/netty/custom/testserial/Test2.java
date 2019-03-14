package src.videotest.netty.custom.testserial;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * 使用nio序列化协议 使用ByteBuffer
 */
public class Test2 {


    public static void main(String[] args) {
        int id = 101;
        int age = 21;
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putInt(id);
        buffer.putInt(age);
        byte[] array = buffer.array();
        System.out.println(Arrays.toString(array));


        ByteBuffer buffer1 = ByteBuffer.wrap(array);
        System.out.println(buffer1.getInt() + ","+ buffer1.getInt());

    }


}
