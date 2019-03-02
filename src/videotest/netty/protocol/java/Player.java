package videotest.netty.protocol.java;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable{

    private long playerId;
    private int age;
    private String name;
    private List<Integer> skill = new ArrayList<>();

    public Player(long playerId, int age, String name) {
        this.playerId = playerId;
        this.age = age;
        this.name = name;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getSkill() {
        return skill;
    }

    public void setSkill(List<Integer> skill) {
        this.skill = skill;
    }
}
