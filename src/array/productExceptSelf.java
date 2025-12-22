package array;

import java.util.Scanner;

/**
 * @Author 2hang1iang
 * @Date 2025/12/22 11:17
 * @Description 238.除自身以外数组的乘积-中等
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * 输入：
 * 4
 * 1 2 3 4
 * 输出：
 * 24 12 8 6
 * 题解：左右乘积列表法,两次遍历，先正后逆
 *  1. 核心逻辑：前缀积 * 后缀积
 *  对于数组中任意位置 i 的元素，除它之外的乘积可以拆解为两部分：
 *  左侧所有元素的乘积（称为“前缀积”）右侧所有元素的乘积（称为“后缀积”）
 *  2. 算法步骤为了达到空间复杂度 O(1)（输出数组不计入空间），我们可以分两步走：
 *  第一遍遍历（计算前缀积）：利用输出数组 answer 存储每个元素左侧的所有元素乘积。
 *  第二遍遍历（计算后缀积并求结果）：从右向左遍历，用一个变量实时动态维护“右侧元素的积”，并将其与 answer 中已有的前缀积相乘。
 **/

public class productExceptSelf {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int[] answer = new int[n];

        // 第一步：计算每个元素左侧的乘积
        //  answer[i] 表示 nums[0] 到 nums[i-1] 的乘积
        answer[0] = 1;
        for(int i = 1; i < n; i++) {
            answer[i] = answer[i-1] * nums[i-1];
        }

        // 第二步：计算每个元素右侧的乘积，并直接乘入 answer
        // R 变量用来实时维护当前元素右侧所有元素的乘积
        int R = 1;
        for(int i = n - 1; i >= 0; i--) {
            // 此时 answer[i] 已经是左积，乘上右积 R 即为最终结果
            // 【关键】
            answer[i] = answer[i] * R;
            // 更新 R，为下一个位置（左边那个）做准备
            R = R * nums[i];
        }

        for(int i = 0; i < n; i++) {
            System.out.print(answer[i] + " ");
        }

    }
}
