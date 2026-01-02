package binary_tree;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/02 10:44
 * @Description 124. 二叉树中的最大路径和 【困难】
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 * 输入：root = -10 9 20 null null 15 7
 * 输出：42
 * 题解：递归与动态维护
 * (1) 对于任意一个节点 root，经过它的最大路径和由三部分组成：
 *  节点本身的值。
 *  左子树提供的最大单向路径和（如果大于0）。
 *  右子树提供的最大单向路径和（如果大于0）。
 * (2) 递归函数的定义（关键）
 * 我们需要一个递归函数 maxGain(node)，它的作用是：返回该节点向下的“单边”最大路径和
 * 为什么是单边？因为如果要连接到父节点，路径只能选择左边或者右边，不能两边都选（否则就不是一条合规的路径了）。
 * 计算公式：currentGain = node.val + max(maxGain(left), maxGain(right))。
 * 注意：如果子树返回的值是负数，我们宁愿不选这棵子树，所以要和 0 取最大值。
 * (3) 更新全局最大值
 * 在递归计算 maxGain 的过程中，我们顺便计算以当前节点为转折点的路径和：
 * price_newpath = node.val + leftGain + rightGain 用这个值去更新全局变量 maxSum。
 **/

public class maxPathSum {
    // 全局变量，记录搜索过程中的最大路径和
    private static int maxSum = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] parts = sc.nextLine().split("\\s+");
        // 构建二叉树
        TreeNode root = buildTree(parts);

        // 执行计算
        fun(root);

        System.out.println(maxSum);
    }

    private static int fun(TreeNode node) {
        if(node == null) {
            return 0;
        }

        // 递归计算左右子节点的最大贡献值
        // 如果贡献值小于 0，则直接丢弃（取 0）
        int leftGain = Math.max(fun(node.left), 0);
        int rightGain = Math.max(fun(node.right), 0);

        // 以当前节点为转折点的最大路径和（左 + 根 + 右）
        int currentPathSum = node.val + leftGain + rightGain;

        // 更新全局最大值
        maxSum = Math.max(maxSum, currentPathSum);

        // 返回该节点能提供给父节点的最大单边贡献（根 + max(左, 右)）
        return node.val + Math.max(leftGain, rightGain);
    }

    // --- 辅助工具：构建二叉树 (层序遍历方式) ---
    public static TreeNode buildTree(String[] parts) {
        if(parts.length == 0 || "null".equals(parts[0])) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(parts[0]));
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

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
