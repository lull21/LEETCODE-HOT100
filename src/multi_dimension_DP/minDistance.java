package multi_dimension_DP;
import java.util.Scanner;
/**
 * @Author 2hang1iang
 * @Date 2026/01/17 10:13
 * @Description 72. 编辑距离 【中等】
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 题解：dp[i][j] 表示将 word1 的前 i 个字符转换为 word2 的前 j 个字符所需的最小操作数
 * 状态转移方程：
 * 如果 word1[i-1] == word2[j-1]，那么 dp[i][j] = dp[i-1][j-1]
 * 如果 word1[i-1] != word2[j-1]，那么 dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
 * 其中 dp[i-1][j-1] 表示替换操作，dp[i-1][j] 表示删除操作，dp[i][j-1] 表示插入操作
 * 初始化：dp[0][j] = j -- 将空字符串转换为 word2 的前 j 个字符需要 j 次插入操作
 *       dp[i][0] = i -- 将 word1 的前 i 个字符转换为空字符串需要 i 次删除操作
 *
 **/

public class minDistance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word1 = sc.nextLine();
        String word2 = sc.nextLine();

        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        // 初始化
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }
        // 状态转移
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
                }
            }
        }
        System.out.println(dp[len1][len2]);
    }
}
