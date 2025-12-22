package array;

import java.util.Scanner;

/**
 * @Author 2hang1iang
 * @Date 2025/12/22 11:47
 * @Description 41.缺失的第一个正数-困难
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 * 输入：
 * 4
 * 3 4 -1 1
 * 输出：
 * 2
 * 题解：“置换法”（也叫“原地哈希”或“数字归位法”）。
 * 1. 核心逻辑：让数字回到它“应有的座位”想象一下，如果你有一个长度为 n 的数组，那么未出现的最小正整数一定在 [1, n+1] 这个区间内。
 * 如果 1 到 n 全都在数组里，那缺失的就是 n+1。如果有数字缺失，那缺失的一定在 1 到 n 之间。
 * 核心思想： 我们尝试把每一个在 [1, n] 范围内的数字 x，送到它应该在的位置——也就是下标为 x-1 的地方。
 * 数字 1 应该放在 nums[0]数字 2 应该放在 nums[1]数字 i 应该放在 nums[i-1]
 * 2. 算法步骤
 * （1）遍历数组（归位过程）：使用 while 循环。
 * 对于当前的数字 nums[i]，如果它在 [1, n] 范围内，且它不在它该在的位置上（即 nums[i] != nums[nums[i]-1]），就把它和目标位置上的数字交换。
 * 重复这个过程，直到当前位置的数字无法再交换为止。
 * （2）二次遍历（查找过程）：归位结束后，再次遍历数组。第一个满足 nums[i] != i + 1 的索引 i，对应的 i+1 就是我们要找的最小正整数。
 * （3）兜底：如果所有位置都符合 nums[i] == i + 1，说明 1 到 n 都在，返回 n + 1。
 **/

public class firstMissingPositive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        // 1. 归位过程
        for(int i = 0; i < n; i++) {
            // 只有当 nums[i] 是正数，且在数组长度范围内，
            // 且它没有待在正确的位置 nums[nums[i]-1] 时，才进行交换
            while(nums[i] > 0 && nums[i] <= n && nums[nums[i]-1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        // 2.寻找第一个不在位上的数字
        for(int i = 0; i < n; i++) {
            if(nums[i] != i + 1) {
                System.out.println(i+1);
                return;
            }
        }

        // 3. 如果都在位，返回 n+1
        System.out.println(n+1);
    }
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
