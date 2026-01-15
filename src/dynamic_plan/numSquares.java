package dynamic_plan;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/14 11:25
 * @Description 279.完全平方数 【中等】
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * 示例 1：
 * 输入：n = 12
 * 输出：3 ,解释：12 = 4 + 4 + 4
 * 题解：
 * 定义 dp[i] 为和为 i 的完全平方数的最少数量。
 * 初始化 dp[0] = 0，其他 dp[i] 初始化为 Integer.MAX_VALUE。
 * 状态转移方程为：dp[i] = Math.min(dp[i], dp[i-j*j] + 1)，其中 j*j 为完全平方数。
 **/

public class numSquares {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j*j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
            }
        }
        System.out.println(dp[n]);
    }
}
