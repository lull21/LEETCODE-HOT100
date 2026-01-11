package binary_search;

import java.util.*;

/**
 * @Author 2hang1iang
 * @Date 2026/01/09 11:11
 * @Description 74. 搜索二维矩阵 [中等]
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 输入: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出: true
 * 题解：
 * 我们可以将矩阵视为一个有序数组，只是我们需要将索引转换为矩阵的行和列。
 * 我们可以使用二分搜索来查找目标值。
 * 我们将 left 初始化为 0，将 right 初始化为 m * n - 1，其中 m 是矩阵的行数，n 是矩阵的列数。
 * 我们在循环中计算 mid 为 (left + right) / 2。
 * 如果 matrix[mid / n][mid % n] 等于目标值，我们返回 true。
 * 如果 matrix[mid / n][mid % n] 大于目标值，我们将 right 设为 mid - 1。
 * 如果 matrix[mid / n][mid % n] 小于目标值，我们将 left 设为 mid + 1。
 * 如果循环结束后没有找到目标值，我们返回 false。
 **/

public class searchMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] matrix = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        int target = sc.nextInt();

        for(int i = 0; i < m; i++) {
            if(matrix[i][n-1] < target) {
                continue;
            }
            int left = 0,right = n -1;
            while(left <= right) {
                int mid = left + ((right - left) >> 1);
                if(matrix[i][mid] == target) {
                    System.out.println(true);
                    return;
                } else if(matrix[i][mid] > target){
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        System.out.println(false);
    }
}
