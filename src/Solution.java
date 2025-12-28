import java.util.Scanner;

/**
 * @Author 2hang1iang
 * @Date 2025/12/26 14:58
 * @Description
 **/

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int res = bestClosingTime(str);
        System.out.println(res);
    }
    public static int bestClosingTime(String customers) {
        int maxNum = Integer.MAX_VALUE;
        int cost = 0;
        int res = 0;
        int n = customers.length();
        char[] cus = customers.toCharArray();
        // dp[i][0] 表示 当前时间没有闭店的最小代价
        // dp[i][1] 表示 当前时间闭店的最小代价
        int[][] dp = new int[n][2];
        dp[0][0] = cus[0] == 'Y' ? 0 : 1; // 开店有顾客来为0，没顾客来为1
        dp[0][1] = cus[0] == 'Y' ? 1 : 0;//
        for(int i = 1; i < n; i++) {
            if(cus[i] == 'Y') { // 当前时间有顾客来
                dp[i][0] = dp[i-1][0]; // 开店
                dp[i][1] = Math.min(dp[i-1][0] + 1, dp[i-1][1] + 1); // 闭店
            } else { // 当前时间没有顾客来
                dp[i][0] = dp[i-1][0] + 1;
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][1]);
            }
            cost = Math.max(dp[i][0], dp[i][1]);
            if(cost < maxNum) {
                maxNum = cost;
                res = i;
            }
            System.out.println(cost + " " + res);
        }
        return res;
    }
}
