package src.leetcode.easy;


/**
 *
 * 剑指offer
 * 替换空格x
 *
 * https://leetcode-cn.com/problems/sort-list/
 * https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/
 *
 */
public class TiHuanKongGeLcof {


    public static void main(String[] args) {
        System.out.println(replaceSpace("We are happy."));
    }

    public static String replaceSpace(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                count++;
            }
        }
        char[] result = new char[chars.length + count * 2];
        for (int i = 0, j = 0; i < chars.length && i < result.length; i++, j++) {
            if (chars[i] == ' ') {
                result[j] = '2';
                result[++j] = '0';
                result[++j] = '%';
            } else {
                result[j] = chars[i];
            }
        }
        return String.valueOf(result);

    }




}