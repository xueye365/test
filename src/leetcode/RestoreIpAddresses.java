package src.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 复原IP地址
 * https://leetcode-cn.com/explore/interview/card/bytedance/242/string/1044/
 */
// todo
public class RestoreIpAddresses {
    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("0000"));
    }


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


}
