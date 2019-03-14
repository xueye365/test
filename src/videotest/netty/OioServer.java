package src.videotest.netty;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 传统socket分析
 *
 * 弊端：无法做长连接的服务器，一个线程只能被一个客户端使用
 * 可以做短链接的服务器，eg: Tomcat
 *
 *
 */
public class OioServer {

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();
        // 创建socket服务，监听10101端口
        ServerSocket serverSocket = new ServerSocket(10101);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("创建了一个客户端");
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        handler(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    public static void handler(Socket socket) {
        byte[] bytes = new byte[1024];
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
            while (true) {
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}
