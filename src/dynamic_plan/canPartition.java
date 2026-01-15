package dynamic_plan;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/15 16:33
 * @Description 416. 分割等和子集 【中等】
 * 给你一个 【只包含正整数】 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 输入：nums = 1 5 11 5
 * 输出：true
 * 题解：
 * 这是一个典型的 【0-1 背包问题】。我们可以将问题转换为：是否存在一个子集，其元素和等于数组总和的一半。
 * 我们可以使用动态规划来解决这个问题。从【后往前遍历】
 * 我们定义一个一维数组 dp，其中 dp[j] 表示能够凑出和为 j。
 * 我们可以根据当前元素是否选择来更新 dp 数组。
 * 最后，dp[target] 就是我们的答案。
 **/

public class canPartition {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        // 1. 计算总和
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // 2. 如果总和是奇数，直接返回 false
        if (sum % 2 != 0) {
            System.out.println("false");
        }

        // 3. 设定背包的目标容量
        int target = sum / 2;

        // 4. 初始化 DP 数组
        // dp[j] 表示：能否凑出和为 j 的子集
        // 长度为 target + 1，因为要包含 0 到 target
        boolean[] dp = new boolean[target + 1];

        // 基础条件：凑出和为 0 是永远可以的（什么都不选）
        dp[0] = true;

        // 5. 开始遍历每一个物品（数字）
        for (int num : nums) {
            // 【核心技巧】：从后往前遍历
            // 必须从 target 遍历到 num，防止同一个数字被重复使用
            for (int j = target; j >= num; j--) {
                // 状态转移方程：
                // dp[j] 为 true 的条件是：
                // 1. 不选当前 num，本来就已经能凑出 j (即 dp[j] 为 true)
                // 2. 选当前 num，那就要看之前能否凑出 j - num (即 dp[j - num] 为 true)
                dp[j] = dp[j] || dp[j - num];
            }

            // 剪枝：如果在中间过程中已经凑出了 target，可以提前返回（可选优化）
            if (dp[target]) {
                System.out.println("true");
            }
        }

        System.out.println(dp[target]);
    }
}
