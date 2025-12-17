package hash;

import java.util.*;

/**
 * @Author 2hang1iang
 * @Date 2025/12/17 11:29
 * @Description 128. 最长连续序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * 输入 ：
 * 6
 * 100 4 200 1 3 2
 * 输出：
 * 4
 **/

public class longestConsecutive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);;
        int n = sc.nextInt();
        int[] nums = new int[n];
        // 无序性，唯一性，线程不安全，允许null值
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
            set.add(nums[i]);
        }
        int res = 0;
        for(int num : set) { // 注意被遍历对象
            // 一旦当前数不再连续，就while遍历出从当前数开始连续的序列长度，并更新最长序列长度
            if(!set.contains(num - 1)) {
                int count = 1;
                int curNum = num;
                while(set.contains(curNum + 1)) {
                    count++;
                    curNum++;
                }
                res = Math.max(res, count);
            }
        }
        System.out.println(res);
    }
}