package two_pointers;

import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2025/12/18 16:10
 * @Description 15.三数之和
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 输入：
 * 6
 * -1 0 1 2 -1 -4
 * 输出：
 * -1 -1 2
 * -1 0 1
 * 题解：
 * 排序+双指针
 * 定一：遍历数组，固定第一个数 nums[i]。
 * 移二：对于剩下的两个数，用双指针 L (左) 和 R (右) 在 nums[i] 后面的区间里寻找，使得 nums[L] + nums[R] == -nums[i]。
 * 去重：这是最容易出错的地方，必须在遍历 i 和移动 L、R 时跳过重复的数字。
 * for循环里套while循环
 * 这道题有 三个 需要去重的地方，请务必背下来：
 * (1)外层循环去重：if (i > 0 && nums[i] == nums[i-1]) continue; （如果这轮用的数字和上一轮一样，结果肯定重复，跳过）。
 * (2)左指针去重：找到答案后，while (nums[L] == nums[L+1]) L++;
 * (3)右指针去重：找到答案后，while (nums[R] == nums[R-1]) R--;
 **/

public class threeSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < nums.length; i++) {
            nums[i] = sc.nextInt();
        }
        Arrays.sort(nums);
        // 2. 遍历：固定第一个数 nums[i]
        for(int i = 0; i < n; i++) {
            // 剪枝：如果第一个数已经大于0，后面不可能凑出0了（因为数组已排序）
            if(nums[i] > 0) return;
            // 去重A：如果当前数和前一个数一样，跳过（避免出现重复的三元组）
            if(i > 0 && nums[i] == nums[i-1]) continue;

            int L = i + 1;
            int R = nums.length - 1;

            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0) {
                    System.out.println(nums[i] + " " + nums[L] + " " + nums[R]);
                    // 去重B：L往后移时，如果遇到相同的数，一直跳过
                    while(L < R && nums[L] == nums[L+1]) L++;
                    // 去重C：R往前移时，如果遇到相同的数，一直跳过
                    while(L < R && nums[R] == nums[R-1]) R--;
                    L++;
                    R--;
                } else if(sum < 0) {
                    L++;
                } else {
                    R--;
                }
            }
        }
    }
}
