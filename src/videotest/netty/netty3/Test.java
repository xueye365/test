package src.videotest.netty.netty3;

import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * 处理粘包处理
 */
public class Test {


//    public static void main(String[] args) throws IOException {
//        Socket socket = new Socket("127.0.0.1", 10101);
//        // 写1000个hello会发生粘包现象
//        for (int i = 0; i < 1000; i++) {
//            socket.getOutputStream().write("hello".getBytes());
//        }
//        socket.close();
//    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 10101);

        String message = "Hello";
        ByteBuffer buffer = ByteBuffer.allocate(4 + message.length());
        buffer.putInt(message.length());
        buffer.put(message.getBytes());

        for (int i = 0; i < 1000; i++) {
            socket.getOutputStream().write(buffer.array());
        }
        socket.close();
    }

}
