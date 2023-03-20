package src.leetcode.easy;


import com.google.common.primitives.Longs;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/decode-the-message/submissions/
 * 解密消息
 */


public class DecodeMessage {


    public static void main(String[] args) {
        System.out.println(decodeMessage2("the quick brown fox jumps over the lazy dog", "vkbs bs t suepuv"));
    }

    public static String decodeMessage(String key, String message) {
        HashMap<Character, Character> map = new HashMap<>();
        char[] keys = key.toCharArray();
        int index = 'a';
        for (int i = 0; i < keys.length; i++) {
            if (!map.containsKey(keys[i]) && keys[i] != ' ') {
                map.put(keys[i], (char)index);
                index++;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        char[] messages = message.toCharArray();
        for (int i = 0; i < messages.length; i++) {
            if (messages[i] != ' ') {
                stringBuilder.append(map.get(messages[i]));
            } else {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }


    public static String decodeMessage2(String key, String message) {
        int[] map = new int[26];
        char[] keys = key.toCharArray();
        int index = 'a';
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] != ' ' && map[keys[i] - 'a'] == 0) {
                map[keys[i] - 'a'] = index;
                index++;
            }
        }

        StringBuilder stringBuilder = new StringBuilder(message.length());
        char[] messages = message.toCharArray();
        for (int i = 0; i < messages.length; i++) {
            if (messages[i] != ' ') {
                stringBuilder.append((char)(map[messages[i] - 'a']));
            } else {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }





}