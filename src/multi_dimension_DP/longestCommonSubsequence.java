package multi_dimension_DP;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/16 15:21
 * @Description 1143. 最长公共子序列 【中等】
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 题解：dp数组多一位
 * 定义 dp[i][j] 表示 text1 的前 i 个字符和 text2 的前 j 个字符的最长公共子序列的长度。
 * 状态转移方程：
 * 如果 text1[i-1] == text2[j-1]，那么 dp[i][j] = dp[i-1][j-1] + 1
 * 如果 text1[i-1] != text2[j-1]，那么 dp[i][j] = max(dp[i-1][j], dp[i][j-1]) 【如果要求连续，则不需要这一步判断】
 **/

public class longestCommonSubsequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text1 = sc.nextLine();
        String text2 = sc.nextLine();

        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        System.out.println(dp[len1][len2]);
    }
}
