package src.leetcode.hard;

import java.util.Arrays;

/**
 * 找中位数
 *
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 *
 */
public class MedianOfTwoSortedArrays {

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



    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int sum = nums1.length + nums2.length;
        int[] nums = new int[sum];
        int index = 0;

        for(int i = 0, j = 0; i < nums1.length || j < nums2.length;){
            if (i < nums1.length && j < nums2.length) {
                if (nums1[i] < nums2[j]) {
                    nums[index++] = nums1[i++];
                } else {
                    nums[index++] = nums2[j++];
                }
            } else if (i < nums1.length) {
                nums[index++] = nums1[i++];
            } else if (j < nums2.length) {
                nums[index++] = nums2[j++];
            }
        }
        if (sum % 2 == 1) {
            return nums[sum / 2];
        } else {
            return (nums[sum / 2] + nums[sum / 2 - 1]) / 2.0;
        }

    }



}

