package src.leetcode.medium;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * 动态规划
 * 括号生成
 * https://leetcode-cn.com/problems/generate-parentheses/
 *
 */


public class GenerateParenthesis {


    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }

    public static List<String> generateParenthesis(int n) {
        List<String> result= new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();
        dfs(n, 0, 0, stringBuffer, result);
        return result;
    }

    public static void dfs(int n, int open, int close, StringBuffer stringBuffer, List<String> result) {
        if (stringBuffer.length() == n * 2) {
            result.add(new String(stringBuffer));
            return;
        }
        if (open < n) {
            stringBuffer.append("(");
            dfs(n, open + 1, close, stringBuffer, result);
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        if (close < open) {
            stringBuffer.append(")");
            dfs(n, open, close + 1, stringBuffer, result);
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
    }


}