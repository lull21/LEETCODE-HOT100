package array;

import java.util.Scanner;

/**
 * @Author 2hang1iang
 * @Date 2025/12/21 16:59
 * @Description 189.轮转数组-中等
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * 输入：
 * 7
 * 1 2 3 4 5 6 7
 * 3
 * 输出：
 * 5 6 7 1 2 3 4
 * 题解：三次反转法
 * 反转整个数组：[7, 6, 5, 4, 3, 2, 1] （这时原来的后半部分跑到了前面，但顺序是反的）
 * 反转前k个元素：[5, 6, 7, 4, 3, 2, 1] （前 3 位顺序正过来了）
 * 反转剩余的元素：[5, 6, 7, 1, 2, 3, 4] （后 4 位顺序也正过来了，大功告成！）
 **/

public class rotate {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int k = sc.nextInt();

        k %= n;

        reverse(nums, 0, n -1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);

        for(int i = 0; i < n; i++) {
            System.out.print(nums[i] + " ");
        }
    }
    private static void reverse(int[] nums, int start, int end) {
        while(start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
