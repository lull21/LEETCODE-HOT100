package binary_search;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/09 13:20
 * @Description 33. 搜索旋转排序数组 【中等】
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 题解：
 * 这道题是二分查找的变体，因为数组被旋转了，所以我们不能直接使用普通的二分查找。
 * 但是我们可以根据数组的性质来判断目标值在左半部分还是右半部分，从而确定二分查找的方向。
 * 具体来说，我们可以比较 nums[mid] 和 nums[left] 的大小关系，来判断 mid 是在左半部分还是右半部分。
 * 如果 nums[left] <= nums[mid]，那么 mid 是在左半部分，否则 mid 是在右半部分。
 * 然后我们根据目标值 target 和 nums[mid] 的大小关系，来判断目标值在左半部分还是右半部分。
 * 如果 nums[left] <= target && target < nums[mid]，那么目标值在左半部分，否则目标值在右半部分。
 * 最后我们根据二分查找的规则，来更新 left 和 right 的值，直到找到目标值或者确定目标值不存在。
 **/

public class search {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int target = sc.nextInt();
        System.out.println(searchfun(nums, target));
    }
    public static int searchfun(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            }
            // 左半部分有序
            if(nums[left] <= nums[mid]) {
                // target 在左半部分
                if(nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // target 在右半部分
                if(nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
