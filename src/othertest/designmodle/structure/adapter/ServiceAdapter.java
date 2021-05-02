package src.othertest.designmodle.structure.adapter;

/**
 * 缺省适配模式
 */
public class ServiceAdapter {
    public static void main(String[] args) {
        鲁智深 luzhishen = new 鲁智深();
        System.out.println(luzhishen.getName());
    }
}


interface 和尚 {
    public void 吃斋();
    public void 念经();
    public void 打坐();
    public void 撞钟();
    public void 习武();
    public String getName();
}

abstract class 天星 implements 和尚 {
    public void 吃斋(){}
    public void 念经(){}
    public void 打坐(){}
    public void 撞钟(){}
    public void 习武(){}
    public String getName(){
        return null;
    }
}

class 鲁智深 extends 天星{
    public void 习武(){
        System.out.println("好样的");
    }
    public String getName(){
        return "鲁智深";
    }
}
