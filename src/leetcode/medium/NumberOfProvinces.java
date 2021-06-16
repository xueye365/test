package src.leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 字节跳动
 *
 * 省份数量
 * https://leetcode-cn.com/problems/number-of-provinces/
 * https://leetcode-cn.com/explore/interview/card/bytedance/243/array-and-sorting/1036/
 *
 */
public class NumberOfProvinces {


    public static void main(String[] args) {
        int[][] isConnected = {{1,0,0},{0,1,0},{0,0,1}};
        System.out.println(findCircleNum(isConnected));
    }

    /**
     * 深度优先搜索
     * @param isConnected
     * @return
     */
    public static int findCircleNum(int[][] isConnected) {
        boolean[] visited = new boolean[isConnected.length];
        int count = 0;
        for (int i = 0; i < isConnected.length; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, i);
                count++;
            }
        }
        return count;
    }


    public static void dfs(int[][] isConnected, boolean[] visited, int i) {
        int[] nexts = isConnected[i];
        for (int j = 0; j < nexts.length; j++) {
            if (!visited[j] && isConnected[i][j] == 1) {
                visited[j] = true;
                dfs(isConnected, visited, j);
            }
        }
    }


    /**
     * 广度优先搜索
     * @param isConnected
     * @return
     */
    public int findCircleNum2(int[][] isConnected) {
        int provinces = isConnected.length;
        boolean[] visited = new boolean[provinces];
        int circles = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < provinces; i++) {
            if (!visited[i]) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int j = queue.poll();
                    visited[j] = true;
                    for (int k = 0; k < provinces; k++) {
                        if (isConnected[j][k] == 1 && !visited[k]) {
                            queue.offer(k);
                        }
                    }
                }
                circles++;
            }
        }
        return circles;
    }


    /**
     * 查并集，没看懂
     * @param isConnected
     * @return
     */
    public int findCircleNum3(int[][] isConnected) {
        int provinces = isConnected.length;
        int[] parent = new int[provinces];
        for (int i = 0; i < provinces; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < provinces; i++) {
            for (int j = i + 1; j < provinces; j++) {
                if (isConnected[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }
        int circles = 0;
        for (int i = 0; i < provinces; i++) {
            if (parent[i] == i) {
                circles++;
            }
        }
        return circles;
    }

    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    public int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }





}
