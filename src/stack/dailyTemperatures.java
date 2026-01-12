package stack;

import java.util.*;

/**
 * @Author 2hang1iang
 * @Date 2026/01/11 15:58
 * @Description 739. 每日温度 【中等】
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指在第 i 天之后，
 * 才会有更高的温度。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * 输入: temperatures = 8 [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * 题解：【单调递减栈】 每次都要把栈里面比自己小的都要逼出来【弹出】
 * 1.初始化：创建一个空栈 stack，用于存储温度的【索引】。
 * 2.遍历温度数组：(for循环里面while循环)
 *  2.1 如果栈为空，将当前索引 i 入栈。
 *  2.2 如果栈不为空，比较当前温度 temperatures[i] 与栈顶索引对应的温度 temperatures[stack.peek()]。
 *  如果当前温度较高，弹出栈顶索引，计算当前索引与弹出索引的差值，将差值存储到结果数组 answer[弹出索引] 中。
 *  重复此步骤，直到栈为空或当前温度不高于栈顶索引对应的温度。
 *  最后，将当前索引 i 入栈。
 * 3.返回结果数组 answer。
 **/


public class dailyTemperatures {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] temperatures = new int[n];
        for (int i = 0; i < n; i++) {
            temperatures[i] = sc.nextInt();
        }
        int[] res = new int[n];
        Deque<Integer> idxStack = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            // 当栈不为空且当前温度大于栈顶索引对应的温度时
            // 当前是高温度，就要把还在栈内的低温度都要逼出来【弹出】
            while(!idxStack.isEmpty() && temperatures[i] > temperatures[idxStack.peek()]) {
                int idx = idxStack.pop();
                res[idx] = i - idx;
            }
            idxStack.push(i);
        }
        System.out.println(Arrays.toString(res));
    }
}
