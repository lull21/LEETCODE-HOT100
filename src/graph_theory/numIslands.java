package graph_theory;

import java.util.Scanner;

/**
 * @Author 2hang1iang
 * @Date 2026/01/02 12:05
 * @Description 200.岛屿数量【中等】
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * 输入：
 * 4 5
 * 1 1 1 1 0
 * 1 1 0 1 0
 * 1 1 0 0 0
 * 0 0 0 0 0
 * 输出：1
 * 题解：已经遍历访问的岛屿，都沉了，沉岛思想
 * 1.遍历网格：从左上角开始，逐行逐列扫描每一个格子。
 * 2.发现岛屿：当我们遇到一个 '1' 时，说明我们发现了一个新岛屿。此时，岛屿数量计数器 +1。
 * 3.“沉掉”岛屿 (核心)：为了防止这块岛屿在后面的遍历中被重复计算，我们需要把与当前这个 '1' 水平或垂直相连的所有陆地全部变成 '0'（或者标记为已访问）。
 *  这就好比你发现了一块陆地，为了标记它，你放了一把火，火势顺着连接的陆地蔓延，直到把这一整块岛屿都烧成焦土（变成 '0'）。
 * 4.重复执行：继续向后扫描，直到遍历完整个网格。
 * 算法步骤：
 * 1.初始化岛屿数量 count = 0。
 * 2.循环遍历 m x n 网格的每一个单元格 (r, c)：
 *  如果 grid[r][c] == '1'：
 *   count++；
 *   调用 dfs(grid, r, c) 函数，将当前陆地及其相连的所有陆地置为 '0'。
 * 3.DFS 函数逻辑：
 *  边界检查：如果坐标 (r, c) 超出网格范围，或者当前格子是 '0'，直接返回（递归出口）。
 *  标记已访问：将 grid[r][c] 设为 '0'。
 *  向四个方向扩散：递归调用 DFS 处理上、下、左、右四个相邻格子。
 **/

public class numIslands {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] nums = new int[m][n];

        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                nums[i][j] = sc.nextInt();
            }
        }

        System.out.println(fun(nums, m, n));
    }

    public static int fun(int[][] nums, int m, int n) {
        if(nums == null || nums.length == 0) return 0;

        int count = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                // 当遇到陆地时，触发 DFS
                if (nums[r][c] == 1) {
                    count++;
                    dfs(nums, r, c);
                }
            }
        }
        return count;
    }

    public static void dfs(int[][] nums, int r, int c) {
        int m = nums.length;
        int n = nums[0].length;

        // 越界检查 或 遇到水 ('0') 则停止
        if (r < 0 || c < 0 || r >= m || c >= n || nums[r][c] == 0) {
            return;
        }

        // 将当前陆地“沉没”，避免重复计算
        nums[r][c] = 0;

        // 递归访问上下左右
        dfs(nums, r - 1, c); // 上
        dfs(nums, r + 1, c); // 下
        dfs(nums, r, c - 1); // 左
        dfs(nums, r, c + 1); // 右
    }
}
