package src.leetcode.medium;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * 字节跳动
 *
 * 合并区间
 *
 * https://leetcode-cn.com/problems/merge-intervals/
 * https://leetcode-cn.com/explore/interview/card/bytedance/243/array-and-sorting/1046/
 *
 */
public class MergeIntervals {


    public static void main(String[] args) {
        int[][] isConnected = {{1,4},{0,2},{3,5}};
        int[][] merge = merge(isConnected);
        for (int[] ints : merge) {
            System.out.println(ints[0] + " " + ints[1]);
        }
    }


    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 1) return intervals;
        Arrays.sort(intervals, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        ArrayList<int[]> ints = new ArrayList();

        ints.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] interval1 = ints.get(ints.size() - 1);
            int[] interval2 = intervals[i];
            // 有交集
            if (interval1[1] >= interval2[0]) {
                interval1[0]=Math.min(interval1[0], interval2[0]);
                interval1[1]=Math.max(interval1[1], interval2[1]);
            } else {
                ints.add(interval2);
            }
        }
        return ints.toArray(new int[ints.size()][2]);
    }




}
