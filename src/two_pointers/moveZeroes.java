package two_pointers;

import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2025/12/17 15:30
 * @Description 282.移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * 输入：
 * 5
 * 0 1 0 3 12
 * 输出：
 * 1 3 12 0 0
 * 题解：
 * 本题侧重处理非零元素，将非零元素全部移动到数组的前端，保持非零元素的相对顺序，零元素自动移动到数组的末尾。
 * 快慢指针追赶，快指针不为零时交换快慢。for循环。
 * 慢指针始终指向下一个用来存放非零元素的位置；它维护的是“非零序列”的尾部。
 *      slow 左边（0 到 slow-1）全都是处理好的非零元素。
 *      slow 当前指向的位置，是等待 fast 找到新的非零元素后扔进来的地方。
 * 快指针遍历数组，遇到非零元素就交换快慢指针指向的元素，同时慢指针后移。
 **/

public class moveZeroes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        // 快慢指针
        // 慢指针指向
        // 快指针指向当前遍历到的元素
        int slowIdx = 0, fastIdx = 0;
        for( ; fastIdx < n; fastIdx++) {
            if(nums[fastIdx] != 0) {
                int tmp = nums[fastIdx];
                nums[fastIdx] = nums[slowIdx];
                nums[slowIdx] = tmp;
                slowIdx++;
            }
        }
        for(int i = 0; i < n - 1; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.print(nums[n-1]);
    }
}
