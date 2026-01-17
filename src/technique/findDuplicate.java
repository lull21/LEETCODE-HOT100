package technique;

import java.util.Scanner;

/**
 * @Author 2hang1iang
 * @Date 2026/01/17 12:17
 * @Description 287.寻找重复数 【中等】
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），
 * 可知至少存在一个重复的整数。
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 * 输入：nums = [3,1,3,4,2]
 * 输出：3
 * 题解：快慢指针-转换为链表成环问题，找到环的入口节点
 **/

public class findDuplicate {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int slow = 0;
        int fast = 0;
        slow = nums[slow];
        fast = nums[nums[fast]];
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        int pre1 = 0;
        int pre2 = slow;
        while(pre1 != pre2) {
            pre1 = nums[pre1];
            pre2 = nums[pre2];
        }
        int res = pre1;

        System.out.println(res);
    }
}
