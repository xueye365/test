package src.leetcode.easy;


/**
 *
 * 剑指offer
 * 用两个栈实现队列
 *
 * https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
 *
 */

import java.util.Stack;

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
public class CQueue {

    public static void main(String[] args) {
         CQueue obj = new CQueue();
         obj.appendTail(1);
         int param_2 = obj.deleteHead();
        System.out.println(param_2);
    }

    Stack<Integer> in;
    Stack<Integer> out;

    public CQueue() {
        in = new Stack();
        out = new Stack();
    }

    public void appendTail(int value) {
        while (!out.empty()) {
            in.push(out.pop());
        }
        in.push(value);
    }

    public int deleteHead() {
        while (!in.empty()) {
            out.push(in.pop());
        }
        if (out.isEmpty()) {
            return -1;
        }
        return out.pop();
    }



//
//    Deque<Integer> stack1;
//    Deque<Integer> stack2;
//
//    public CQueue() {
//        stack1 = new LinkedList<Integer>();
//        stack2 = new LinkedList<Integer>();
//    }
//
//    public void appendTail(int value) {
//        stack1.push(value);
//    }
//
//    public int deleteHead() {
//        // 如果第二个栈为空
//        if (stack2.isEmpty()) {
//            while (!stack1.isEmpty()) {
//                stack2.push(stack1.pop());
//            }
//        }
//        if (stack2.isEmpty()) {
//            return -1;
//        } else {
//            int deleteItem = stack2.pop();
//            return deleteItem;
//        }
//    }

}