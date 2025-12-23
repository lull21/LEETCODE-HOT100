package matrix;

import java.util.Scanner;

/**
 * @Author 2hang1iang
 * @Date 2025/12/23 11:49
 * @Description 240.搜索二维矩阵Ⅱ-中等
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * 输入：
 * 5 5
 * 1 4 7 11 15
 * 2 5 8 12 19
 * 3 6 9 16 22
 * 10 13 14 17 24
 * 18 21 23 26 30
 * 5
 * 输出：
 * true
 * 题解：“Z 字形查找”（也叫 “阶梯搜索” 或 “从右上角出发法”）
 * 虽然每行每列都是有序的，但它并不像完全展开的一维有序数组，因此不能直接使用简单的二分查找。
 * 我们要寻找一个特殊的起点，使得每次比较都能排除掉一行或一列。
 * 最佳起点：右上角 (0, n-1) 观察右上角的元素：
 * 它是所在行的最大值：往左走，数字会变小。
 * 它是所在列的最小值：往下走，数字会变大。
 * 搜索逻辑（类似二叉搜索树）：
 * 从右上角开始。
 * 如果当前值 == target：找到了，返回 true。
 * 如果当前值 > target：说明当前列下面的元素都比 target 大，向左移动一列 (col--)。
 * 如果当前值 < target：说明当前行左边的元素都比 target 小，向下移动一行 (row++)。
 *
 **/

public class searchMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] matrix = new int[m][n];
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        int target = sc.nextInt();

        // 1. 边界检查
        if (matrix == null || m == 0 || n == 0) {
            System.out.println(false);
            return;
        }

        // 2. 从右上角开始搜索 (第一行，最后一列)
        int row = 0;
        int col = n - 1;

        while (row < m && col >= 0) {
            if (matrix[row][col] == target) {
                System.out.println(true);
                return;
            } else if (matrix[row][col] > target) {
                // 当前值太大，往左移动以减小数值
                col--;
            } else {
                // 当前值太小，往下移动以增大数值
                row++;
            }
        }

        System.out.println(false);
    }
}

/**
 * “如果矩阵是完全有序的（即每一行的第一个数都大于前一行的最后一个数）呢？”
 * 那种情况（LeetCode 74）可以直接把整个矩阵看作一个长的一维数组，使用 O(\log(m \times n)) 的标准二分查找。
 * 本题（LeetCode 240）条件较弱，行与行之间没有全局顺序，所以 O(m+n) 是最优解。
 */