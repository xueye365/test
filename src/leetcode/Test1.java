package leetcode;

import java.util.Arrays;

/**
 * 找中位数
 */
public class Test1 {

    public static void main(String[] args) {
        int[] a = {1};
        int[] b = {2,3,4,5,6};
        System.out.println(findMedianSortedArrays(a,b));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int len1 = nums1.length;
        int len2 = nums2.length;
        int flag1 = 0;
        int flag2 = 0;
        int[] result = new int[len1 + len2];
        if (len1 == 0) {
            result = nums2;
        }
        if (len2 == 0) {
            result = nums1;
        } else {
            while (flag1 < len1 && flag2 < len2) {
                while (flag1 < len1 && flag2 < len2 && nums1[flag1] <= nums2[flag2]) {
                    result[flag1 + flag2] = nums1[flag1];
                    flag1++;
                    if (flag1 >= len1) {
                        break;
                    }
                }
                while (flag1 < len1 && flag2 < len2 && nums2[flag2] <= nums1[flag1]) {
                    result[flag1 + flag2] = nums2[flag2];
                    flag2++;
                    if (flag2 >= len2) {
                        break;
                    }
                }
            }
            if (flag1 == len1 && flag2 != len2) {
                for (; flag2 < len2; flag2++) {
                    result[flag1 + flag2] = nums2[flag2];
                }
            }
            if (flag2 == len2 && flag1 != len1) {
                for (; flag1 < len1; flag1++) {
                    result[flag1 + flag2] = nums1[flag1];
                }
            }
        }

        int total = len1 + len2;
        double mid = total%2 == 0 ? (result[total/2-1] + result[total/2])/2.0 : result[total/2];
        return mid;
    }

}

