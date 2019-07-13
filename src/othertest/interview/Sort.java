package src.othertest.interview;




import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 排序
 * C语言使用快排
 * java语言使用堆排序
 *      Arrays.sort(a);
 *      1.如果要排序的数组的长度小于47，插入排序优先于快速排序。      -- 好像还会通过是否左区间区分但插入排序和双插入排序
 *      2.如果要排序的数组的长度小于286，快速排序优先用于合并排序。    --选取五个节点，是否存在相等，区分单轴快排和双轴快排
 *      3.如果要排序的短数组或字符数组的长度较大3200, 计数排序优先于快速排序。--byte,short，char会使用这种，其他数据类型不会使用计数排序
 *
 *      4.浮点数的排序比较复杂。先把NaN数放到数组的右边，然后和int一样，这种方式排完序之后缺点是无法进行+0和-0的排序。于是又进行了+0和-0的排序。
 *      5.对象的排序不是用的DualPivotQuicksort中的sort函数，而是采用了归并排序，原因是为了保证排序的稳定性。
 *
 *      归并排序中的最大运行次数 : 67
 *      归并排序中运行的最大长度 : 33
 *
 *      |--插入排序--|--快排--|--归并排序(int到此为止)--|--计数排序--|
 *      0          47       286                     3200        无穷
 *
 *
 */
public class Sort {

    public static void main(String[] args) {
        int[] a = new int[]{5666,3444,2333,1111,4000,2777,35,6999999};
        radixSort(a);
        sout(a);
        Arrays.sort(a);
//        CollectionUtils.q
    }

    /**
     * 冒泡 稳定排序，因为相等的时候不交换 基于比较得出的
     * 最好情况时间复杂度是 O(n)
     * 最坏情况时间复杂度为 O(n^2)
     * 平均情时间复杂度就是 O(n^2)
     * @param a
     */
    public static void bubbleSort(int[] a) {
        if (a.length <= 1) return;

        for (int i = 0; i < a.length; ++i) {
            // 提前退出冒泡循环的标志位
            boolean flag = false;
            for (int j = 0; j < a.length - i - 1; ++j) {
                if (a[j] > a[j+1]) { // 交换
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    flag = true;  // 表示有数据交换
                }
            }
            if (!flag) break;  // 没有数据交换，提前退出
        }
        sout(a);
    }


    /**
     * 插入排序
     * 稳定的排序，后面出现的元素，插入到前面出现元素的后面，这样就可以保持原有的前后顺序不变。
     * 最好情况时间复杂度是 O(n)
     * 最坏情况时间复杂度为 O(n^2)
     * 平均情时间复杂度就是 O(n^2)
     *
     * 插入更受欢迎！！！！
     * 冒泡排序的数据交换要比插入排序的数据移动要复杂，冒泡排序需要 3 个赋值操作，而插入排序只需要 1 个
     *
     * 随机生成一万个元素的数组，
     * 冒泡排序算法大约 700ms 才能执行完成，而插入排序只需要 100ms 左右就能搞定！
     * @param a
     */
    public static void insertionSort(int[] a){
        for (int i = 1; i < a.length; ++i) {
            int value = a[i];
            int j = i - 1;
            // 查找插入的位置
            for (; j >= 0; --j) {
                if (a[j] > value) {
                    a[j+1] = a[j];  // 数据移动
                } else {
                    break;
                }
            }
            a[j+1] = value; // 插入数据
        }
        sout(a);
    }



    /**
     * 选择排序
     * 每次都要找剩余未排序元素中的最小值，并和前面的元素交换位置，这样破坏了稳定性。
     * 最好情况时间复杂度是 O(n)
     * 最坏情况时间复杂度为 O(n^2)
     * 平均情时间复杂度就是 O(n^2)
     *
     * @param a
     */
    public static void selectionSort(int[] a) {
        if (a.length <= 1) return;

        for (int i = 0; i < a.length - 1; ++i) {
            // 查找最小值
            int minIndex = i;
            for (int j = i + 1; j < a.length; ++j) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            int tmp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = tmp;
        }
    }


    /**
     * 归并排序
     * 在合并的过程中，如果两端有值相同的元素，那我们可以先把左边的元素放入 tmp 数组。这样就保证了值相同的元素，在合并前后的先后顺序不变。
     * 所以，归并排序是一个稳定的排序算法。
     *
     * T(n) = 2*T(n/2) + n
     *      = 2*(2*T(n/4) + n/2) + n = 4*T(n/4) + 2*n
     *      = 4*(2*T(n/8) + n/4) + 2*n = 8*T(n/8) + 3*n
     *      = 8*(2*T(n/16) + n/8) + 3*n = 16*T(n/16) + 4*n
     *      ......
     *      = 2^k * T(n/2^k) + k * n
     *      ......
     * T(n) = 2^kT(n/2^k)+kn
     * 当 T(n/2^k)=T(1) 时，也就是 n/2^k=1
     * k=log2n
     * 我们将 k 值代入上面的公式
     * T(n)=Cn+nlog2n
     *
     * 归并排序的时间复杂度任何情况下都是 O(nlogn)
     * 归并排序不是原地排序算法 空间复杂度O(n)
     *
     * @param a
     */
    public static void mergeSort(int[] a) {
        mergeSortInternally(a, 0, a.length-1);
    }

