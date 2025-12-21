package substring;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2025/12/20 15:39
 * @Description 560.和为K的子数组-中等
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * 子数组是数组中元素的连续非空序列。
 * 输入：
 * 3
 * 1 1 1
 * 2
 * 输出：
 * 2
 * 题解:
 * 暴力方法：两层for循环，第一次正序遍历，第二层从当前数往前遍历，并累加比较结果
 * 前缀和 + 哈希表
 * 1.前缀和 preSum[i] 表示从数组开头到第i个元素的累加和。
 *   关键公式：任意子数组[j, i]的和=preSum[i]-preSum[j-1]
 * 2.逻辑转化我们要找的是：preSum[i] - preSum[j-1] == k。
 *   也就是在每一个位置 i，去寻找：之前是否存在一个位置 j-1，其前缀和等于 preSum[i] - k
 * 3.引入哈希表（HashMap）为了不用每次都往前遍历找这个值，我们用一个 HashMap 来记录：
 *   Key：前缀和的值。
 *   Value：该前缀和出现的次数。
 **/

public class subarraySum {
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        // 1. map用于存储：key=前缀和, value=该前缀和出现的次数
        HashMap<Integer, Integer> map = new HashMap<>();
        // 2. 特别注意：初始化！前缀和为0出现过1次（处理子数组刚好从下标0开始的情况）
        map.put(0,1);

        int count = 0; // 结果计数器
        int preSum = 0; // 前缀和计数器

        for(int num : nums) {
            preSum += num; // 更新当前前缀和

            // 3. 核心逻辑：如果 preSum - k 在 map 中，说明找到了满足条件的子数组
            if(map.containsKey(preSum - k)) {
                count += map.get(preSum - k);
            }

            // 4. 将当前的前缀和存入 map
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }

        System.out.println(count);

    }

}
