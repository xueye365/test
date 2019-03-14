package src.videotest.netty.protocol.protoautogener;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * protobuf学习
 */
public class PB2Bytes {

    public static void main(String[] args) throws InvalidProtocolBufferException {
        byte[] bytes = toByte();
        toPlayer(bytes);
    }

    /**
     * 序列化
     */
    public static byte[] toByte() {
        // 获取一个PBPlayer构造器
        PlayerModule.PBPlayer.Builder builder = PlayerModule.PBPlayer.newBuilder();
        // 设置数据
        // 如果少必须的数据则报错
        builder.setPlayerId(101).setAge(20).setName("Peter").addSkills(1001);
        // 构造数据
        PlayerModule.PBPlayer player = builder.build();
        // 序列化成字节数组
        byte[] bytes = player.toByteArray();
        System.out.println(bytes);
        return bytes;
    }

    /**
     * 反序列化
     */
    public static void toPlayer(byte[] bs) throws InvalidProtocolBufferException {
        PlayerModule.PBPlayer pbPlayer = PlayerModule.PBPlayer.parseFrom(bs);
        System.out.println(pbPlayer.getPlayerId() + pbPlayer.getAge() + pbPlayer.getName() + pbPlayer.getSkillsList().toArray());
    }
}
