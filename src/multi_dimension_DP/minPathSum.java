package multi_dimension_DP;

import java.util.Scanner;

/**
 * @Author 2hang1iang
 * @Date 2026/01/16 14:19
 * @Description 64. 最小路径和 【中等】
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 题解：二维动态规划 dp[i][j] 表示到达第 i 行第 j 列的最小路径和。
 * 初始化：dp[0][0] = grid[0][0]，首行、首列的路径和为累加。
 * 状态转移：dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j]。
 **/

public class minPathSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] grid = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for(int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for(int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        System.out.println(dp[m-1][n-1]);
    }
}
