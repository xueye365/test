package othertest.arithmetic;

/**
 * 四种算法思想
 */
public class Algorithm {


    /**
     * 贪心算法
     * 霍夫曼编码（Huffman Coding）、Prim 和 Kruskal 最小生成树算法, Dijkstra 单源最短路径算法
     *
     * 针对一组数据，我们定义了限制值和期望值，希望从中选出几个数据，在满足限制值的情况下，期望值最大
     *
     * 1.100kg 物品的背包装豆子，单位质量最高价值的放入
     * 2.但用贪心算法解决问题的思路，并不总能给出最优解，eg：图找最短路径（路径中边的权值和最小）
     * 3. m 个糖果和 n 个孩子分：找出对糖果大小需求最小的，然后发给他剩下的糖果中能满足他的最小的糖果
     * 4.我们现在要用这些钱来支付 K 元，最少要用多少张纸币
     * 5.按照起始端点从小到大的顺序对这 n 个区间排序， 我们每次选择的时候，选左端点跟前面的已经覆盖的区间不重合的，右端点又尽量小的
     * 6.霍夫曼编码（Huffman Coding）：把出现频率比较多的字符，用稍微短一些的编码；出现频率比较少的字符，用稍微长一些的编码。
     *
     *
     * 霍夫曼编码是一种十分有效的编码方法，广泛用于数据压缩中，其压缩率通常在 20%～90% 之间。
     *
     * 问题：
     * 在一个非负整数 a 中，我们希望从中移除 k 个数字，让剩下的数字值最小，如何选择移除哪 k 个数字呢？
     * 由最高位开始，比较低一位数字，如高位大，移除，若高位小，则向右移一位继续比较两个数字，直到高位大于低位则移除，循环k次
     *
     *
     * 贪心算法的背包问题，数据是可以分的
     *
     * 溯算法的背包问题，数据是不可以分的
     *
     */


    /**
     * 分治算法：计算数组中的逆序数
     *
     * MapReduce 是 Google 大数据处理的三驾马车之一，另外两个是 GFS 和 Bigtable。它在倒排索引、PageRank 计算、网页分析等搜索引擎相关的技术中都有大量的应用
     *
     */
    private int num = 0; // 全局变量或者成员变量

    public int count(int[] a, int n) {
        num = 0;
        mergeSortCounting(a, 0, n - 1);
        return num;
    }

    private void mergeSortCounting(int[] a, int p, int r) {
        if (p >= r) return;
        int q = (p + r) / 2;
        mergeSortCounting(a, p, q);
        mergeSortCounting(a, q + 1, r);
        merge(a, p, q, r);
    }

