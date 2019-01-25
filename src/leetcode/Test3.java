package leetcode;

/**
 * 字符串转换整数
 */
public class Test3 {

    public static void main(String[] args) {
        System.out.println(myAtoi("abc -123"));
    }

    public static int myAtoi(String str) {
        String str2 = str.trim();
        str2 = str.replace("\"", "a");
        str2 = str2.replace("\\", "a");
        if (str2 == null || str2 == "") {
            return 0;
        }
        boolean isStart = false;
        int start = 0;
        int len = 0;
        for (int i = 0; i <= str2.length() - 1; i++) {
            if ((!isStart && str2.charAt(i) == '-' && (i + 1 <= str2.length() - 1 && str2.charAt(i + 1) >= 48 && str2.charAt(i + 1) <= 57))
                    || (str2.charAt(i) >= 48 && str2.charAt(i) <= 57)
                    || str2.charAt(i) == 46) {
                if (!isStart) {
                    start = i;
                }
                isStart = true;
                len++;
            } else if (!isStart && str2.charAt(i) == 43) {
                if (i + 1 <= str2.length() - 1 && (str2.charAt(i + 1) < 48 || str2.charAt(i + 1) > 57)) {
                    return 0;
                }
                continue;
            } else if (str2.charAt(i) != 32 && (str2.charAt(i) < 48 || str2.charAt(i) > 57)) {
                break;
            } else if (!isStart) {
                continue;
            } else {
                break;
            }
        }
        if (!isStart) {
            return 0;
        }
        if (start == 0 && len == 0) {
            return 0;
        }
        if (len == 1 && str2.charAt(0) == '-') {
            return 0;
        }
        String str3 = str2.substring(start, start + len);
        Double result = Double.valueOf(str3);
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return result.intValue();
    }



}
