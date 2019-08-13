package othertest.other;

/**
 * 1，父类静态代码块>子类静态代码块>非静态代码块>构造方法>普通方法
 *      每次创建对象都会调用非静态代码块
 * 2.普通方法，父类引用指向子类对象，调用子类方法
 * 3.静态方法，父类引用指向子类对象，调用父类方法
 * 4.普通属性，父类引用指向子类对象，调用父类属性
 * 4.静态属性，父类引用指向子类对象，调用父类属性
 *
 */
public class SubAndSuperTest {

    public static void main(String[] args) {
        B b1 = new B();
        b1.test();
        System.out.println(b1.a);
        System.out.println(b1.b);
        A b2 = new B();
        b2.test();
        System.out.println(b2.a);
        System.out.println(b2.b);
    }
}

class A {
    int a = 1;
    static int b = 3;
    static {
        System.out.println("I`m A static");
    }
    {
        System.out.println("I`m A");
    }
    public A() {
        System.out.println("I`m A constractor");
    }
    public void test() {
        System.out.println("I`m A function");
    }
}

class B extends A{
    int a = 2;
    static int b = 4;
    static {
        System.out.println("I`m B static");
    }
    {
        System.out.println("I`m B");
    }
    public B() {
        System.out.println("I`m B constractor");
    }
    public void test() {
        System.out.println("I`m B function");
    }
}




