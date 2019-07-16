package src.othertest.arithmetic;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 图
 *
 * 邻接矩阵来表示一个图：
 * 无向图，有向图，带权图， 比较浪费空间
 *
 * 邻接表存储方法：，比较节省空间，但是使用起来就比较耗时间
 * 稀疏图，顶点很多，但每个顶点的边并不多，那邻接矩阵的存储方法就更加浪费空间了
 * 每个顶点对应的链表里面，存储的是指向的顶点
 *
 *
 *
 *
 * 判断用户 A 是否关注了用户 B；
 * 判断用户 A 是否是用户 B 的粉丝；
 * 用户 A 关注用户 B；
 * 用户 A 取消关注用户 B；
 * 根据用户名称的首字母排序，分页获取用户的粉丝列表；  逆邻接表
 * 根据用户名称的首字母排序，分页获取用户的关注列表。  邻接表 数据量特别大的时候使用哈希算法分片方式存储在不同的机器上
 *
 *
 *
 */
public class Graph {


    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 4);
        graph.addEdge(1, 2);
        graph.addEdge(2, 5);
        graph.addEdge(3, 4);
        graph.addEdge(3, 6);
        graph.addEdge(4, 5);
        graph.addEdge(4, 7);
        graph.addEdge(6, 7);
        bfs(0, 7);
    }

    private static int v; // 顶点的个数
    private static LinkedList<Integer> adj[]; // 邻接表

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) { // 无向图一条边存两次
        adj[s].add(t);
         adj[t].add(s);
    }


    /**
     * 广度优先算法
     *
     * 时间复杂度是 O(V+E)，其中，V 表示顶点的个数，E 表示边的个数， 每个顶点都要进出一遍队列，每个边也都会被访问一次
     * 空间复杂度是 O(V) ： 空间消耗主要在几个辅助变量 visited 数组、queue 队列、prev 数组上。这三个存储空间的大小都不会超过顶点的个数
     *
     * @param s
     * @param t
     */
    public static void bfs(int s, int t) {
        if (s == t) return;
        boolean[] visited = new boolean[v];
        visited[s]=true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        int[] prev = new int[v];
        for (int i = 0; i < v; ++i) {
            prev[i] = -1;
        }
        while (queue.size() != 0) {
            int w = queue.poll();
            for (int i = 0; i < adj[w].size(); ++i) {
                int q = adj[w].get(i);
                if (!visited[q]) {
                    prev[q] = w;
                    if (q == t) {
                        print(prev, s, t);
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }
            }
        }
    }

    private static void print(int[] prev, int s, int t) { // 递归打印 s->t 的路径
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }


    /**
     * 深度优先搜索
     *
     * 时间复杂度是 O(E) ： 每条边最多会被访问两次，一次是遍历，一次是回退，E 表示边的个数
     * 空间复杂度就是 O(V) ： 递归调用栈的最大深度不会超过顶点的个数
     */

    boolean found = false; // 全局变量或者类成员变量

    public void dfs(int s, int t) {
        found = false;
        boolean[] visited = new boolean[v];
        int[] prev = new int[v];
        for (int i = 0; i < v; ++i) {
            prev[i] = -1;
        }
        recurDfs(s, t, visited, prev);
        print(prev, s, t);
    }

    private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
        if (found == true) return;
        visited[w] = true;
        if (w == t) {
            found = true;
            return;
        }
        for (int i = 0; i < adj[w].size(); ++i) {
            int q = adj[w].get(i);
            if (!visited[q]) {
                prev[q] = w;
                recurDfs(q, t, visited, prev);
            }
        }
    }




}
