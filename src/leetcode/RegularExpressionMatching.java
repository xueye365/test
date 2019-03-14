package src.leetcode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式匹配
 *
 * https://leetcode-cn.com/problems/regular-expression-matching/
 *
 * ***** 未完成， 测试用例和我理解的不一样
 *
 *
 */
public class RegularExpressionMatching {

    public static void main(String[] args) {
        System.out.println(isMatch("aaa", "ab*ac*a"));
    }

    public static boolean isMatch(String s, String p) {
        if (p == null || p == "") {
            return false;
        }
        int flag = 0;
        char last = 0;
        for (int i = 0; i < p.length(); i++) {
            if (s.length() <= flag) {
                return false;
            }
            if (p.charAt(i) == s.charAt(flag)) {
                last = p.charAt(i);
                flag++;
                if (s.length() <= flag) {
                    return true;
                } else {
                    continue;
                }
            } else if (p.charAt(i) == '.' || p.charAt(i) == '*') {
                if (p.charAt(i) == '.') {
                    flag++;
                    last = 0;
                    if (s.length() <= flag) {
                        return true;
                    }
                }
                if (p.charAt(i) == '*') {
                    while (last != 0) {
                        if (s.charAt(flag) == last) {
                            flag++;
                            if (s.length() - 1 < flag) {
                                if (i == p.length() - 1 || (i + 1 == p.length() - 1 && s.length() == flag && s.charAt(flag - 1) == last)) {
                                    return true;
                                }
                            } else {
                                continue;
                            }
                        } else {
                            break;
                        }
                    }
                    if (last == 0) {
                        flag++;
                        if (s.length() - 1 < flag ) {
                            if (i == p.length() - 1) {
                                return true;
                            }
                        }
                    }
                }
            } else {
                break;
            }
        }
        return false;
    }


    public static boolean isMatch2(String s, String p) {
        if (p == null || p == "") {
            return false;
        }
        // 编译正则表达式
        Pattern pattern = Pattern.compile(p);
        // 忽略大小写的写法
        Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }


}
