package src.leetcode.easy;


/**
 *
 * 学生出勤记录 I
 *
 * https://leetcode-cn.com/problems/student-attendance-record-i/
 *
 */


public class checkRecord {


    public static void main(String[] args) {
        System.out.println(checkRecord("ALLAPPL"));
    }

    public static boolean checkRecord(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int ACount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'A') {
                ACount++;
            }
            if (ACount == 2) {
                return false;
            }
            if (s.charAt(i) == 'L') {
                int LCount = 1;
                for (int j = i + 1; j < s.length(); j++){
                    if (s.charAt(j) == 'L') {
                        LCount++;
                    } else {
                        i = j - 1;
                        break;
                    }
                    if (LCount == 3) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


}