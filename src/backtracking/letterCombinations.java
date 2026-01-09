package backtracking;

import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/06 21:26
 * @Description 17.电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 输入：
 * 输出：
 * 题解：
 **/

public class letterCombinations {
    static List<String> res = new ArrayList<>();
    static StringBuilder temp = new StringBuilder();
    private static Map<Character, List<Character>> map = new HashMap<>() {
        {
            put('2', Arrays.asList('a', 'b', 'c'));
            put('3', Arrays.asList('d', 'e', 'f'));
            put('4', Arrays.asList('g','h', 'i'));
            put('5', Arrays.asList('j', 'k', 'l'));
            put('6', Arrays.asList('m', 'n', 'o'));
            put('7', Arrays.asList('p', 'q', 'r', 's'));
            put('8', Arrays.asList('t', 'u', 'v'));
            put('9', Arrays.asList('w', 'x', 'y', 'z'));
        }
    };
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = sc.nextLine();
        backtrack(digits, 0);
        System.out.println(res);
    }

    public static void backtrack(String digits, int num) {
        if(num == digits.length()) {
            res.add(temp.toString());
            return;
        }

        List<Character> list = map.get(digits.charAt(num));

        for(int i = 0; i < list.size(); i++) {
            Character cur = list.get(i);
            temp.append(cur);
            backtrack(digits, num + 1);
            temp.deleteCharAt(num);
        }
    }
}
