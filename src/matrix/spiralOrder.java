package matrix;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2025/12/23 11:00
 * @Description 54.螺旋矩阵-中等
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * 输入：
 * 3 3
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * 输出：
 * 1 2 3 6 9 8 7 4 5
 * 题解：边界收缩（模拟-自己心里模拟一遍）
 *  我们可以想象有四个“挡板”围住了当前的矩阵：
 * top: 当前未遍历区域的上边界
 * bottom: 当前未遍历区域的下边界
 * left: 当前未遍历区域的左边界
 * right: 当前未遍历区域的右边界
 *
 * 螺旋遍历的逻辑就像剥洋葱一样，每一轮包含四个步骤：
 * 1.从左到右遍历 top 行，完成后 top 下移（top++）。
 * 2.从上到下遍历 right 列，完成后 right 左移（right--）。
 * 3.从右到左遍历 bottom 行，完成后 bottom 上移（bottom--）。
 * 4.从下到上遍历 left 列，完成后 left 右移（left++）。
 * 注意： 在执行第 3 步和第 4 步之前，必须再次检查 top <= bottom 或 left <= right，因为前面的边界收缩可能已经导致遍历结束，避免重复遍历。
 **/

public class spiralOrder {
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

        List<Integer> res = new ArrayList<>();

        // 1. 初始化四个边界
        int top = 0;
        int bottom = m - 1;
        int left = 0;
        int right = n - 1;

        // 当边界没有重叠时候，继续循环
        while(top <= bottom && left <= right) {
            // 步骤一：从左到右遍历上边界
            for(int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            top++; // 上边界下移

            // 步骤二：从上到下遍历右边界
            for(int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            right--;

            // 步骤三：从右到左遍历下边界（需要判断是否还有行可遍历）
            if(top <= bottom) {
                for(int i = right; i >= left; i--) {
                    res.add(matrix[bottom][i]);
                }
                bottom--;
            }

            // 步骤四：从下到上遍历左边界（需要判断是否还有列可以遍历）
            if(left <= right) {
                for(int i = bottom; i >= top; i--) {
                    res.add(matrix[i][left]);
                }
                left++;
            }
        }
        System.out.println(res);
//        int[] ans = res.stream().mapToInt(Integer::intValue).toArray();
//        System.out.println(ans);
    }
}
