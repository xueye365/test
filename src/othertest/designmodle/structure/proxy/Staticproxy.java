package src.othertest.designmodle.structure.proxy;

/**
 * 代理模式(静态代理模型)
 */
public class Staticproxy {

    public static void main(String[] args) {
        AbstractObject obj = new ProxyObject();
        obj.operation();
    }

}

/**
 * 抽象对象角色：声明了目标对象和代理对象的共同接口，这样一来在任何可以使用目标对象的地方都可以使用代理对象。
 */
abstract class AbstractObject {
    //操作
    public abstract void operation();
}

/**
 * 定义了代理对象所代表的目标对象。
 */
class RealObject extends AbstractObject {
    @Override
    public void operation() {
        //一些操作
        System.out.println("一些操作");
    }
}

/**
 * 代理对象内部含有目标对象的引用，从而可以在任何时候操作目标对象；代理对象提供一个与目标对象相同的接口，以便可以在任何时候替代目标对象。
 * 代理对象通常在客户端调用传递给目标对象之前或之后，执行某个操作，而不是单纯地将调用传递给目标对象。
 */
class ProxyObject extends AbstractObject{
    RealObject realObject = new RealObject();
    @Override
    public void operation() {
        //调用目标对象之前可以做相关操作
        System.out.println("before");
        realObject.operation();
        //调用目标对象之后可以做相关操作
        System.out.println("after");
    }
}