package videotest.netty.custom.testserial;

import com.alibaba.fastjson.JSON;
import videotest.netty.custom.module.Player;

import java.util.Arrays;

/**
 * 自定义序列化协议
 */
public class Test4 {

	public static void main(String[] args) {
		
		Player player = new Player();
		player.setPlayerId(10001);
		player.setAge(22);
		player.getSkills().add(101);
		player.getResource().setGold(99999);
		
		byte[] bytes = player.getBytes();
		
		System.out.println(Arrays.toString(bytes));
		
		//==============================================
		
		Player player2 = new Player();
		player2.readFromBytes(bytes);
		System.out.println(JSON.toJSON(player2));

	}

}
