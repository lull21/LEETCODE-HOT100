package dynamic_plan;

import java.util.*;

/**
 * @Author 2hang1iang
 * @Date 2026/01/14 11:38
 * @Description 322.零钱兑换 【中等】
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3 ,解释：11 = 5 + 5 + 1
 * 题解：
 * 定义 dp[i] 为金额为 i 所需的最少硬币数。
 * 初始化 dp[0] = 0，其他 dp[i] 初始化为 Integer.MAX_VALUE。
 * 状态转移方程为：dp[i] = Math.min(dp[i], dp[i-coin] + 1)，其中 coin 为硬币面额。
 **/

public class coinChange {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] coins = new int[n];
        for(int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }
        int amount = sc.nextInt();

        int[] dp = new int [amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int coin : coins) {
            for(int i = coin; i <= amount; i++){
                if(dp[i-coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i-coin] + 1);
                }
            }
        }
        int res = dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
        System.out.println(res);
    }
}
