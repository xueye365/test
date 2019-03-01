package videotest.netty.netty3;


import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务端
 */
public class Server {

    public static void main(String[] args) {
        // 服务类
        ServerBootstrap bootstrap = new ServerBootstrap();
        // 负责select
        ExecutorService boss = Executors.newCachedThreadPool();
        // 负责读写任务
        ExecutorService worker = Executors.newCachedThreadPool();
        // 设置niosocket的工厂
        bootstrap.setFactory(new NioServerSocketChannelFactory(boss, worker));
        // 设置管道工厂
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                // 管道里头装的都是过滤器
                // 这里这样写接收消息的时候就可以直接用e.getMessage()变成字符串
                pipeline.addLast("decoder", new StringDecoder());
                // 这里这样写回写数据可以直接用字符串
                pipeline.addLast("encoder", new StringEncoder());
                pipeline.addLast("helloHandler", new ServerHandler());
                return pipeline;
            }
        });

        bootstrap.bind(new InetSocketAddress(10101));
        System.out.println("start!");

    }

}