    // 递归调用函数
    private static void mergeSortInternally(int[] a, int p, int r) {
        // 递归终止条件
        if (p >= r) return;

        // 取p到r之间的中间位置q,防止（p+r）的和超过int类型最大值
        int q = p + (r - p)/2;
        // 分治递归
        mergeSortInternally(a, p, q);
        mergeSortInternally(a, q+1, r);

        // 将A[p...q]和A[q+1...r]合并为A[p...r]
        merge(a, p, q, r);
    }

    private static void merge(int[] a, int p, int q, int r) {
        int i = p;
        int j = q+1;
        int k = 0; // 初始化变量i, j, k
        int[] tmp = new int[r-p+1]; // 申请一个大小跟a[p...r]一样的临时数组
        while (i<=q && j<=r) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++]; // i++等于i:=i+1
            } else {
                tmp[k++] = a[j++];
            }
        }

        // 判断哪个子数组中有剩余的数据
        int start = i;
        int end = q;
        if (j <= r) {
            start = j;
            end = r;
        }

        // 将剩余的数据拷贝到临时数组tmp
        while (start <= end) {
            tmp[k++] = a[start++];
        }

        // 将tmp中的数组拷贝回a[p...r]
        for (i = 0; i <= r-p; ++i) {
            a[p+i] = tmp[i];
        }
    }




    /**
     * 快速排序
     * 如果数组中有两个 8，其中一个是 pivot，经过分区处理之后，后面的 8 就有可能被放到了另一个 8 的前面，先后顺序就颠倒了。所以，快速排序并不是一个稳定的排序算法。
     * 归并排序的处理过程是由下到上的，先处理子问题，然后再合并。而快排正好相反，它的处理过程是由上到下的，先分区，然后再处理子问题
     *
     * 最好情况时间复杂度是 O(nlogn)
     * 最坏情况时间复杂度为 O(n^2)
     * 平均情时间复杂度就是 O(nlogn)
     * 优化点：1.合理选择分区点（三数取中法，五数取中，或者十数取中）、
     *        2.避免递归太深：第一种是限制递归深度， 一旦递归过深，超过了我们事先设定的阈值，就停止递归
     *                      第二种是通过在堆上模拟实现一个函数调用栈，手动模拟递归压栈、出栈的过程，这样就没有了系统栈大小的限制
     *
     * @param a
     */
    public static void quickSort(int[] a) {
        quickSortInternally(a, 0, a.length-1);
        sout(a);
    }

    // 快速排序递归函数，p,r为下标
    private static void quickSortInternally(int[] a, int p, int r) {
        if (p >= r) return;

        int q = partition(a, p, r); // 获取分区点
        quickSortInternally(a, p, q-1);
        quickSortInternally(a, q+1, r);
    }

    private static int partition(int[] a, int p, int r) {
        int pivot = a[r];
        int i = p;
        for(int j = p; j < r; ++j) {
            if (a[j] < pivot) {
                if (i == j) {
                    ++i;
                } else {
                    int tmp = a[i];
                    a[i++] = a[j];
                    a[j] = tmp;
                }
            }
        }

        int tmp = a[i];
        a[i] = a[r];
        a[r] = tmp;

        System.out.println("i=" + i);
        return i;
    }








    /**
     * 桶排序
     *
     * 稳定的
     *
     * 分成m个桶，每个桶进行快排，最后把全部桶正常排序
     * 最好情况时间复杂度是 O(n)      当桶的个数 m 接近数据个数 n 时
     * 最坏情况时间复杂度为 O(n^2)    当桶的个数 m 接近数据个数 0 时，相当于使用快排，快排的最坏情况是O(n^2)
     * 平均情时间复杂度就是 O(n*log(n/m))
     *
     * 空间复杂度是O(n + m) 因为使用到了额外空间
     *
     */
    public static void bucketSort(int[] arr, int bucketSize) {
        if (arr.length < 2) {
            return;
        }

        // 数组最小值
        int minValue = arr[0];
        // 数组最大值
        int maxValue = arr[1];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < minValue) {
                minValue = arr[i];
            } else if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }

        // 桶数量
        int bucketCount = (maxValue - minValue) / bucketSize + 1;
        int[][] buckets = new int[bucketCount][bucketSize];
        // 这个是为了记录扩容之后数据个数，有可能扩容后大小变成10，但是其实里头只有7个数据，这种情况，下面的排序就都需要使用这个
        int[] indexArr = new int[bucketCount];

        // 将数组中值分配到各个桶里
        for (int i = 0; i < arr.length; i++) {
            int bucketIndex = (arr[i] - minValue) / bucketSize;
            // 如果某个桶中落入的数据太多，将导致扩容
            if (indexArr[bucketIndex] == buckets[bucketIndex].length) {
                ensureCapacity(buckets, bucketIndex);
            }
            buckets[bucketIndex][indexArr[bucketIndex]++] = arr[i];
        }

        // 对每个桶进行排序，这里使用了快速排序
        int k = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (indexArr[i] == 0) {
                continue;
            }
            quickSortInternally(buckets[i], 0, indexArr[i] - 1);
            for (int j = 0; j < indexArr[i]; j++) {
                arr[k++] = buckets[i][j];
            }
        }
    }





    /**
     * 计数排序
     * 稳定
     * 时间复杂度：O(n+k)， k是最大数，没有最坏和最好
     * 对一个一个待排序的元素x，我们可以确定小于等于x的个数i，根据这个个数i，我们就可以把x放到索引i处
     * 空间复杂度：O(n+k)
     *
     */
    public static void countingSort(int[] a) {
        if (a.length <= 1) return;

        // 查找数组中数据的范围
        int max = a[0];
        for (int i = 1; i < a.length; ++i) {
            if (max < a[i]) {
                max = a[i];
            }
        }

        // 申请一个计数数组c，下标大小[0,max]
        int[] c = new int[max + 1];
        for (int i = 0; i < max + 1; ++i) {
            c[i] = 0;
        }

        // 计算每个元素的个数，放入c中
        for (int i = 0; i < a.length; ++i) {
            c[a[i]]++;
        }

        // 依次累加
        for (int i = 1; i < max + 1; ++i) {
            c[i] = c[i-1] + c[i];
        }

        // 临时数组r，存储排序之后的结果
        int[] r = new int[a.length];
        // 计算排序的关键步骤了，有点难理解
        for (int i = a.length - 1; i >= 0; --i) {
            int index = c[a[i]]-1;
            r[index] = a[i];
            c[a[i]]--;
        }

        // 将结果拷贝会a数组
        for (int i = 0; i < a.length; ++i) {
            a[i] = r[i];
        }
    }





    /**
     * 基数排序
     * 稳定
     * 时间复杂度：O(n*k)， k是可以分割的位数，当k不大的时候可以忽略
     * 对一个一个待排序的元素x，我们可以确定小于等于x的个数i，根据这个个数i，我们就可以把x放到索引i处
     * 空间复杂度：O(n+k)
     *
     */

    public static void radixSort(int[] arr) {
        int max = 0;
//      找到最大的数
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
//      获取最大数的位数
        int times = 0;
        while (max > 0) {
            max = max / 10;
            times++;
        }


//      创建一个二维的list
        List<ArrayList> list = new ArrayList<>();
//      创建10个list（每一位有从0到9，一共10个数，每个list数组用来存放每次迭代中，0-9 每个数组中需要装入的数）
        for (int i = 0; i < 10; i++) {
            ArrayList list1 = new ArrayList();
            //在二维数组中把这10个数组加进去，相当于二维数组的行，从0-9的行
            list.add(list1);
        }


//      进行times次分配和收集
        for (int i = 0; i < times; i++) {
//          分配
            for (int j = 0; j < arr.length; j++) {
                int x = arr[j] % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                // list.get(x) 是在返回第0的这个行的list上面的数，然后再 add(arr[j]) 是把当前的这个数添加到末尾去
                list.get(x).add(arr[j]);
            }

//            收集        ------------>   把这0-9共10个list里面的数值存到一个数组里面
            int count = 0;
            for (int j = 0; j < 10; j++) {
                ArrayList arrayList = list.get(j);
                for (int i1 = 0; i1 < arrayList.size(); i1++) {
                    arr[count++] = new Integer(arrayList.get(i1).toString());
                }
                arrayList.clear();

            }
        }
    }

    // 打印数据
    private static void sout(int[] a){
        for (int i = 0; i < Arrays.asList(a).get(0).length; i++) {
            System.out.println(Arrays.asList(a).get(0)[i]);
        }
    }

    // 桶排序，数组扩容
    private static void ensureCapacity(int[][] buckets, int bucketIndex) {
        int[] tempArr = buckets[bucketIndex];
        int[] newArr = new int[tempArr.length * 2];
        for (int j = 0; j < tempArr.length; j++) {
            newArr[j] = tempArr[j];
        }
        buckets[bucketIndex] = newArr;
    }

    // 基数排序，数组扩容
    private static int[] arrayAppend(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }


}















