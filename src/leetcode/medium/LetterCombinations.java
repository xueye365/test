package leetcode.medium;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 未完成
 * 电话号码的字母组合
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 */
public class LetterCombinations {

    public static void main(String[] args) {
        List<String> strings = letterCombinations("23");
    }


    public static final Map<Character, List<String>> map = new HashMap();

    static {
        map.put('2', new ArrayList(){{add("a"); add("b"); add("c");}});
        map.put('3', new ArrayList(){{add("d"); add("e"); add("f");}});
        map.put('4', new ArrayList(){{add("g"); add("h"); add("i");}});
        map.put('5', new ArrayList(){{add("j"); add("k"); add("l");}});
        map.put('6', new ArrayList(){{add("m"); add("n"); add("o");}});
        map.put('7', new ArrayList(){{add("p"); add("q"); add("r"); add("s");}});
        map.put('8', new ArrayList(){{add("t"); add("u"); add("v");}});
        map.put('9', new ArrayList(){{add("w"); add("x"); add("y"); add("z");}});
    }


    public static List<String> letterCombinations(String digits) {
        if (StringUtils.isEmpty(digits)) {
            return null;
        }
        if (digits.contains("1")) {
            digits.replace("1", "");
        }
        List<String> result = new ArrayList<>();
        letter(digits, result, 0, new StringBuffer());
        return result;
    }

    public static List<String> letter(String digits, List<String> result, Integer index, StringBuffer temp) {
        if (index == digits.length()) {
            result.add(temp.toString());
        } else {
            List<String> strings = map.get(digits.charAt(index));
            for (String s : strings) {
                temp.append(s);
                letter(digits, result, index++, temp);
                temp.deleteCharAt(index);
            }
        }
        return result;
    }



//    public List<String> letterCombinations(String digits) {
//        List<String> combinations = new ArrayList<String>();
//        if (digits.length() == 0) {
//            return combinations;
//        }
//        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
//            put("2", "abc");
//            put("3", "def");
//            put("4", "ghi");
//            put("5", "jkl");
//            put("6", "mno");
//            put("7", "pqrs");
//            put("8", "tuv");
//            put("9", "wxyz");
//        }};
//        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
//        return combinations;
//    }

//    public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
//        if (index == digits.length()) {
//            combinations.add(combination.toString());
//        } else {
//            char digit = digits.charAt(index);
//            String letters = phoneMap.get(digit);
//            int lettersCount = letters.length();
//            for (int i = 0; i < lettersCount; i++) {
//                combination.append(letters.charAt(i));
//                backtrack(combinations, phoneMap, digits, index + 1, combination);
//                combination.deleteCharAt(index);
//            }
//        }
//    }



}
