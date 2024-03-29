package src.videotest.netty.custom;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import src.videotest.netty.custom.coder.RequestEncoder;
import src.videotest.netty.custom.coder.ResponseDecoder;
import src.videotest.netty.custom.handler.HiHandler;
import src.videotest.netty.custom.model.Request;
import src.videotest.netty.custom.module.Player;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {


    public static void main(String[] args) throws InterruptedException {

        //服务类
        ClientBootstrap bootstrap = new  ClientBootstrap();

        //线程池
        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        //socket工厂
        bootstrap.setFactory(new NioClientSocketChannelFactory(boss, worker));

        //管道工厂
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decoder", new ResponseDecoder());
                pipeline.addLast("encoder", new RequestEncoder());
                pipeline.addLast("hiHandler", new HiHandler());
                return pipeline;
            }
        });

        //连接服务端
        ChannelFuture connect = bootstrap.connect(new InetSocketAddress("127.0.0.1", 10101));
//        Channel channel = connect.sync().getChannel();

        System.out.println("client start");

        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("请输入playerId");
            int playerId = Integer.parseInt(scanner.nextLine());
            System.out.println("请输入age");
            int age = Integer.parseInt(scanner.nextLine());

            Player player = new Player();
            player.setPlayerId(playerId);
            player.setAge(age);
            Request request = new Request();
            request.setModule((short) 1);
            request.setCmd((short) 1);
            request.setData(player.getBytes());
            //发送请求
//            channel.write(request);
        }
    }

}
