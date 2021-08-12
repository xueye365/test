package src.leetcode.hard;


import java.util.*;

/**
 *
 * 剑指offer
 * 数据流中的中位数
 *
 * https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/
 *
 */
public class MedianFinder {

    /**
     * Your MedianFinder object will be instantiated and called as such:
     * MedianFinder obj = new MedianFinder();
     * obj.addNum(num);
     * double param_2 = obj.findMedian();
     */

    Queue<Integer> pre;
    Queue<Integer> next;
    Integer midInteger;
    double midDouble;



    public static void main(String[] args) {
        MedianFinder obj = new MedianFinder();
        obj.addNum(6);
        System.out.println(obj.findMedian());
        obj.addNum(10);
        System.out.println(obj.findMedian());
        obj.addNum(2);
        System.out.println(obj.findMedian());
        obj.addNum(6);
        System.out.println(obj.findMedian());
        obj.addNum(5);
        System.out.println(obj.findMedian());
        obj.addNum(0);
        System.out.println(obj.findMedian());
        obj.addNum(6);
        System.out.println(obj.findMedian());
        obj.addNum(3);
        System.out.println(obj.findMedian());
        obj.addNum(1);
        System.out.println(obj.findMedian());
        obj.addNum(0);
        System.out.println(obj.findMedian());
        obj.addNum(0);
        System.out.println(obj.findMedian());
    }

    /**
     * 忘记了排序的版本
     */
    /** initialize your data structure here. */
//    public MedianFinder() {
//        pre = new ArrayDeque<>();
//        next = new ArrayDeque<>();
//    }

//    public void addNum(int num) {
//        if (pre.size() == next.size()) {
//            next.add(num);
//            midInteger = next.peek();
//            midDouble = new Double(midInteger);
//        } else if (next.size() > pre.size()) {
//            pre.add(midInteger);
//            next.add(num);
//            next.poll();
//            midDouble = (midInteger + next.peek()) / 2.0d;
//        }
//    }

//    public double findMedian() {
//        return midDouble;
//    }



//    List<Integer> list;

    /**
     * 加上了排序的版本
     */
    /** initialize your data structure here. */
//    public MedianFinder() {
//        list = new ArrayList();
//    }

//    public void addNum1(int num) {
//        list.add(num);
//        int i = list.size() - 2;
//        for (; i >= 0; i--) {
//            if (list.get(i) >= num) {
//                list.set(i + 1, list.get(i));
//            } else {
//                break;
//            }
//        }
//        list.set(i + 1, num);
//    }
//
//
//
//    public double findMedian1() {
//        if (list.size() == 0) return 0d;
//        int midIndex = list.size() / 2;
//        if (list.size() % 2 == 0) {
//            return (list.get(midIndex) + list.get(midIndex - 1)) / 2.0;
//        } else {
//            return list.get(midIndex);
//        }
//    }


    /**
     * 使用优先队列的版本
     */
    public MedianFinder() {
        // 大顶堆 存较小的一部分
        pre = new PriorityQueue<>((x, y) -> (y - x));
        // 小顶堆 存较大的一部分
        next = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (pre.size() != next.size()) {
            next.add(num);
            pre.add(next.poll());
        } else {
            pre.add(num);
            next.add(pre.poll());
        }
    }

    public double findMedian() {
        if (pre.size() != 0 && next.size() != 0 && pre.size() == next.size()) {
            return (pre.peek() + next.peek()) / 2.0;
        } else if (next.size() > pre.size()) {
            return next.peek();
        }
        return 0;
    }









}