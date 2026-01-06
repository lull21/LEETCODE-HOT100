package graph_theory;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/03 15:16
 * @Description 994. 腐烂的橘子 【中等】
 * 给你一个 m x n 的网格 grid ，其中：
 * 0 表示一个空单元格
 * 1 表示一个新鲜橘子
 * 2 表示一个腐烂的橘子
 * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
 * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 * 输入：
 * grid = [[2,1,1],[1,1,0],[0,1,1]]
 * 输出：4
 * 题解：
 * 1. 先遍历一遍网格，将【所有腐烂的橘子加入队列】，同时【统计新鲜橘子的数量】。
 * 2. 然后从队列中取出腐烂的橘子，将其周围的新鲜橘子加入队列，同时将新鲜橘子的数量减一。
 * 3. 重复步骤2，直到队列为空或者新鲜橘子的数量为0。
 * 4. 如果新鲜橘子的数量为0，则返回分钟数；否则返回-1。
 **/

public class orangesRotting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        int minutes = 0;
        int freshOranges = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }

        // 没有新鲜橘子，直接返回0
        if (freshOranges == 0) {
            System.out.println(0);
            return;
        }

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty() && freshOranges > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] rotten = queue.poll();

                for (int[] dir : directions) {
                    int x = rotten[0] + dir[0];
                    int y = rotten[1] + dir[1];

                    if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != 1) {
                        continue;
                    }
                    grid[x][y] = 2;
                    freshOranges--;
                    queue.offer(new int[]{x, y});
                }
            }
            minutes++;
        }
        if (freshOranges == 0) {
            System.out.println(minutes);
        } else {
            System.out.println(-1);
        }

    }
}
