package leetcode.medium;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 字符串的排列
 * <p>
 * https://leetcode-cn.com/explore/interview/card/bytedance/242/string/1016/
 */
public class PermutationInString {

    public static void main(String[] args) {
//        String s1 = "ia", s2 = "eidabooo";

        String s1 = "adc";
        String s2 = "dcda";

        System.out.println(checkInclusion2(s1, s2));
    }


    /**
     * 通过滑动窗口，维持s2中一个和s1大小一致的窗口，计算s2中字符出现的次数和s1字符出现次数一致
     */
    private static boolean checkInclusion(String s1, String s2) {
        boolean result = false;
        if (s1 == null || s2 == null || "".equals(s1) || "".equals(s2) || s1.length() > s2.length()) {
            return false;
        }
        Map<Character, Integer> mapS1 = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            if (mapS1.containsKey(s1.charAt(i))) {
                mapS1.put(s1.charAt(i), mapS1.get(s1.charAt(i)) + 1);
            } else {
                mapS1.put(s1.charAt(i), 1);
            }
        }

        for (int i = 0; i < s2.length(); i++) {
            if (s1.length() + i > s2.length()) {
                break;
            }
            String subString = s2.substring(i, s1.length() + i);
            Map<Character, Integer> mapS2 = new HashMap<>();
            for (int j = 0; j < subString.length(); j++) {
                if (mapS2.containsKey(subString.charAt(j))) {
                    mapS2.put(subString.charAt(j), mapS2.get(subString.charAt(j)) + 1);
                } else {
                    mapS2.put(subString.charAt(j), 1);
                }
            }

            int count = 0;
            for (Map.Entry<Character, Integer> entry : mapS2.entrySet()) {
                if (!mapS1.containsKey(entry.getKey()) || !mapS1.get(entry.getKey()).equals(entry.getValue())) {
                    break;
                } else {
                    count++;
                }
            }
            if (count == mapS1.size()) {
                result = true;
                break;
            }
        }
        return result;
    }




    private static boolean checkInclusion2(String s1, String s2) {
        boolean result = false;
        if (s1 == null || s2 == null || "".equals(s1) || "".equals(s2) || s1.length() > s2.length()) {
            return false;
        }
        int[] mapS1 = new int[128];
        int mapS1Count = 0;
        for (int i = 0; i < s1.length(); i++) {
            // 记录一共有多少个字符
            if (mapS1[s1.charAt(i)] == 0) {
                mapS1Count++;
            }
            mapS1[s1.charAt(i)] += 1;
        }

        for (int i = 0; i < s2.length(); i++) {
            if (s1.length() + i > s2.length()) {
                break;
            }
            String subString = s2.substring(i, s1.length() + i);
            int[] mapS2 = new int[128];
            for (int j = 0; j < subString.length(); j++) {
                mapS2[subString.charAt(j)] += 1;
            }
            int count = 0;
            for (int i1 = 97; i1 < mapS2.length; i1++) {

                if (mapS1[i1] == mapS2[i1] && mapS1[i1] != 0 && mapS2[i1] != 0) {
                    count++;
                } else {
                    continue;
                }
            }

            if (count == mapS1Count && count != 0 && mapS1Count != 0) {
                result = true;
                break;
            }
        }
        return result;
    }


    /**
     * 最优
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion3(String s1, String s2) {
        if(s1 == null) return true;
        if(s2 == null) return false;
        if(s1.length() > s2.length()) return false;

        int[] s1a = new int[26];
        int[] win = new int[26];
        int l = 0, r = 0;
        while(r < s1.length()) {
            char s1c = s1.charAt(r);
            char s2c = s2.charAt(r);
            s1a[s1c - 'a']++;
            win[s2c - 'a']++;
            r++;
        }
        if(Arrays.equals(s1a, win)) return true;
        while(r < s2.length()) {
            win[s2.charAt(r) - 'a']++;
            win[s2.charAt(l) - 'a']--;
            if(Arrays.equals(s1a, win)) return true;
            r++;
            l++;
        }
        return false;
    }





}
