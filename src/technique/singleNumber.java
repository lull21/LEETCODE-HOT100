package technique;
import java.util.Scanner;

/**
 * @Author 2hang1iang
 * @Date 2026/01/17 10:48
 * @Description 136.只出现一次的数字 【简单】 异或
 * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 * 输入：nums = [2,2,1]
 * 输出：1
 * 题解：位运算 异或运算 数组中的全部元素的异或运算结果即为数组中只出现一次的数字。
 * 异或运算的性质：
 * 1. 任何数和 0 做异或运算，结果仍然是原来的数，即 a ^ 0 = a。
 * 2. 任何数和其自身做异或运算，结果是 0，即 a ^ a = 0。
 * 3. 异或运算满足交换律和结合律，即 a ^ b ^ a = b ^ a ^ a = b ^ (a ^ a) = b ^ 0 = b。
 **/

public class singleNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        System.out.println(res);
    }
}
