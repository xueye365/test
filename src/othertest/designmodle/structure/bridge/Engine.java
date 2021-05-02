package src.othertest.designmodle.structure.bridge;

/**
 * 桥接模式
 */
// 把抽象化与实现化解耦，使得二者可以独立变化
public interface Engine {
    //安装引擎
    public void installEngine();
}


/**
 *
 * 类(接口)描述:2000cc的引擎类 实现引擎接口
 * @author xnn
 * 2018年11月4日下午8:46:16
 */
class Engine2000 implements Engine {

    public void installEngine() {
        System.out.println("安装2000cc发动机");
    }
}


/**
 *
 * 类(接口)描述:2200cc的引擎类 实现引擎接口
 * @author xnn
 * 2018年11月4日下午8:47:14
 */
class Engine2200 implements Engine{

    public void installEngine() {
        System.out.println("安装2200cc发动机");
    }

}



/**
 *
 * 类(接口)描述:汽车抽象类
 * @author xnn
 * 2018年11月4日下午8:44:02
 */
abstract class Car {
    //汽车的引擎
    private Engine engine;

    public Car(Engine engine) {
        this.engine = engine;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
    //给汽车装引擎
    public abstract void installEngine();
}


/**
 *
 * 类(接口)描述:巴士类  继承Car
 * @author xnn
 * 2018年11月4日下午8:47:59
 */
class Bus extends Car{
    //有参构造函数(传了个引擎进去)
    public Bus(Engine engine) {
        super(engine);
    }
    //安装引擎的方法(调用的是Engine类的installEngine()方法)
    public void installEngine() {
        System.out.print("Bus：");
        this.getEngine().installEngine();
    }
}


/**
 *
 * 类(接口)描述:Jeep类  继承Car
 * @author xnn
 * 2018年11月4日下午8:50:47
 */
class Jeep extends Car {

    public Jeep(Engine engine) {
        super(engine);
    }

    public void installEngine() {
        System.out.print("Jeep：");
        this.getEngine().installEngine();
    }

}


class MainClass {
    public static void main(String[] args) {

        Engine engine2000 = new Engine2000();
        Engine engine2200 = new Engine2200();

        Car car1 = new Bus(engine2000);
        car1.installEngine();

        Car car2 = new Bus(engine2200);
        car2.installEngine();

        Car jeep1 = new Jeep(engine2000);
        jeep1.installEngine();

        Car jeep2 = new Jeep(engine2200);
        jeep2.installEngine();

    }
}