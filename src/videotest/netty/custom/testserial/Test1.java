package src.videotest.netty.custom.testserial;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * 自定义序列化协议
 */
public class Test1 {


    public static void main(String[] args) throws IOException {
        int id = 101;
        int age = 21;

        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        arrayOutputStream.write(int2byte(id));
        arrayOutputStream.write(int2byte(age));
        byte[] byteArray = arrayOutputStream.toByteArray();
        System.out.println(Arrays.toString(byteArray));


        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(byteArray);
        byte[] idbytes = new byte[4];
        arrayInputStream.read(idbytes);
        System.out.println(byte2int(idbytes));

        byte[] agebytes = new byte[4];
        arrayInputStream.read(agebytes);
        System.out.println(byte2int(agebytes));
    }

    /**
     * 大端字节序列（先写高位再写低位）
     * @param i
     * @return
     */
    public static byte[] int2byte(int i){
        byte[] bytes = new byte[4];
        bytes[0] = (byte)(i >> 3 * 8);// 获取第一个字节
        bytes[1] = (byte)(i >> 2 * 8);
        bytes[2] = (byte)(i >> 1 * 8);
        bytes[3] = (byte)(i >> 0 * 8);
        return bytes;
    }
    /**
     * 大端字节序列（先写高位再写低位）
     * @param bytes
     * @return
     */
    public static int byte2int(byte[] bytes){
        return (bytes[0] << 3 * 8) |
        (bytes[1] << 2 * 8) |
        (bytes[2] << 1 * 8) |
        (bytes[3] << 0 * 8);
    }
}
