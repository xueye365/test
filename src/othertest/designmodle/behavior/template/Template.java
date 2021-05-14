package src.othertest.designmodle.behavior.template;

/**
 * 模版模式
 *
 * 复用和扩展
 *
 * 模板模式把一个算法中不变的流程抽象到父类的模板方法 templateMethod() 中，
 * 将可变的部分 method1()、method2() 留给子类 ContreteClass1 和 ContreteClass2 来实现
 *
 * 让框架用户可以在不修改框架源码的情况下，定制化框架的功能
 *
 */
public class Template {


    public static void main(String[] args) {
        AbstractClass demo = new ConcreteClass1();
        demo.templateMethod();
    }


}



abstract class AbstractClass {
    public final void templateMethod() {
        //...
        method1();
        //...
        method2();
        //...
    }

    protected abstract void method1();
    protected abstract void method2();
}

class ConcreteClass1 extends AbstractClass {
    @Override
    protected void method1() {
        //...
    }

    @Override
    protected void method2() {
        //...
    }
}

class ConcreteClass2 extends AbstractClass {
    @Override
    protected void method1() {
        //...
    }

    @Override
    protected void method2() {
        //...
    }
}


