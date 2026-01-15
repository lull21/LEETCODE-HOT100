package dynamic_plan;

import java.util.*;

/**
 * @Author 2hang1iang
 * @Date 2026/01/14 14:09
 * @Description 300.最长递增子序列 【中等】
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * 输入：nums = 8 10 9 2 5 3 7 101 18
 * 输出：4
 * 题解：子序列的问题都是 for循环中 if判断，因为是非连续的，要两层循环，连续的一层for即可
 **/

public class lengthOfLIS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int res = 1;

        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }

        System.out.println(res);

    }
}
