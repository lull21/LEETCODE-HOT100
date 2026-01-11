package binary_search;
import java.util.*;

/**
 * @Author 2hang1iang
 * @Date 2026/01/09 13:54
 * @Description 153. 寻找旋转排序数组中的最小值 【中等】
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * 例如，数组 nums = [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] 。
 * 请找出其中最小的元素。
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 * 输入：nums = [3,4,5,1,2]
 * 输出：1
 * 题解：旋转后的数组具有一个重要特性：数组被分成两个有序的部分。
 * 在旋转排序数组中，最小值一定在旋转点的右侧。
情况1：nums[mid] <= nums[right]
  说明 mid 在右半部分（包含最小值）
  最小值可能在 mid 的左边或就是 mid 本身
  所以将 right 移动到 mid，继续在左半边搜索
情况2：nums[mid] > nums[right]
  说明 mid 在左半部分（不包含最小值）
  最小值一定在 mid 的右边
  所以将 left 移动到 mid + 1，在右半边搜索
 **/

public class findMin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int left = 0, right = n - 1;
        while (left < right) { // 不能使用 left <= right，因为这样会导致死循环
            int mid = left + (right - left) / 2;
            if (nums[mid] <= nums[right]) { // 说明 mid 在左半部分
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(nums[left]);
    }

    private int findMax(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= nums[left]) { // 说明 mid 在左半部分
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return nums[left];
    }
}
