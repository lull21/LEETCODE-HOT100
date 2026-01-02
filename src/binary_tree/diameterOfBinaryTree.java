package binary_tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @Author 2hang1iang
 * @Date 2025/12/30 08:58
 * @Description 543. 二叉树的直径 【简单】
 * 给你一棵二叉树的根节点，返回该树的 直径 。
 * 二叉树的 直径 是指树中【任意两个节点】之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
 * 两节点之间路径的 长度 由它们之间边数表示。
 * 输入：1 2 3 4 5
 * 输出：3
 * 解释：3 ，取路径 [4,2,1,3] 或 [5,2,1,3] 的长度。
 * 题解：深度优先搜索（DFS）-【后序遍历】
 * 直径 = 左子树深度 + 右子树深度
 * 自底向上（Bottom-up）：使用后序遍历。
 * 先拿到左右子树的高度，再处理当前节点。这样每个节点只被访问一次，时间复杂度为 $O(n)$。
 * 边数 vs 节点数：路径上的节点数 = $L + R + 1$。路径上的边数（即直径）= $L + R$。递归函数通常返回节点数，方便空节点返回 0。
 * 全局变量：在 Java 中，由于 int 是值传递，建议使用一个类成员变量或长度为 1 的数组来记录全局最大直径。
 **/

public class diameterOfBinaryTree {
    public static int maxDepth;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] parts = sc.nextLine().split(" ");

        TreeNode root = bulidTree(parts);

        maxDepth = 0;
        fun(root);

        System.out.println(maxDepth);
    }

    public static int fun(TreeNode root) {
        if(root == null) return 0;

        int leftDepth = fun(root.left);
        int rightDepth = fun(root.right);

        maxDepth = Math.max(maxDepth, leftDepth + rightDepth);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static TreeNode bulidTree(String[] parts) {
        if(parts.length == 0 || "null".equals(parts[0])) return null;

        TreeNode root = new TreeNode(Integer.parseInt(parts[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while(!queue.isEmpty() && i < parts.length) {
            TreeNode node = queue.poll();
            if(i < parts.length && !"null".equals(parts[i])) {
                node.left = new TreeNode(Integer.parseInt(parts[i]));
                queue.add(node.left);
            }
            i++;

            if(i < parts.length && !"null".equals(parts[i])){
                node.right = new TreeNode(Integer.parseInt(parts[i]));
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }
}
