package array;

import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2025/12/21 16:56
 * @Description 53.最大子数组和-中等
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 输入：
 * 9
 * -2 1 -3 4 -1 2 1 -5 4
 * 输出：
 * 6
 * 题解：
 * 动态规划
 *
 **/

public class maxSubArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        int res = nums[0];
        for(int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            res = Math.max(res, dp[i]);
        }
        System.out.println(res);
    }

    /**
     * 采用一个数计数，降低空间复杂度
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        // 1. 初始化：maxSum 记录全局最大和，preSum 记录当前子数组的累加和
        // 注意：maxSum 不能初始化为 0，因为数组可能全是负数
        int maxSum = nums[0];
        int preSum = 0;

        for (int num : nums) {
            // 2. 核心逻辑：如果前面的累加和大于 0，则继续累加；否则从当前数重新开始
            // 相当于 preSum = Math.max(num, preSum + num);
            if (preSum > 0) {
                preSum += num;
            } else {
                preSum = num;
            }

            // 3. 实时更新全局最大值
            maxSum = Math.max(maxSum, preSum);
        }

        return maxSum;
    }
}
