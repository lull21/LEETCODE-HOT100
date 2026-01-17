package technique;

import java.util.Scanner;

/**
 * @Author 2hang1iang
 * @Date 2026/01/17 10:59
 * @Description 169. 多数元素 【简单】 - 众数
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * 输入：nums = [3,2,3]
 * 输出：3
 * 题解：摩尔投票法 - 票数正负抵消  - 【votes = 0时，假设当前数字为众数】
 * 【每个数字遍历，当前票数为0时，假设当前数字为众数，遍历到下一个数字时，若与当前众数相同，则票数加1，否则减1。】
 * 推论一： 若记 众数 的票数为 +1 ，非众数 的票数为 −1 ，则一定有所有数字的 票数和 >0 。
 * 推论二： 若数组的前a个数字的票数和 = 0 ，则数组剩余 (n-a)个数字的票数和一定仍 >0 ，即后 (n-a) 个数字的 众数仍为 x 。
 * 算法流程：
 * 1. 初始化： 票数统计 votes = 0 ， 众数 x；
 * 2. 循环： 遍历数组 nums 中的每个数字 num ；
 * 3. 当 票数 votes 等于 0 ，则假设当前数字 num 是众数；
 * 4. 当 num = x 时，票数 votes 自增 1 ；当 num != x 时，票数 votes 自减 1 ；
 * 5. 返回值： 返回 x 即可；
 * 后还可以用：
 * 1.哈希表统计每个元素出现的次数，找出出现次数大于 n/2 的元素。
 * 2.数组排序后取中间元素。
 **/

public class majorityElement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int res = 0;
        int votes = 0;
        for (int num : nums) {
            if (votes == 0) {
                res = num;
            }
            votes += num == res ? 1 : -1;
        }
        System.out.println(res);
    }
}
