package greed;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/12 20:59
 * @Description 121. 买卖股票的最佳时机 【简单】 【只能买卖一次】
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * 输入：prices = [7,1,5,3,6,4]
 * 输出：5
 * 题解：
 * 遍历数组，
 * 每次更新最大利润，
 * 每次更新最小价格
 **/

public class maxProfit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextInt();
        }
        int profit = 0;
        int minPrice = prices[0];
        for (int i = 1; i < n; i++) {
            profit = Math.max(profit, prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }
        System.out.println(profit);
    }
}
