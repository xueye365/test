package src.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 字节跳动
 *
 * https://leetcode-cn.com/problems/triangle/
 * https://leetcode-cn.com/explore/interview/card/bytedance/246/dynamic-programming-or-greedy/1030/
 *
 */
public class Triangle {


    public static void main(String[] args) {
        ArrayList<List<Integer>> a = new ArrayList<>();
        a.add(new ArrayList<Integer>(){{add(2);}});
        a.add(new ArrayList<Integer>(){{add(3);add(4);}});
        a.add(new ArrayList<Integer>(){{add(6);add(5);add(7);}});
        a.add(new ArrayList<Integer>(){{add(4);add(1);add(8);add(3);}});
        System.out.println(minimumTotal(a));
    }
    public static int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null) {
            return 0;
        }
        if (triangle.size() == 1 && triangle.get(0) != null) {
            return triangle.get(0).get(0);
        }
        for (int i = triangle.size() - 1; i > 0; i--) {
            List<Integer> pre = triangle.get(i);
            List<Integer> integers = triangle.get(i - 1);
            for (int i1 = 0; i1 < integers.size(); i1++) {
                int min = Math.min(integers.get(i1) + pre.get(i1), integers.get(i1) + pre.get(i1 + 1));
                integers.set(i1, min);
            }

        }
        return triangle.get(0).get(0);
    }


    /**
     * 官方递归实现
     * @param triangle
     * @return
     */
    public int minimumTotal1(List<List<Integer>> triangle) {
        return  dfs(triangle, 0, 0);
    }

    private int dfs(List<List<Integer>> triangle, int i, int j) {
        if (i == triangle.size()) {
            return 0;
        }
        return Math.min(dfs(triangle, i + 1, j), dfs(triangle, i + 1, j + 1)) + triangle.get(i).get(j);
    }



}
