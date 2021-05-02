package src.othertest.designmodle.create;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 创建型的设计模式
 * 单俐模式
 *
 * 配置信息类、连接池类、ID 生成器类
 *
 * 线程冲突解决方案：日志互相覆盖问题， 类级别锁、分布式锁、并发队列、单例模式等解决方案
 *
 */
public class Singleton {

}


/**
 * 饿汉式
 * 优：将耗时的初始化操作，提前到程序启动的时候完成，这样就能避免在程序运行的时候，再去初始化导致的性能问题。
 *    如果实例占用资源多，按照 fail-fast 的设计原则（有问题及早暴露）
 *    这样也能避免在程序运行一段时间后，突然因为初始化这个实例占用资源过多，导致系统崩溃，影响系统的可用性
 */
class IdGenerator1 {
    private AtomicLong id = new AtomicLong(0);
    private static final IdGenerator1 instance = new IdGenerator1();
    private IdGenerator1() {}
    public static IdGenerator1 getInstance() {
        return instance;
    }
    public long getId() {
        return id.incrementAndGet();
    }
}


/**
 * 懒汉式，在单线程中是可行的，但是在多线程中可能会同时判断实俐为空，创建两个实俐，所以要在方法上加synchronized
 * 缺：函数的并发度很低
 */
class IdGenerator2 {
    private AtomicLong id = new AtomicLong(0);
    private static IdGenerator2 instance;
    private IdGenerator2() {}
    public static synchronized IdGenerator2 getInstance() {
        if (instance == null) {
            instance = new IdGenerator2();
        }
        return instance;
    }
    public long getId() {
        return id.incrementAndGet();
    }
}


/**
 * 双重检测
 * 优：这种实现方式解决了懒汉式并发度低的问题
 * 缺：因为指令重排序，可能会导致 IdGenerator 对象被 new 出来，并且赋值给 instance 之后，还没来得及初始化，就被另一个线程使用了。
 *    要解决这个问题，我们需要给 instance 成员变量加上 volatile 关键字
 *    但：我们现在用的高版本的 Java 已经在 JDK 内部实现中解决了这个问题
 */
class IdGenerator3 {
    private AtomicLong id = new AtomicLong(0);
    private static IdGenerator3 instance;
    private IdGenerator3() {}
    public static IdGenerator3 getInstance() {
        if (instance == null) {
            synchronized (IdGenerator3.class) {
                if (instance == null) {
                    instance = new IdGenerator3();
                }
            }
        }
        return instance;
    }
    public long getId() {
        return id.incrementAndGet();
    }
}


/**
 *
 * 静态内部类
 * 优：当外部类 IdGenerator 被加载的时候，并不会创建 SingletonHolder 实例对象
 *    只有当调用 getInstance() 方法时，SingletonHolder 才会被加载，这个时候才会创建 instance
 *    instance 的唯一性、创建过程的线程安全性，都由 JVM 来保证。所以，这种实现方法既保证了线程安全，又能做到延迟加载
 */
class IdGenerator4 {
    private AtomicLong id = new AtomicLong(0);
    private static IdGenerator4 instance;
    private IdGenerator4() {}
    private static class IdGeneratorHolder {
        private static final IdGenerator4 instance = new IdGenerator4();
    }
    public static IdGenerator4 getInstance() {
        return IdGeneratorHolder.instance;
    }
    public long getId() {
        return id.incrementAndGet();
    }
}

/**
 * 枚举
 * 优：这种实现方式通过 Java 枚举类型本身的特性，保证了实例创建的线程安全性和实例的唯一性
 *    枚举模式的单例还可以防止序列化和反序列化生成新的实例
 */
enum IdGenerator5 {
    INSTANCE;

    private AtomicLong id = new AtomicLong(0);
    public long getId() {
        return id.incrementAndGet();
    }
}

