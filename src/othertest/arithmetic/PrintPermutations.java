package src.othertest.arithmetic;

/**
 * 打印一组数据的所有排列
 * 如果我们确定了最后一位数据，那就变成了求解剩下 n−1 个数据的排列问题。
 * 而最后一位数据可以是 n 个数据中的任意一个，因此它的取值就有 n 种情况。
 * 所以，“n 个数据的排列”问题，就可以分解成 n 个“n−1 个数据的排列”的子问题。
 */
public class PrintPermutations {

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4};
        printPermutations(a, 4, 4);
    }


    // 调用方式：
    // int[]a = a={1, 2, 3, 4}; printPermutations(a, 4, 4);
    // k表示要处理的子数组的数据个数
    public static void printPermutations(int[] data, int n, int k) {
        if (k == 1) {
            for (int i = 0; i < n; ++i) {
                System.out.print(data[i] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < k; ++i) {
            int tmp = data[i];
            data[i] = data[k-1];
            data[k-1] = tmp;

            printPermutations(data, n, k - 1);

            tmp = data[i];
            data[i] = data[k-1];
            data[k-1] = tmp;
        }
    }

}
