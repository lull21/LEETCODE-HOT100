package backtracking;

import java.util.*;

/**
 * @Author 2hang1iang
 * @Date 2026/01/08 21:26
 * @Description 22. 括号生成 【中等】
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 输入：3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 题解：回溯--左括号数大于右括号数时，才能添加右括号
 **/

public class generateParenthesis {
    private static List<String> res = new ArrayList<>();
    private static StringBuilder temp = new StringBuilder();
    private static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        backtrack(0, 0);
        System.out.println(res);
    }
    private static void backtrack(int left, int right) {
        if (left == n && right == n) {
            res.add(temp.toString());
            return;
        }
        if (left < n) {
            temp.append('(');
            backtrack(left + 1, right);
            temp.deleteCharAt(temp.length() - 1);
        }
        if (left > right) {
            temp.append(')');
            backtrack(left, right + 1);
            temp.deleteCharAt(temp.length() - 1);
        }
    }
}
