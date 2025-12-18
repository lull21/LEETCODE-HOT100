package two_pointers;

import java.util.*;

/**
 * @Author 2hang1iang
 * @Date 2025/12/18 17:15
 * @Description 42.接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 输入：
 * 12
 * 0 1 0 2 1 0 1 3 2 1 2 1
 * 输出：
 * 6
 * 题解：
 *木桶原理
 * 首先，不要去想整个池子怎么接水，而是关注每一列（每一个柱子）上面能存多少水。
 * 对于任何一个位置 i，它上面能存多少水，取决于：
 * 它左边最高的柱子 (leftMax)。
 * 它右边最高的柱子 (rightMax)。
 * 它自己的高度 (height[i])。
 * 水 = min(左边最高，右边最高) - 自己高度
 * 上面计算出每一点的水量，累加即为总存水量
 * 一个while循环即可
 * 需要left right leftMax rightMax
 **/

public class trap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] height = new int[n];
        for(int i = 0; i < n; i++) {
            height[i] = sc.nextInt();
        }
        int left = 0;
        int right = n - 1;
        // 左右两边的最高墙记录
        int leftMax = 0;
        int rightMax = 0;

        int ans = 0; // 总水量

        // 3.开始向中间收缩
        while(left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            // 4. 核心逻辑：谁小算谁，谁小谁动
            // 如果左边的墙比右边的墙矮，那么左边这个位置的水位高度就由左墙决定
            // (因为右边反正有更高的墙挡着，水肯定流不出去)
            if (leftMax < rightMax) {
                ans += leftMax - height[left]; // 累加水量
                left++; // 左指针前进
            } else {
                // 同理，如果右墙矮，右边的水位由右墙决定
                ans += rightMax - height[right]; // 累加水量
                right--; // 右指针后退
            }
        }
        System.out.println(ans);
    }
}
