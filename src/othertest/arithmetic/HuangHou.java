package src.othertest.arithmetic;

import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * <p>description  :  </p>
 * <p>className    :  HuangHou </p>
 * <p>create time  :  2019-07-29 11:05 AM</p>
 * <p>@version     :  1.0</p>
 *  八皇后问题：m * m的矩阵，填入m个棋子，要求同一行、同一列、同一斜线只能有一个棋子
 * @author <p>     :  ZhangPengfei</p>
 **/
public class HuangHou {

    private static Set<Integer> set = new HashSet<Integer>() {{
        add(0);
        add(1);
        add(2);
        add(3);
    }};

    public static void main(String[] args) {
        int m = 8;
        List<Integer[][]> result = new ArrayList<>();
        Integer[][] data = new Integer[m][m];
        fill(0, data, result);
        print(result);
        System.out.println(result.size());
    }

    private static void fill(int m, Integer[][] data, List<Integer[][]> result) {
        if(m >= data.length) {
            result.add(data);
            return;
        }
        List<Integer> possibleSite = getPossibleSite(m, data);
        if(CollectionUtils.isEmpty(possibleSite)) {
            return;
        }
        for(Integer site : possibleSite) {
            Integer[][] temp = copyData(data);
            resolve(m, site, temp);
            fill(m + 1, temp, result);
        }
    }

    private static void resolve(int m, int n, Integer[][] data) {
        data[m][n] = 10;
        for(int i = 0; i < data.length; i++) {
            fillImpossible(i, n, data);
        }
        for(int i = 0; i < data.length; i++) {
            fillImpossible(m, i, data);
        }
        //处理斜线
        int temp = m + n;
        int x = 0;
        while(x < data.length) {
            fillImpossible(temp - x, x, data);
            x++;
        }

        temp = m - n;
        x = 0;
        while (x < data.length) {
            fillImpossible(temp + x, x, data);
            x++;
        }
    }

    private static void fillImpossible(int m, int n, Integer[][] data) {
        if(m >= 0 && m < data.length && n >= 0 && n <data.length) {
            Integer num = data[m][n];
            if(num == null) {
                data[m][n] = -1;
            }
        }
    }

    /**
     * 复制数组
     * @param data
     * @return
     */
    private static Integer[][] copyData(Integer[][] data) {
        Integer[][] copy = new Integer[data.length][data.length];
        for (int i = 0; i < data.length; i++) {
            System.arraycopy(data[i], 0, copy[i], 0, data.length);
        }
        return copy;
    }

    private static List<Integer> getPossibleSite(int m, Integer[][] data) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < data[m].length; i++) {
            if(data[m][i] == null || data[m][i] != -1) {
                list.add(i);
            }
        }
        return list;
    }

    private static void print(List<Integer[][]> result) {
        for(Integer[][] one : result) {

            for(int i = one.length - 1; i >= 0; i--) {
                Integer[] temp = one[i];
                for(Integer num : temp) {
                    if(num == null) {
                        System.out.print("\t");
                    } else {
                        System.out.print(num + "\t");
                    }
                }
                System.out.println();
            }

            System.out.println("================================================");
            System.out.println();
        }

    }
}