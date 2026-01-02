package binary_tree;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/01 09:40
 * @Description 437. 路径总和 III 【中等】
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，
 * 求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，
 * 但是路径方向必须是向下的（只能从父节点到子节点）。
 * 输入：root = 10 5 -3 3 2 null 11 3 -2 null 1 targetSum = 8
 * 输出：3
 * 题解：二叉树遍历与数组前缀和
 * 在树中，从根节点到当前节点 curr 的路径上，所有节点值之和即为该节点的前缀和。
 * 假设从根节点到某个祖先节点 A 的前缀和为 S_1，到当前节点 B 的前缀和为 S_2。
 * 如果 S_2 - S_1 = targetSum，那么从节点 A 的子节点到节点 B 的这段路径和，正好就是 targetSum！
 * 1.哈希表记录：使用一个 HashMap<Long, Integer>，键（Key）是前缀和，值（Value）是该前缀和出现的次数。
 * 2.递归与回溯：
 *   进入节点时：计算当前前缀和，查看 map 中是否存在 currSum - targetSum。如果有，说明找到了对应数量的路径。
 *   更新 map：将当前前缀和存入 map。
 *   递归：继续向左、右子树搜索。
 *   回溯（重要）：退出当前节点前，将当前前缀和在 map 中的计数减 1。这是为了防止“左子树的前缀和”干扰到“右子树”的计算（路径必须是向下的，不跨越左右子树）。
 **/

public class pathSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] parts = sc.nextLine().split("\\s+");
        int targetSum = sc.nextInt();
        TreeNode root = buildTree(parts);

        System.out.println(sum(root, targetSum));
    }

    public static int sum(TreeNode root, int targetSum) {
        // 使用 Long 防止溢出
        Map<Long, Integer> prefixSumMap = new HashMap<>();
        // 初始化：前缀和为 0 的路径默认有 1 条（代表从根节点开始的路径）
        prefixSumMap.put(0L, 1);
        return dfs(root, 0L, targetSum, prefixSumMap);
    }

    private static int dfs(TreeNode node, long currSum, int targetSum, Map<Long, Integer> map) {
        if (node == null) return 0;

        int res = 0;
        currSum += node.val;

        // 1. 核心判断：寻找是否存在一个祖先节点的前缀和，满足当前和减去它等于 targetSum
        res += map.getOrDefault(currSum - targetSum, 0);

        // 2. 将当前前缀和存入 map，供子节点使用
        map.put(currSum, map.getOrDefault(currSum, 0) + 1);

        // 3. 递归左右子树
        res += dfs(node.left, currSum, targetSum, map);
        res += dfs(node.right, currSum, targetSum, map);

        // 4. 回溯：移除当前路径的前缀和，避免影响其他分支
        map.put(currSum, map.get(currSum) - 1);

        return res;
    }

    public static TreeNode buildTree(String[] parts) {
        if(parts.length == 0 || "null".equals(parts[0])) return null;

        TreeNode root = new TreeNode(Integer.parseInt(parts[0]));
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        int i = 1;

        while(!queue.isEmpty() && i < parts.length) {
            TreeNode node = queue.poll();
            if(i < parts.length && !"null".equals(parts[i])) {
                node.left = new TreeNode(Integer.parseInt(parts[i]));
                queue.add(node.left);
            }
            i++;

            if(i < parts.length && !"null".equals(parts[i])) {
                node.right = new TreeNode(Integer.parseInt(parts[i]));
                queue.add(node.right);
            }
            i++;
        }

        return root;
    }

}
