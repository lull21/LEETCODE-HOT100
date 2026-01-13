package greed;

import java.util.Scanner;

/**
 * @Author 2hang1iang
 * @Date 2026/01/12 20:59
 * @Description 55. 跳跃游戏 【中等】
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 题解；
 * 1. 遍历数组，更新记录最大覆盖范围
 * 每次更新coverRage
 * 每次检查，coverage一直记录的是从【0开始，能覆盖的最大下标】.而不是从当前位置开始能覆盖的最大下标
 **/

public class canJump {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int coverRage = 0; //  不是覆盖范围，是最远覆盖下标
        for (int i = 0; i <= coverRage; i++) {
            coverRage = Math.max(coverRage, i + nums[i]);
            if(coverRage >= n - 1) { // 与>n的区别是，>=n-1时，说明能覆盖到最后一个下标
                System.out.println(true);
                return;
            }
        }
        System.out.println(false);
    }
}
