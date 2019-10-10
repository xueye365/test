package leetcode.medium;

import java.util.ArrayDeque;

/**
 *   简化路径
 *
 *   https://leetcode-cn.com/explore/interview/card/bytedance/242/string/1013/
 *
 */
public class SimplifyPath {

    public static void main(String[] args) {
        System.out.println(simplifyPath("/a//b////c/d//././/.."));
        System.out.println(simplifyPath("/a/../../b/../c//.//"));
        System.out.println(simplifyPath("/a/./b/../../c/"));
        System.out.println(simplifyPath("/home//foo/"));
        System.out.println(simplifyPath("/../"));
        System.out.println(simplifyPath("/home/"));
    }


    public static String simplifyPath(String path) {
        String[] split = path.split("/");

        ArrayDeque<String> deque = new ArrayDeque();

        for (int i = 0; i < split.length; i++) {
            if (".".equals(split[i]) || "".equals(split[i])) {
                continue;
            }
            if ("..".equals(split[i])) {
                if (deque.size() > 0) {
                    deque.peekLast();
                    deque.removeLast();
                    continue;
                }
            } else {
                deque.addLast(split[i]);
            }
        }
        String result = "";
        while (deque.size() > 0) {
            result += "/" + deque.pollFirst();
        }
        if ("".equals(result)) {
            result = "/";
        }
        return result;
    }

}
