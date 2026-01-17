package multi_dimension_DP;
import java.util.Scanner;
/**
 * @Author 2hang1iang
 * @Date 2026/01/16 14:25
 * @Description 5. 最长回文子串 【中等】
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 输入：s = "babad"
 * 输出："bab"
 * 题解：
 * 如果 s[i] != s[j]，那么 dp[i][j] = false
 * 如果 s[i] == s[j]，那么有三种情况：
 * 如果 j - i <= 2（即长度 ≤ 3），那么一定是回文
 * 否则，dp[i][j] = dp[i+1][j-1]
 * 第一层for循环，枚举子串长度L
 * 第二层for循环，枚举左边界i
 **/

public class longestPalindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        int len = s.length();
        if (len < 2) {
            System.out.println(s);
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        System.out.println(s.substring(begin, begin + maxLen));
    }
}
