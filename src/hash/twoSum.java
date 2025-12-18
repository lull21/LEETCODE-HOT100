package hash;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 2hang1iang
 * @Date 2025/12/16 20:17
 * @Description 1. 两数之和
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 输入：
 * 4
 * 2 7 11 15
 * 9
 * 输出：
 * 0 1
 * 题解：
 * 用HashMap,containsKey()函数；
 **/

public class twoSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int target = sc.nextInt();
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            if(map.containsKey(target - nums[i])) {
                System.out.println(map.get(target - nums[i]) + " " + i);
            }
            map.put(nums[i], i);
        }
    }
}