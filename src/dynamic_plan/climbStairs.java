package dynamic_plan;
import java.util.Scanner;
/**
 * @Author 2hang1iang
 * @Date 2026/01/14 10:41
 * @Description 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 输入：n = 3
 * 输出：3
 * 题解：动态规划三部曲
 * 1. 确定dp数组以及下标的含义
 * dp[i]： 爬到第i层楼梯，有dp[i]种方法
 * 2. 确定递推公式
 * dp[i] = dp[i - 1] + dp[i - 2]
 * 3. dp数组如何初始化
 * dp[0] = 1
 * dp[1] = 1
 **/

public class climbStairs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        System.out.println(dp[n]);
    }
}
