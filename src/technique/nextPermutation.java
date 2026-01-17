package technique;
import java.util.Scanner;
/**
 * @Author 2hang1iang
 * @Date 2026/01/17 11:42
 * @Description 31. 下一个排列 【中等】
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须 原地 修改，只允许使用额外常数空间。
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * 题解：分四步 + 两个辅助函数（交换和反转）
 * 1.倒序找到升序点i 即nums[i] < nums[i+1]
 * 2.倒序找到第一个比升序点值大的数j 即 nums[i] <nums[j]
 * 3.交换i和j
 * 4.将i+1到n-1的数排序为升序
 **/

public class nextPermutation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        // 1. 从右向左找到第一个升序对(从左往右升序)
        int i = n - 2;
        while(i >= 0 && nums[i] >= nums[i+1]){
            i--;
        }
        // 2. 如果找到了这样的位置i
        if(i >= 0) {
            // 3. 从右向左找到第一个大于nums[i]的元素
            int j = n - 1;
            while(j >= 0 && nums[i] >= nums[j]){
                j--;
            }
            // 4. 交换nums[i]和nums[j]
            swap(nums, i, j);
        }
        // 5. 反转i+1到末尾的部分
        reverse(nums, i + 1);
    }
    // 辅助方法：交换数组中的两个元素
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 辅助方法：反转数组从start到末尾的部分
    private static void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
