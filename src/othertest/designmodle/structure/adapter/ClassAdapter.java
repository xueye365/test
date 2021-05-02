package src.othertest.designmodle.structure.adapter;

/**
 * 类适配器(继承原，实现目标)
 */
public class ClassAdapter extends Adaptee implements Target{

    @Override
    public void sampleOperation2() {
        System.out.println("adapter");
    }
}


/**
 * 目标接口
 */
interface Target {
    /**
     * 这是源类Adaptee也有的方法
     */
    public void sampleOperation1();
    /**
     * 这是源类Adapteee没有的方法
     */
    public void sampleOperation2();
}

/**
 * 原类
 */
class Adaptee {

    public void sampleOperation1(){}

}

