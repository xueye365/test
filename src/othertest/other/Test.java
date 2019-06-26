package src.othertest.other;


import java.util.HashMap;
import java.util.HashSet;

public class Test {
    static int depth = 0;

    public static void main(String[] args) throws Exception {
        System.out.println(f(5));
        HashSet hashSet = new HashSet();
        HashMap hashMap = new HashMap();
    }


    public static int f(int n) throws Exception{
        ++depth;
        if (depth > 1000) throw new Exception();

        if (n == 1) {
            return 1;
        } else if (n == 0) {
            return 0;
        } else {
            return f(n-1) +  f(n-2);
        }
    }

}

