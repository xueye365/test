package leetcode;

/**
 * 正则表达式匹配
 *
 * https://leetcode-cn.com/problems/regular-expression-matching/
 *
 *
 */
public class RegularExpressionMatching {

    public static void main(String[] args) {
        System.out.println(isMatch("mississippi", "mis*is*p*."));
    }

    public static boolean isMatch(String s, String p) {
        int flag = 0;
        boolean isPoint = false;
        char last = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == s.charAt(flag)) {
                last = p.charAt(i);
                flag++;
                continue;
            } else {
                if (p.charAt(i) == '.') {
                    flag++;
                    isPoint = true;
                    if (s.length() <= flag) {
                        return true;
                    }
                }
                if (p.charAt(i) == '*') {
                    while (s.charAt(flag) == last) {

                    }
                    if (isPoint) {
                        flag++;
                        continue;
                    } else {
                        flag++;
                    }
                }
                return false;
            }

        }




        return true;
    }

}
