package dynamic_plan;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/15 15:44
 * @Description 152. 乘积最大子数组 【中等】
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 测试用例的答案是一个 32-位 整数。
 * 子数组 是数组的连续子序列。
 * 输入：nums = 2 3 -2 4
 * 输出：6
 * 题解：维护一个最大值和最小值，因为负数乘以负数会变成正数，所以需要维护一个最小值
 **/

public class maxProduct {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        // 2. 初始化变量
        // maxF: 维护当前位置结尾的最大乘积
        // minF: 维护当前位置结尾的最小乘积
        // ans: 全局最大值，初始化为数组第一个元素
        int max = nums[0];
        int min = nums[0];
        int res = nums[0];

        // 3. 从第2个元素开始遍历
        for(int i = 1; i < n; i++) {
            int num = nums[i];

            // 【核心技巧】：如果当前数字是负数，最大值和最小值会发生反转
            // 所以我们先交换 max 和 min，这样后面的逻辑就不用写两套了
            if (num < 0) {
                int temp = max;
                max = min;
                min = temp;
            }

            // 4. 更新当前的最大值和最小值
            // 要么是当前数字自己另起炉灶(num)，要么是延续前面的乘积(maxF * num)
            max = Math.max(num, max * num);
            min = Math.min(num, min * num);

            res = Math.max(res, max);
        }
        System.out.println(res);
    }
}
