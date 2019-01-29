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
        int remainder = s.length() % (numRows + numRows - 2);
        int row = (s.length() / (numRows + numRows - 2) * (numRows - 1)) + (remainder != 0 ? (remainder > numRows ? remainder - numRows + 1 : 1) : 0);
        char[][] a = new char[row][4];

        System.out.println(row);


        return null;
    }


}
