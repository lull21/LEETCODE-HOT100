package dynamic_plan;

import java.util.*;

/**
 * @Author 2hang1iang
 * @Date 2026/01/15 17:10
 * @Description 32. 最长有效括号 【困难】
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * 输入：s = ())(()
 * 输出：2
 * 题解：
 * 这是一个典型的 【动态规划问题】。我们可以定义一个一维数组 dp，其中 dp[i] 表示以下标 i 结尾的最长有效括号子串的长度。
 * 我们可以根据当前字符是否为 ')' 来更新 dp 数组。
 * 方法一：栈
 * 关键技巧：哨兵（Base）
 * 为了处理边界情况（比如一开始就是 )，或者计算第一个匹配的长度），我们需要在栈底先放一个 -1。
 * 遍历字符串：
 * 如果是 (：直接把当前 下标 入栈。
 * 如果是 )：先出栈（相当于匹配掉了一个左括号）。
 *   检查栈是否为空：
 *    若为空：说明刚才那个 ) 是多余的（没人和它配对）。它把栈底的参照物都弹走了。这时候，我们把当前这个 ) 的下标入栈，作为新的参照物（分割点）。
 *    若不为空：说明匹配成功！当前的有效长度 = 当前下标 - 栈顶元素。用这个长度去更新 maxAns。
 * 方法二：动态规划
 **/

public class longestValidParentheses {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int res = 0;

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if(stack.isEmpty()) {
                    stack.push(i);
                } else {
                    res = Math.max(res, i - stack.peek());
                }
            }
        }
        System.out.println(res);
    }
}
