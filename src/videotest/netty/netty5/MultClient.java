package videotest.netty.netty5;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多连接客户端
 */
public class MultClient {

    // 服务类
    private Bootstrap bootstrap = new Bootstrap();
    // 会话
    private List<Channel> channels = new ArrayList<>();

    private final AtomicInteger index = new AtomicInteger();


    public void init(int count) {
        // worker
        EventLoopGroup worker = new NioEventLoopGroup();
        // 设置线程池
        bootstrap.group(worker);
        // 设置一个工厂
        bootstrap.channel(NioSocketChannel.class);
        // 设置管道
        bootstrap.handler(new ChannelInitializer<io.netty.channel.Channel>() {

            @Override
            protected void initChannel(io.netty.channel.Channel channel) throws Exception {
                channel.pipeline().addLast(new StringDecoder());
                channel.pipeline().addLast(new StringEncoder());
                channel.pipeline().addLast(new ClientHandler());
            }
        });

        for (int i = 0; i < count; i++) {
            ChannelFuture connect = bootstrap.connect("127.0.0.1", 10101);
            channels.add(connect.channel());
        }
    }

    /**
     * 获取会话
     * @param
     * @return
     */
    public Channel nextChannel() {
        return getFirstActiveChannel(0);
    }


    private Channel getFirstActiveChannel(int count) {
        Channel channel = channels.get(Math.abs(index.getAndIncrement() % channels.size()));
        if (!channel.isActive()) {
            reconnect(channel);
            if (count > channels.size()) {
                throw new RuntimeException("no can use channel");
            }
        }
        return channel;
    }

    /**
     * 重连
     * @param channel
     * @return
     */
    private void reconnect(Channel channel) {
        // 最好的方式是写一个单任务队列，如果连接断开就扔一个任务，用锁有可能会阻塞线程
        synchronized (channel) {
            if (channels.indexOf(channel) == -1) {
                return ;
            }
        }
        Channel newChannel = bootstrap.connect("127.0.0.1", 10101).channel();
        channels.set(channels.indexOf(channel), newChannel);
    }


    public static void main(String[] args) {
        MultClient client = new MultClient();
        client.init(5);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.println("请输入");
                String buffer = bufferedReader.readLine();
                client.nextChannel().writeAndFlush(buffer);
            } catch (Exception e) {

            }
        }

    }

}
