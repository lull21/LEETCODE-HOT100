package matrix;

import java.util.Scanner;

/**
 * @Author 2hang1iang
 * @Date 2025/12/22 15:16
 * @Description 73.矩阵置零-中等
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 * 输入：
 * 3 4
 * 0 1 2 0
 * 3 4 5 2
 * 1 3 1 5
 * 输出：
 * 0 0 0 0
 * 0 4 5 0
 * 0 3 1 0
 * 题解：
 * 最直观的方法是使用两个辅助数组分别记录哪一行、哪一列该清零，但这需要 O(m+n) 的空间。
 * 方法一：
 * 为了达到 O(1) 额外空间，最巧妙的思路是：利用矩阵的第一行和第一列来充当“账本”。
 * 1. 核心逻辑：借位做标记
 * 我们要利用矩阵自身的边缘来记录状态。
 * 第一步： 用两个【布尔变量】记录第一行和第一列原本是否包含 0。
 * 第二步： 遍历矩阵内部（除去第一行第一列），如果 matrix[i][j] == 0，就将该行对应的开头 matrix[i][0] 和该列对应的开头 matrix[0][j] 设为 0。
 * 第三步： 根据第一行和第一列的“账本”记录，将内部元素清零。
 * 第四步： 最后根据第一步的记录，决定是否将第一行和第一列整体清零。
 *
 * 方法二：O(m+n)空间，采用两个辅助布尔数组，分别记录
 **/

public class setZeroes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        // 1. 标记第一行和第一列原本是否有 0
        boolean rowZero = false;
        boolean colZero = false;

        int[][] matrix = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
                if(i == 0 && matrix[i][j] == 0) rowZero = true;
                if(j == 0 && matrix[i][j] == 0) colZero = true;
            }
        }

        // 2. 利用第一行和第一列记录内部元素的 0 情况
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 3. 根据第一行和第一列的标记，将内部元素置零
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 4.最后处理第一行和第一列
        if(colZero) {
            for(int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
        if(rowZero) {
            for(int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(j < n - 1) {
                    System.out.print(matrix[i][j] + " ");
                } else {
                    System.out.println(matrix[i][j]);
                }
            }
        }
    }

    /**
     * 方法二
     * @param matrix
     */
    public void setZeroes2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == 0) {
                    col[j] = row[i] = true;
                }
            }
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(col[j] || row[i] ) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
