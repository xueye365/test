package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 复原IP地址
 * https://leetcode-cn.com/explore/interview/card/bytedance/242/string/1044/
 */
// todo
public class RestoreIpAddresses {
    public static void main(String[] args) {
        System.out.println(restoreIpAddresses2("25525511135"));
    }


    /**
     * 我写的
     *
     * 输入:"010010"
     * 输出[]
     * 预期结果["0.10.0.10","0.100.1.0"]
     *
     */
    public static List<String> restoreIpAddresses(String s) {
        List<String> redult = new ArrayList<>();
        if (s == null || "".equals(s)) {
            return redult;
        }
        String temp = "";
        if (s.length() == 4) {
            char a = s.charAt(0);
            for (int i = 0; i < s.length(); i++) {
                if (a == s.charAt(i) && i == s.length() - 1) {
                    temp += a;
                } else if (a == s.charAt(i)) {
                    temp += a + ".";
                } else {
                    break;
                }
            }
            redult.add(temp);
        }
        if (s.length() < 8 || s.length() > 12) {
            return redult;
        }
        String sub = "";
        sub += s.substring(0,3) + ".";
        sub += s.substring(3,6) + ".";
        String last = s.substring(6, s.length());
        for (int i = 1; i < last.length() - 1; i++) {
            String subTemp = sub;
            if (last.substring(0,i).length() > 0
                    && last.substring(0,i).length() <= 3
                    && last.substring(i,last.length()).length() > 0
                    && last.substring(i,last.length()).length() <= 3) {
                subTemp += last.substring(0,i) + ".";
                subTemp += last.substring(i,last.length());
                redult.add(subTemp);
            } else {
                continue;
            }
        }
        return redult;

    }


    /**
     * 我按照官方思路写的代码
     * 深度优先算法，使用回溯
     * @param s
     * @return
     */
    public static List<String> restoreIpAddresses2(String s) {
        List result = new ArrayList();
        Stack<String> stack = new Stack<String>();
        // i:ip一共四段,0,1,2,3
        // j:每段长度不能超过三个字符,1,2,3
        // k:游标走到的地方2:说明有两个字符已经在栈中
        int k = 0;
        for (int i = 0, j = 1; i < 4 && j <= 3; i++) {
            String temp = i == 3 ? s.substring(k, s.length()) : s.substring(k, k + j);
            if (check(temp)){
                stack.add(temp);
                // 游标向下走相应长度
                k = i == 3 ? s.length() : k + j;
                j = 1;
            } else {
                List<Integer> adjust = adjust(stack, k);
                j = adjust.get(0);
                k = adjust.get(1);
                i = adjust.get(2);
            }
            if (stack.size() == 4) {
                StringBuffer string = new StringBuffer();
                for (Object stemp : stack.toArray()) {
                    string.append(stemp).append('.');
                }
                result.add(string.delete(string.length() - 1, string.length()));

                // todo 结束条件,
                if (stack.stream().allMatch(a -> a.length() == 3) || stack.peek().length() != 3) {
                    return result;
                } else {
                    List<Integer> adjust = adjust(stack, k);
                    j = adjust.get(0);
                    k = adjust.get(1);
                    i = adjust.get(2);
                    continue;
                }
            }
        }
        return result;
    }


    public static boolean check(String s) {
        int len = s.length();
        if (len > 3) return false;
        return s.charAt(0) == '0' ? len == 1 : Integer.valueOf(s) <= 255;
    }

    /**
     * pop出上一个错误的格
     * @param stack
     * @param k
     * @return
     */
    public static List<Integer> adjust(Stack<String> stack, int k) {
        // 结果：第一个是j，第二个是k
        List result = new ArrayList();
        while (stack.size() != 0) {
            String pop = stack.pop();
            int popLen = pop.length();
            // 尝试长度增加或减小长度
            if (popLen == 3) {
                result = adjust(stack, k -= 3);
                return result;
            } else {
                // 游标回来相应长度
                k -= popLen;
                int i = stack.size() - 1;
                result.add(++popLen);
                result.add(k);
                result.add(i);
                return result;
            }
        }
        result.add(2);
        result.add(0);
        result.add(0);
        return result;
    }







    /**
     * 官方标准答案
     * 深度优先算法
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses3(String s) {
        n = s.length();
        this.s = s;
        backtrack(-1, 3);
        return output;
    }

    int n;
    String s;
    LinkedList<String> segments = new LinkedList<String>();
    ArrayList<String> output = new ArrayList<String>();

    public boolean valid(String segment) {
        int m = segment.length();
        if (m > 3)
            return false;
        return (segment.charAt(0) != '0') ? (Integer.valueOf(segment) <= 255) : (m == 1);
    }

    public void update_output(int curr_pos) {
    /*
    Append the current list of segments
    to the list of solutions
    */
        String segment = s.substring(curr_pos + 1, n);
        if (valid(segment)) {
            segments.add(segment);
            output.add(String.join(".", segments));
            segments.removeLast();
        }
    }

    public void backtrack(int prev_pos, int dots) {
        int max_pos = Math.min(n - 1, prev_pos + 4);
        for (int curr_pos = prev_pos + 1; curr_pos < max_pos; curr_pos++) {
            String segment = s.substring(prev_pos + 1, curr_pos + 1);
            if (valid(segment)) {
                segments.add(segment);  // 位置点
                if (dots - 1 == 0)      // 如果所有3个点都被放置
                    update_output(curr_pos);  // 将解决方案添加到输出中
                else
                    backtrack(curr_pos, dots - 1);  // continue to place dots
                segments.removeLast();  // remove the last placed dot
            }
        }
    }




}
