package stack;

import java.util.*;

/**
 * @Author 2hang1iang
 * @Date 2026/01/11 17:01
 * @Description 84. 柱状图中最大的矩形 【困难】
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 题解:【单调递增栈（栈底到栈顶元素单调递增）】 + 虚拟柱子（最后一个柱子高度为0，保证所有柱子都能被弹出处理）
 * 【找到每个柱子离他最近的比它小的柱子的索引】
 * while循环中是在找栈顶元素的左边界，栈顶元素的右边界就是当前for遍历的元素的索引
 * 即找到【左边第一个比它小的柱子和右边第一个比它小的柱子的索引】。
 * 然后计算以该柱子为高的矩形面积，取最大值即可。
 **/

public class largestRectangleArea {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] heights = new int[n];
        for(int i = 0; i < n; i++) {
            heights[i] = sc.nextInt();
        }
        Deque<Integer> idxStack = new ArrayDeque<>();
        int maxArea = 0;

        for(int i = 0; i <= n; i++) {
            // 当前柱子的高度：如果是最后一个（虚拟柱子），高度为0
            int currentHeight  = (i == n ? 0 : heights[i]);

            // 当栈不为空且当前柱子高度小于栈顶柱子高度时
            while(!idxStack.isEmpty() && currentHeight < heights[idxStack.peek()]) {
                int height = heights[idxStack.pop()]; // 要计算的是这个栈顶的元素，将其弹出
                // 计算宽度
                // 如果栈为空，说明弹出的柱子是到目前遇到的最小的，宽度就是i
                // 否则宽度 = i - 新栈顶 - 1
                // 新栈顶是当前高度的左边界
                int width = idxStack.isEmpty() ? i : i - idxStack.peek() - 1; // idxStack.peek()此时是新的栈顶
                maxArea = Math.max(maxArea, height * width);
            }
            idxStack.push(i);
        }
        System.out.println(maxArea);
        sc.close();
    }
}
