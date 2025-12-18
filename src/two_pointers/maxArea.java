package two_pointers;

import java.util.*;

/**
 * @Author 2hang1iang
 * @Date 2025/12/18 15:04
 * @Description 11.盛最多水的容器
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 * 输入：
 * 9
 * 1 8 6 2 5 4 8 3 7
 * 输出：
 * 49
 * 题解：
 * 左右指针，左右逼近。while循环，哪边高度低，哪边朝中间移动。更新面积最大值。
 **/

public class maxArea {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] height = new int[n];
        for(int i = 0; i < n; i++) {
            height[i] = sc.nextInt();
        }
        int res  = 0, area = 0;
        int leftIdx = 0, rightIdx = n - 1;
        while(leftIdx < rightIdx) {
            int high = Math.min(height[leftIdx], height[rightIdx]);
            area = high * (rightIdx - leftIdx);
            if(height[leftIdx] > height[rightIdx]) {
                rightIdx--;
            } else {
                leftIdx++;
            }
            res = Math.max(area, res);
        }
        System.out.println(res);
    }
}
