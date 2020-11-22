package othertest.arithmetic;

/**
 * 二分查找
 * 如果有 1000 万个整数数据
 *
 * 二分查找依赖的是顺序表结构,因为需要按照下标随机访问，链表随机访问的时间复杂度是 O(n)
 * 数据必须是有序的，每次插入、删除操作之后保证数据仍然有序
 * 如果要处理的数据量很小，完全没有必要用二分查找， 这里有一个例外。如果数据之间的比较操作非常耗时，不管数据量大小，我都推荐使用二分查找， eg：长度超过 300 的字符串
 * 二分查找的底层需要依赖数组这种数据结构， 1GB 大小的数据就需要 1GB 的连续内存空间。
 *
 */
public class Search {

    public static void main(String[] args) {
        int[] ints = {1, 3, 4, 8, 8, 8, 11, 18, 19, 20, 28392};
        System.out.println(bsearch(ints, 8, 8));
    }


    /**
     * 查找第一个等于这个数的位置
     * @param a
     * @param n
     * @param value
     * @return
     */
    public static int bsearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid =  low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if ((mid == 0) || (a[mid - 1] != value)) return mid + 1;
                else high = mid - 1;
            }
        }
        return -1;
    }


    /**
     * 查找最后一个等于这个数的位置
     * @param a
     * @param n
     * @param value
     * @return
     */
    public static int bsearchlastOne(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) >> 1;
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if (mid != n - 1 && a[mid + 1] != value) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找第一个大于等于这个数的位置
     * @param a
     * @param n
     * @param value
     * @return
     */
    public static int bsearchFirstBiggerOne(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) >> 1;
            if (a[mid] >= value) {
                if (mid == n - 1 || a[mid - 1] < value) {
                    return mid;
                } else {
                    high = mid + 1;
                }
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个小于等于这个数的位置
     * @param a
     * @param n
     * @param value
     * @return
     */
    public static int bsearchLastLowerOne(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) >> 1;
            if (a[mid] > value) {
                high = mid - 1;
            } else {
                if (mid == n - 1 || a[mid + 1] > value)  {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }



    // 正常的二分
    public static int bsearch(int[] a, int value) {
        int low = 0;
        int high = a.length - 1;

        while (low <= high) {
            // 极端情况下，low和high都很大，相加容易溢出
            // 右移1等于除以二
            int mid = low+(high-low)>>1;
            if (a[mid] == value) {
                return mid;
            } else if (a[mid] < value) {
                // 如果直接写成 low=mid 或者 high=mid，就可能会发生死循环
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

}
