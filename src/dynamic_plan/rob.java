package dynamic_plan;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/14 11:10
 * @Description 198. 打家劫舍 【中等】
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * 输入：[1,2,3,1]
 * 输出：4
 * 题解：
 * 1. 定义一个数组 dp，dp[i] 表示偷窃到第 i 个房屋时能够偷窃到的最高金额。
 * 2. 初始化 dp[0] = nums[0]，dp[1] = max(nums[0], nums[1])。
 * 3. 对于每个房屋 i，有两种情况：
 *    a. 偷窃第 i 个房屋，那么第 i-1 个房屋不能偷窃，dp[i] = dp[i-2] + nums[i]。
 *    b. 不偷窃第 i 个房屋，那么 dp[i] = dp[i-1]。
 * 4. 最终结果为 dp[n-1]，其中 n 是房屋的数量。
 **/

public class rob {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int[] dp = new int[n];
        if(n == 0) {
            System.out.println(nums[0]);
            return;
        }
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for(int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }
        System.out.println(dp[n-1]);
    }
    public int fun2(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0; // 0为不偷
        dp[0][1] = nums[0];// 1为偷

        for(int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1] = dp[i-1][0] + nums[i];
        }

        return Math.max(dp[n-1][0], dp[n-1][1]);
    }
}
