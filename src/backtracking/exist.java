package backtracking;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/08 21:59
 * @Description 79. 单词搜索 【中等】
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 题解：回溯--从每个单元格开始，向四个方向搜索，直到找到单词或搜索到边界
 * 关键：是要记录当前搜索到的字符位置 k，当 k 等于单词长度时，说明单词已找到。
 **/

public class exist {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        char[][] board = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = sc.next().charAt(0);
            }
        }
        String word = sc.next();
        boolean res = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (backtrack(board, word, i, j, 0)) {
                    res = true;
                    break;
                }
            }
        }
        System.out.println(res);
    }

    private static boolean backtrack(char[][] board, String word, int i, int j, int k) {
        if (k == word.length()) {
            return true;
        }
        // 搜索到边界或当前单元格不匹配单词字符
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(k)) {
            return false;
        }
        // 标记当前单元格已访问
        char temp = board[i][j];
        board[i][j] = '#';
        // 向四个方向搜索
        boolean res = backtrack(board, word, i + 1, j, k + 1) ||
                      backtrack(board, word, i - 1, j, k + 1) ||
                      backtrack(board, word, i, j + 1, k + 1) ||
                      backtrack(board, word, i, j - 1, k + 1);
        board[i][j] = temp;
        return res;
    }
}
