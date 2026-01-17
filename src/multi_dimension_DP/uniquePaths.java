package multi_dimension_DP;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/15 18:06
 * @Description 62. 不同路径 【中等】
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 * 输入：m = 3, n = 7
 * 输出：28
 * 题解：二维动态数据 dp[i][j] 表示到达第 i 行第 j 列的不同路径数。
 * 初始化：第一行，第一列dp = 1。
 * 状态转移：dp[i][j] = dp[i-1][j] + dp[i][j-1]。
 **/

public class uniquePaths {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for(int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        System.out.println(dp[m-1][n-1]);
    }
}
