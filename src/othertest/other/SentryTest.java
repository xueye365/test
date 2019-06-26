package src.othertest.other;

/**
 * 这是一个哨兵的作用
 */
public class SentryTest {

    public static int test(int[] args, int n, char key) {
        if(args == null || n <= 0) {
            return -1;
        }
        int i = 0;
        while (i < n) {
            if (args[i] == key) {
                return i;
            }
            ++i;
        }
        return -1;
    }


    public static int test1(int[] a, int n, char key) {
        if(a == null || n <= 0) {
            return -1;
        }
        if (a[n-1] == key) {
            return n-1;
        }
        int tmp = a[n-1];
        // 哨兵
        a[n-1] = key;
        int i = 0;
        // 最后一个指定等于key，就可以跳出循环
        // 减少了i < n的判断
        while (a[i] != key) {
            ++i;
        }
        a[n-1] = tmp;

        if (i == n-1) {
            return -1;
        } else {
            return i;
        }
    }
}
