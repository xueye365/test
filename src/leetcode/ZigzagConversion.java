package leetcode;

/**
 * Z 字形变换
 *
 * https://leetcode-cn.com/problems/zigzag-conversion/
 *
 */
public class ZigzagConversion {

    public static void main(String[] args) {
        System.out.println(convert("LEETCODEISHIRING", 3));
    }


    public static String convert(String s, int numRows) {
        if (s == null || s.length() == 0) {
            return "";
        }
        if (numRows < 2) {
            return s;
        }
        // 每组个数
        int a = numRows + numRows - 2;
        // 最后一组剩余个数
        int b = s.length() % a;
        // 最后一组列数
        int c = b != 0 ? (b > numRows ? b - numRows + 1 : 1) : 0;
        // 总共组数
        int d = s.length() / a;
        // 总共列数
        int list = (d * (numRows - 1)) + c;
        char[][] temp = new char[numRows][list];

        // 第多少行
        int rowFlag = 0;
        // 第多少列
        int lineFlag = 0;
        for (int i = 0; i < s.length(); i++) {
            // 标记是否是每组最开始一列
            int flag = lineFlag % (numRows - 1);
            if (flag == 0 && rowFlag < numRows) {
                // 如果是第一列则依次放入字母
                temp[rowFlag][lineFlag] = s.charAt(i);
                rowFlag++;
            }
            // 这一列满了则增加一列
            if (rowFlag == numRows) {
                lineFlag++;
                rowFlag = 0;
                continue;
            }
            // 如果不是第一列或第一列已经满了则列加一
            if (flag != 0){
                if (lineFlag <= list) {
                    rowFlag = numRows - 1 - lineFlag % (numRows - 1);
                    temp[rowFlag][lineFlag] = s.charAt(i);
                    rowFlag = 0;
                    lineFlag++;
                } else {
                    break;
                }
            }
        }
        String result = new String();
        for (int i = 0; i < temp.length; i++) {
            for (int i1 = 0; i1 < temp[i].length; i1++) {
                if (temp[i][i1] != '\u0000') {
                    result += temp[i][i1];
                }
            }
        }
        return result;
    }


}
