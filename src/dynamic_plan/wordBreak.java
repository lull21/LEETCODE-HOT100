package dynamic_plan;

import java.util.*;

/**
 * @Author 2hang1iang
 * @Date 2026/01/14 13:39
 * @Description 139.单词拆分 【中等】
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 题解：用到HashSet来存储单词字典，然后用dp[i]表示字符串s的前i个字符是否可以被单词字典拼接出来。
 * dp[i] = true => dp[j] ==true && set.contains(s.substring(j,i))
 **/

public class wordBreak {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int n = sc.nextInt();
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(sc.next());
        }

        int len = s.length();
        boolean[] dp = new boolean[len+1];
        for(int i = 1; i <= len; i++) {
            dp[i] = false;
        }
        dp[0] = true;
        for(int i = 1; i <= len; i++) {
            for(int j = 0; j < i && !dp[i]; j++) {
                if(set.contains(s.substring(j, i)) && dp[j]) {
                    dp[i] = true;
                }
            }
        }
        System.out.println(dp[len]);
    }
}
