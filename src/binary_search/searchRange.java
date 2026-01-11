package binary_search;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/09 11:30
 * @Description 34. 在排序数组中查找元素的第一个和最后一个位置 [中等]
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 题解：
 * 1. 二分查找找到目标值的左边界
 * 2. 二分查找找到目标值的右边界
 **/

public class searchRange {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int target = sc.nextInt();

        int leftEdge = findEdge(nums, target);
        if(leftEdge == nums.length || nums[leftEdge] != target) {
            System.out.println(new int[] {-1, -1});
            return;
        }
        // 右边界要减一，因为 findEdge函数找到的是大于等于 target 的第一个位置
        int rightEdge = findEdge(nums, target + 1) - 1;
        System.out.println(new int[] {leftEdge, rightEdge});
    }

    // 与传统二分不同的是，这里是找到大于等于 target 的【第一个位置】
    // 而不是找到等于 target 的位置
    // 当 nums[mid] >= target 时，right 会被赋值为 mid - 1，
    // 这是因为我们要找到的是目标值的左边界，而不是等于目标值的位置
    private static int findEdge(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] >= target) { // 这里要注意 是 >= 不是 >
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