    private void merge(int[] a, int p, int q, int r) {
        int i = p, j = q + 1, k = 0;
        int[] tmp = new int[r - p + 1];
        while (i <= q && j <= r) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            } else {
                num += (q - i + 1); // 统计 p-q 之间，比 a[j] 大的元素个数
                tmp[k++] = a[j++];
            }
        }
        while (i <= q) { // 处理剩下的
            tmp[k++] = a[i++];
        }
        while (j <= r) { // 处理剩下的
            tmp[k++] = a[j++];
        }
        for (i = 0; i <= r - p; ++i) { // 从 tmp 拷贝回 a
            a[p + i] = tmp[i];
        }
    }


    /**
     * 回溯算法
     * 比如数独、八皇后、0-1 背包、图的着色、旅行商问题、全排列等等
     * 回溯算法很多时候都应用在“搜索”这类问题上
     *
     * 回溯算法非常适合用递归代码实现
     *
     *
     *
     */


    /**
     * 八皇后问题
     * 我们有一个 8x8 的棋盘，希望往里放 8 个棋子（皇后），每个棋子所在的行、列、对角线都不能有另一个棋子
     */
    int[] result = new int[8];// 全局或成员变量, 下标表示行, 值表示 queen 存储在哪一列

    public void cal8queens(int row) { // 调用方式：cal8queens(0);
        if (row == 8) { // 8 个棋子都放置好了，打印结果
            printQueens(result);
            return; // 8 行棋子都放好了，已经没法再往下递归了，所以就 return
        }
        for (int column = 0; column < 8; ++column) { // 每一行都有 8 中放法
            if (isOk(row, column)) { // 有些放法不满足要求
                result[row] = column; // 第 row 行的棋子放到了 column 列
                cal8queens(row + 1); // 考察下一行
            }
        }
    }

    private boolean isOk(int row, int column) {// 判断 row 行 column 列放置是否合适
        int leftup = column - 1, rightup = column + 1;
        for (int i = row - 1; i >= 0; --i) { // 逐行往上考察每一行
            if (result[i] == column) return false; // 第 i 行的 column 列有棋子吗？
            if (leftup >= 0) { // 考察左上对角线：第 i 行 leftup 列有棋子吗？
                if (result[i] == leftup) return false;
            }
            if (rightup < 8) { // 考察右上对角线：第 i 行 rightup 列有棋子吗？
                if (result[i] == rightup) return false;
            }
            --leftup;
            ++rightup;
        }
        return true;
    }

    private void printQueens(int[] result) { // 打印出一个二维矩阵
        for (int row = 0; row < 8; ++row) {
            for (int column = 0; column < 8; ++column) {
                if (result[row] == column) System.out.print("Q ");
                else System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }




    /**
     * 正则表达式
     */
    class Pattern {
        private boolean matched = false;
        private char[] pattern; // 正则表达式
        private int plen; // 正则表达式长度

        public Pattern(char[] pattern, int plen) {
            this.pattern = pattern;
            this.plen = plen;
        }

        public boolean match(char[] text, int tlen) { // 文本串及长度
            matched = false;
            rmatch(0, 0, text, tlen);
            return matched;
        }

        // ti:文本游标
        // pj:表达式游标
        // text:文本
        // tlen:文本长度
        private void rmatch(int ti, int pj, char[] text, int tlen) {
            if (matched) return; // 如果已经匹配了，就不要继续递归了
            if (pj == plen) { // 正则表达式到结尾了
                if (ti == tlen) matched = true; // 文本串也到结尾了
                return;
            }
            if (pattern[pj] == '*') { // * 匹配任意个字符
                for (int k = 0; k <= tlen - ti; ++k) {
                    rmatch(ti + k, pj + 1, text, tlen);
                }
            } else if (pattern[pj] == '?') { // ? 匹配 0 个或者 1 个字符
                rmatch(ti, pj + 1, text, tlen);
                rmatch(ti + 1, pj + 1, text, tlen);
            } else if (ti < tlen && pattern[pj] == text[ti]) { // 纯字符匹配才行
                rmatch(ti + 1, pj + 1, text, tlen);
            }
        }
    }



    /**
     * 0,1背包问题
     * 在不超过背包所能装载重量的前提下，如何让背包中物品的总重量最大？
     * 用回溯算法解决这个问题的时间复杂度 O(2^n)
     *
     */

    public static int maxW = Integer.MIN_VALUE; // 存储背包中物品总重量的最大值

    // cw 表示当前已经装进去的物品的重量和；i 表示考察到哪个物品了；
    // w 背包重量；items 表示每个物品的重量；n 表示物品个数
    // 假设背包可承受重量 100，物品个数 10，物品重量存储在数组 a 中，那可以这样调用函数：
    // f(0, 0, a, 10, 100)
    public static void f(int i, int cw, int[] items, int n, int w) {
        if (cw == w || i == n) { // cw==w 表示装满了 ;i==n 表示已经考察完所有的物品
            if (cw > maxW) maxW = cw;
            return;
        }
        // 不选择当前物品，直接考虑下一个
        f(i + 1, cw, items, n, w);
        if (cw + items[i] <= w) {// 已经超过可以背包承受的重量的时候，就不要再装了
            // 选择当前物品，并考虑下一个
            f(i + 1, cw + items[i], items, n, w);
        }
    }


    /**
     *
     * 0,1背包问题
     * 动态规划
     * 复杂度是 O(n*w)
     * 动态规划是一种空间换时间的解决思路
     *
     */
    // weight:物品重量，n:物品个数，w:背包可承载重量
    public int knapsack(int[] weight, int n, int w) {
        boolean[][] states = new boolean[n][w+1]; // 默认值false
        states[0][0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        if (weight[0] <= w) {
            states[0][weight[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划状态转移
            for (int j = 0; j <= w; ++j) {// 不把第i个物品放入背包
                if (states[i-1][j] == true) states[i][j] = states[i-1][j];
            }
            for (int j = 0; j <= w-weight[i]; ++j) {//把第i个物品放入背包
                if (states[i-1][j]==true) states[i][j+weight[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[n-1][i] == true) return i;
        }
        return 0;
    }

    // 在空间复杂度上做了优化，使用数组存储
    public static int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w+1]; // 默认值false
        states[0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        if (items[0] <= w) {
            states[items[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = w-items[i]; j >= 0; --j) {//把第i个物品放入背包
                if (states[j]==true) states[j+items[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[i] == true) return i;
        }
        return 0;
    }

    // 对于一组不同重量、不同价值、不可分割的物品，我们选择将某些物品装入背包，在满足背包最大重量限制的前提下，背包中可装入物品的总价值最大是多少呢？
    // 升级难度之后的回溯算法
    class Test01{

        private int maxV = Integer.MIN_VALUE; // 结果放到maxV中
        private int[] items = {2,2,4,6,3};  // 物品的重量
        private int[] value = {3,4,8,9,6}; // 物品的价值
        private int n = 5; // 物品个数
        private int w = 9; // 背包承受的最大重量
        public void f(int i, int cw, int cv) { // 调用f(0, 0, 0)
            if (cw == w || i == n) { // cw==w表示装满了，i==n表示物品都考察完了
                if (cv > maxV) maxV = cv;
                return;
            }
            f(i + 1, cw, cv); // 选择不装第i个物品
            if (cw + items[i] <= w) {
                f(i + 1, cw + items[i], cv + value[i]); // 选择装第i个物品
            }
        }
    }

    // 升级难度之后的递归算法
    public static int knapsack3(int[] weight, int[] value, int n, int w) {
        int[][] states = new int[n][w+1];
        for (int i = 0; i < n; ++i) { // 初始化states
            for (int j = 0; j < w+1; ++j) {
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        if (weight[0] <= w) {
            states[0][weight[0]] = value[0];
        }
        for (int i = 1; i < n; ++i) { //动态规划，状态转移
            for (int j = 0; j <= w; ++j) { // 不选择第i个物品
                if (states[i-1][j] >= 0) states[i][j] = states[i-1][j];
            }
            for (int j = 0; j <= w-weight[i]; ++j) { // 选择第i个物品
                if (states[i-1][j] >= 0) {
                    int v = states[i-1][j] + value[i];
                    if (v > states[i][j+weight[i]]) {
                        states[i][j+weight[i]] = v;
                    }
                }
            }
        }
        // 找出最大值
        int maxvalue = -1;
        for (int j = 0; j <= w; ++j) {
            if (states[n-1][j] > maxvalue) maxvalue = states[n-1][j];
        }
        return maxvalue;
    }


    /**
     * 选出来的商品价格总和最大程度地接近满减条件（200 元）
     */

// items商品价格，n商品个数, w表示满减条件，比如200
    public static void double11advance(int[] items, int n, int w) {
        boolean[][] states = new boolean[n][3*w+1];//超过3倍就没有薅羊毛的价值了
        states[0][0] = true;  // 第一行的数据要特殊处理
        if (items[0] <= 3*w) {
            states[0][items[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = 0; j <= 3*w; ++j) {// 不购买第i个商品
                if (states[i-1][j] == true) states[i][j] = states[i-1][j];
            }
            for (int j = 0; j <= 3*w-items[i]; ++j) {//购买第i个商品
                if (states[i-1][j]==true) states[i][j+items[i]] = true;
            }
        }

        int j;
        for (j = w; j < 3*w+1; ++j) {
            if (states[n-1][j] == true) break; // 输出结果大于等于w的最小值
        }
        if (j == 3*w+1) return; // 没有可行解
        for (int i = n-1; i >= 1; --i) { // i表示二维数组中的行，j表示列
            if(j-items[i] >= 0 && states[i-1][j-items[i]] == true) {
                System.out.print(items[i] + " "); // 购买这个商品
                j = j - items[i];
            } // else 没有购买这个商品，j不变。
        }
        if (j != 0) System.out.print(items[0]);
    }




}
