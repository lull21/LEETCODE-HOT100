package greed;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/12 21:00
 * @Description 45. 跳跃游戏 II 【中等】
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个下标。
 * 假设你总是可以到达数组的最后一个下标。
 * 输入：nums = [2,3,1,1,4]
 * 输出：2
 * 题解：两个覆盖下标，curCoverIdx记录当前覆盖下标，nextCoverIdx记录下一个覆盖下标
 * 遍历终止条件为n-1，因为最后一个下标不需要跳跃
 * 每次更新nextCoverIdx
 * 每次检查 i == curCoverIdx
 **/

public class jump {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int curCoverIdx = 0, nextCoverIdx = 0;
        int jump = 0;
        for(int i = 0; i < n - 1; i++) {
            nextCoverIdx = Math.max(nextCoverIdx, nums[i] + i);
            if(i == curCoverIdx) {
                jump++;
                curCoverIdx = nextCoverIdx;
            }
        }
        System.out.println(jump);
    }
}
