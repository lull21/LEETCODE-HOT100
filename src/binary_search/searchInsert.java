package binary_search;

import java.util.Scanner;

/**
 * @Author 2hang1iang
 * @Date 2026/01/09 10:44
 * @Description 35. 搜索插入位置 [简单]
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 题解：
 * 这是一个简单的二分查找问题。我们可以使用二分查找来找到目标值的插入位置。
 * 具体来说，我们维护两个指针 left 和 right，分别指向数组的第一个元素和最后一个元素。
 * 然后，我们计算中间位置 mid = (left + right) / 2。
 * 如果 nums[mid] 等于目标值，我们直接返回 mid。
 * 如果 nums[mid] 大于目标值，我们将 right 指针移动到 mid - 1。
 * 如果 nums[mid] 小于目标值，我们将 left 指针移动到 mid + 1。
 * 当 left 大于 right 时，我们返回 left 作为目标值的插入位置。
 **/

public class searchInsert {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int target = sc.nextInt();

        if(target > nums[n-1]){
            System.out.println(n);
        }
        if(target < nums[0]){
            System.out.println(0);
        }
        int left =0, right = n -1;
        while(left <= right) {
            int mid = left + ((right - left) >> 1);
            if(nums[mid] == target) {
                System.out.println(mid);
                return;
            } else if(target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(left);
    }
}
