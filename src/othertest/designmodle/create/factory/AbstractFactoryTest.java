package src.othertest.designmodle.create.factory;

//        抽象工厂模式的优点
//          分离接口和实现
//          　　客户端使用抽象工厂来创建需要的对象，而客户端根本就不知道具体的实现是谁，客户端只是面向产品的接口编程而已。也就是说，客户端从具体的产品实现中解耦。
//
//          使切换产品族变得容易
//          　　因为一个具体的工厂实现代表的是一个产品族，比如上面例子的从Intel系列到AMD系列只需要切换一下具体工厂。
//
//        抽象工厂模式的缺点
//          不太容易扩展新的产品
//          　　如果需要给整个产品族添加一个新的产品，那么就需要修改抽象工厂，这样就会导致修改所有的工厂实现类。

public class AbstractFactoryTest {

    public static void main(String[]args){
        ComputerEngineer cf = new ComputerEngineer();
        AbstractFactory af = new IntelFactory();
        cf.makeComputer(af);
    }
}

interface Cpu {
    public void calculate();
}

interface Mainboard {
    public void installCPU();
}

class IntelCpu implements Cpu {
    /**
     * CPU的针脚数
     */
    private int pins = 0;
    public  IntelCpu(int pins){
        this.pins = pins;
    }
    @Override
    public void calculate() {
        System.out.println("Intel CPU的针脚数：" + pins);
    }

}
class AmdCpu implements Cpu {
    /**
     * CPU的针脚数
     */
    private int pins = 0;
    public  AmdCpu(int pins){
        this.pins = pins;
    }
    @Override
    public void calculate() {
        System.out.println("AMD CPU的针脚数：" + pins);
    }
}


class IntelMainboard implements Mainboard {
    /**
     * CPU插槽的孔数
     */
    private int cpuHoles = 0;
    /**
     * 构造方法，传入CPU插槽的孔数
     * @param cpuHoles
     */
    public IntelMainboard(int cpuHoles){
        this.cpuHoles = cpuHoles;
    }
    @Override
    public void installCPU() {
        System.out.println("Intel主板的CPU插槽孔数是：" + cpuHoles);
    }
}

class AmdMainboard implements Mainboard {
    /**
     * CPU插槽的孔数
     */
    private int cpuHoles = 0;
    /**
     * 构造方法，传入CPU插槽的孔数
     * @param cpuHoles
     */
    public AmdMainboard(int cpuHoles){
        this.cpuHoles = cpuHoles;
    }
    @Override
    public void installCPU() {
        System.out.println("AMD主板的CPU插槽孔数是：" + cpuHoles);
    }
}

interface AbstractFactory {
    /**
     * 创建CPU对象
     * @return CPU对象
     */
    Cpu createCpu();
    /**
     * 创建主板对象
     * @return 主板对象
     */
    Mainboard createMainboard();
}

class IntelFactory implements AbstractFactory {

    @Override
    public Cpu createCpu() {
        return new IntelCpu(755);
    }

    @Override
    public Mainboard createMainboard() {
        return new IntelMainboard(755);
    }

}
class AmdFactory implements AbstractFactory {

    @Override
    public Cpu createCpu() {
        return new IntelCpu(938);
    }

    @Override
    public Mainboard createMainboard() {
        return new IntelMainboard(938);
    }

}

class ComputerEngineer {
    private Cpu cpu = null;
    private Mainboard mainboard = null;
    public void makeComputer(AbstractFactory af){
        prepareHardwares(af);
    }
    private void prepareHardwares(AbstractFactory af){
        this.cpu = af.createCpu();
        this.mainboard = af.createMainboard();
        this.cpu.calculate();
        this.mainboard.installCPU();
    }
}





