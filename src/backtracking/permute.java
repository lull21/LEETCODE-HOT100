package backtracking;

import java.util.*;

/**
 * @Author 2hang1iang
 * @Date 2026/01/06 20:24
 * @Description 46.全排列 【中等】
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 题解：
 * 回溯算法的核心在于：尝试 -> 递归 -> 回退。
 * 尝试：选择一个还没用过的数字放入当前路径。
 * 递归：去填下一个位置。
 * 回退（撤销）：这是最关键的一步。当你探索完一种可能后，
 * 需要把刚才选的数字拿出来，并标记为“未使用”，以便在同一层级尝试其他数字。
 * 1.全局变量：一个 res 存储所有结果，一个 path 存储当前正在构建的排列，一个 used 布尔数组记录哪些数字被用过。
 * 2.终止条件：当 path 的长度等于输入数组 nums 的长度时，说明找到了一个全排列。
 * 3.循环与剪枝：遍历 nums，如果当前数字没被用过（used[i] == false），就选中它，继续向下递归。
 **/

public class permute {
    // 用于存放最终所有结果
    static List<List<Integer>> res = new ArrayList<>();
    // 用于存放当前路径（一个可能的排列）
    static List<Integer> path = new ArrayList<>();
    // 记录数字是否被使用过
    static boolean[] used;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        used = new boolean[n];

        // 执行回溯函数
        backtrack(nums);

        System.out.println(res);
    }

    public static void backtrack(int[] nums) {
        // 终止条件
        if(path.size() == nums.length) {
            // 注意：必须 new 一个新 list 存入，否则 res 里的 path 会随着回溯变空
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i = 0; i < nums.length; i++) {
            // 如果这个数字已经在路径里了，跳过
            if(used[i]) continue;

            // 1.做选择
            used[i] = true;
            path.add(nums[i]);

            // 2.递归进入下一层
            backtrack(nums);

            // 3.撤销选择
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}
