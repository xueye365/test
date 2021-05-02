package src.leetcode.other;


import java.util.Arrays;
import java.util.List;

/**
 * 奇数和偶数分开数组两端，在数组内完成转换
 */
public class OddNumberPluralSeparate {

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(2,3,1,4,5,0,3,4,6,1,1,1,1,1,4,5,7,7,0);
        System.out.println(test2(integers));
    }

    public static List<Integer> test2(List<Integer> list) {
        for (int i = 0, j = list.size() - 1; i <= j; ) {
            int modi = list.get(i) % 2;
            int modj = list.get(j) % 2;
            int tempi = list.get(i);
            int tempj = list.get(j);
            if (modi == 1 && modj == 0) {
                j--;
                i++;
                continue;
            } else if (modi == 1 && modj == 1) {
                i++;
            } else if (modi == 0 && modj == 0) {
                j--;
            } else {
                list.set(i, tempj);
                list.set(j, tempi);
            }
        }
        return list;
    }


    public static int[] solution(int[] a){
        int i=0;
        int j=a.length-1;
        while(i<j){
            //从前向后找到第一个偶数
            while (i<j&&a[i]%2!=0){
                i++;
            }
            //从后向前找到第一个奇数
            while (i<j&&a[j]%2==0){
                j--;
            }
            //交换奇数偶数的位置
            int t=a[i];
            a[i]=a[j];
            a[j]=t;
        }
        return a;
    }



}

