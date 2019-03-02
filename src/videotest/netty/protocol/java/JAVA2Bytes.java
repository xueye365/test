package videotest.netty.protocol.java;

import java.io.*;
import java.util.Arrays;

public class JAVA2Bytes {

    public static void main(String[] args) {
        byte[] bytes = toByte();
        toPlayer(bytes);
    }
    /**
     * 序列化
     */
    public static byte[] toByte() {
        byte[] bytes = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            Player player = new Player(101,20,"Peter");
            player.getSkill().add(1001);
            objectOutputStream.writeObject(player);
            bytes = byteArrayOutputStream.toByteArray();
            System.out.println(Arrays.toString(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;

    }

    /**
     * 反序列化
     */
    public static void toPlayer(byte[] bs) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bs));
            Player player = (Player)objectInputStream.readObject();
            System.out.println(player.getPlayerId() + player.getAge() + player.getName() + player.getSkill());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



}
