package src.othertest.other;

/**
 * String.intern方法
 */
public class InternDifference {


    public static void main(String[] args) {
        test2();



    }



    /**
     *
     * 1.6结果：false false
     * 1.7结果：false true
     *
     * 因为1.6 常量池在方法区，当发现常量池没有数据的时候会在常量池创建字符串并返回引用
     * 因为1.7 常量池在堆区，当发现常量池没有数据的时候---查看堆区是否有字符串----有----将对象引用入常量池
     *                                                                ----无----在常量池创建字符串并返回引用
     *
     * 所以这个例子中1.7的aa在常量池中存储的是引用，引用指向相同对象
     *
     */
    public static void test() {
        String s = new String("a");
        s.intern();
        String s2 = "a";
        System.out.println(s == s2);

        String s3 = new String("a") + new String("a");
        s3.intern();
        String s4 = "aa";
        System.out.println(s3 == s4);
    }


    public static void test2() {
        int s = 129;
        Integer s1 = 129;
        Integer s2 = new Integer(129);
        Integer s3 = new Integer(129);
        Integer s4 = new Integer(s1);
        Integer s5 = new Integer(s);
        Integer s6 = new Integer(s);

        System.out.println(s == s1);        // 猜测 true 实际 true
        System.out.println(s2 == s3);        // 猜测 false 实际 false
        System.out.println(s5 == s6);        // 猜测 false 实际 true
        System.out.println(s == s2);        // 猜测 true 实际 true   自动拆箱，Integer变成int类型
        System.out.println(s == s4);        // 猜测 true 实际 true
        System.out.println(s == s5);        // 猜测 true 实际 true
        System.out.println(s2 == s4);        // 猜测 false 实际 false
        System.out.println(s2 == s5);        // 猜测 false 实际 false
        System.out.println(s4 == s5);        // 猜测 false 实际 false
    }
    
    


}
