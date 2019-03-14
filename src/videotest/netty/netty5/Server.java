package src.videotest.netty.netty5;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

public class Server {

    public static void main(String[] args) {
        // 服务类
        ServerBootstrap bootstrap = new ServerBootstrap();

        // EventLoopGroup 对boss worker进行封装
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();

        // 设置线程池
        bootstrap.group(boss, work);

        // 设置socket工厂
        bootstrap.channel(NioServerSocketChannel.class);

        // 设置管道工厂
        bootstrap.childHandler(new ChannelInitializer<Channel>() {

            @Override
            protected void initChannel(Channel channel) throws Exception {
                channel.pipeline().addLast(new IdleStateHandler(5,5,10));
                channel.pipeline().addLast(new StringDecoder());
                channel.pipeline().addLast(new StringEncoder());
                channel.pipeline().addLast(new ServerHandler());
            }

        });

        // 设置参数
        // 对serverSocketchannel的设置，TCP主机有一个队列保存链接，accept会获取一个channel，会从队列取出一个链接，最多能链接2048，连接缓冲池
        bootstrap.option(ChannelOption.SO_BACKLOG, 2048);
        // 对socketChannel的设置，维持连接的活跃，清除死链接
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        // 对socketChannel的设置，关闭延迟发送
        bootstrap.option(ChannelOption.TCP_NODELAY, true);


        // 绑定端口
        ChannelFuture bind = bootstrap.bind(10101);

        System.out.println("start");

        // 等待服务端关闭
        try {
            // 这样会阻塞在这里，等待channel运行完再关闭
            bind.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 优雅的关闭
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }

    }



}
