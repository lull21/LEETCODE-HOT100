package matrix;
import java.util.Scanner;
/**
 * @Author 2hang1iang
 * @Date 2025/12/23 11:46
 * @Description 48.旋转图像-中等
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 * 输入：
 * 3
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * 输出：
 * 7 4 1
 * 8 5 2
 * 9 6 3
 * 题解：先对角线翻转，再左右镜像翻转
 * （1）：沿主对角线进行转置 (Transpose)
 * 主对角线就是从左上角 (0,0) 到右下角 (n-1, n-1) 的连线（下图中的 1, 5, 9）。
 * 我们将对角线两侧的元素进行互换，即：matrix[i][j] 与 matrix[j][i] 互换。
 * 原始:          转置后:
 * [1], 2,  3      [1], 4,  7
 *  4, [5], 6  =>   2, [5], 8
 *  7,  8, [9]      3,  6, [9]
 * 此时你会发现，原矩阵的第一行 [1, 2, 3] 变成了新矩阵的第一列，这已经很接近目标了。
 * （2）：左右镜像翻转 (水平翻转)
 * 接下来，我们只需要把每一行的元素进行左右颠倒即可。
 * 转置后: 左右翻转后 (最终结果):
 * [1, 4, 7]  =>   [7, 4, 1]
 * [2, 5, 8]  =>   [8, 5, 2]
 * [3, 6, 9]  =>   [9, 6, 3]
 * 如果题目要求 顺时针 旋转：先主对角线转置(\)，再左右镜像翻转。
 * 如果题目要求 逆时针 旋转：先主对角线转置(\)，再上下镜像翻转。
 **/

public class rotate {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] matrix = new int[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        // 第一步：沿主对角线转置矩阵
        // 遍历矩阵的上三角区域（包括对角线），与下三角区域对应元素交换
        for(int i = 0; i < n; i++) {
            // 注意这里 j 从 i 开始，避免重复交换和自己交换
            for(int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // 第二步：左右镜像翻转每一行
        for(int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;
            // 使用双指针法反转当前行
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(j < n - 1) {
                    System.out.print(matrix[i][j] + " ");
                } else {
                    System.out.println(matrix[i][j]);
                }
            }
        }
    }
}
