package backtracking;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/09 10:11
 * @Description 51.N皇后 【困难】
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 每个解决方案包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 输入: 4
 * 输出: [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 题解：回溯
 * 关键：
 * 1. 每一行只能有一个皇后，所以我们可以用一个数组来表示每一行的皇后位置。
 * 2. 每一列只能有一个皇后，所以我们可以用一个数组来表示每一列的皇后位置。
 * 3. 每一条对角线只能有一个皇后，所以我们可以用一个数组来表示每一条对角线的皇后位置。
 * 4. 每一条反对角线只能有一个皇后，所以我们可以用一个数组来表示每一条反对角线的皇后位置。
 **/

public class solveNQueens {
    private static List<List<String>> res = new ArrayList<>();
    private static List<String> path = new ArrayList<>();
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        backtrack(n, 0);
        System.out.println(res);
    }

     private static void backtrack(int n, int row) {
         // 终止条件：如果已经处理完所有行，说明找到了一个有效的解决方案
         if (row == n) {
             // 终止条件：如果已经处理完所有行，说明找到了一个有效的解决方案
            res.add(new ArrayList<>(path));
            return;
        }
         // 尝试在当前行的每一列放置皇后
        for (int col = 0; col < n; col++) {
            // 检查在当前位置(row, col)放置皇后是否有效
            if (isValid(row, col, n)) {
                path.add(generateRowString(col, n));
                backtrack(n, row + 1);
                path.remove(path.size() - 1);
            }
        }
    }
    // 检查在当前位置(row, col)放置皇后是否有效
    private static boolean isValid(int row, int col, int n) {
         // 检查同一列是否有其他皇后
         for (int i = 0; i < row; i++) {
            if (path.get(i).charAt(col) == 'Q') {
                return false;
            }
        }
         // 检查左上对角线是否有其他皇后
         // 从当前位置向左上方遍历
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (path.get(i).charAt(j) == 'Q') {
                return false;
            }
        }
         // 检查右上对角线是否有其他皇后
         // 从当前位置向右上方遍历
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (path.get(i).charAt(j) == 'Q') {
                return false;
            }
        }
        return true;
    }

    // 生成一行字符串，表示在指定列放置皇后
    private static String generateRowString(int col, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i == col) {
                sb.append('Q');
            } else {
                sb.append('.');
            }
        }
        return sb.toString();
    }
}
