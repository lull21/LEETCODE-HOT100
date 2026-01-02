package binary_tree;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/02 10:44
 * @Description 236. 二叉树的最近公共祖先（LCA） 【中等】
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 题解：后序遍历（DFS）-递归
 * 1.边界情况：如果 root 为空，或者 root 等于 p 或 q，直接返回 root。
 * 2.递归搜索：去左子树找 p 和 q，去右子树找 p 和 q。
 * 3.结果判断：
 *  左右都有：如果在左子树找到了一个，右子树也找到了一个，说明p和q分布在当前节点的两侧，那么当前节点就是 LCA。
 *  只有左边有：说明p和q都在左子树，返回左子树的结果。
 *  只有右边有：说明p和q都在右子树，返回右子树的结果。
 *  两边都没有：返回 null。
 **/

public class lowestCommonAncestor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] parts = sc.nextLine().split("\\s+");

        // 读取需要查找的 p 和 q 的值
        int pVal = sc.nextInt();
        int qVal = sc.nextInt();

        // 构建二叉树
        TreeNode root = buildTree(parts);

        // 在树中找到p和q节点的引用
        TreeNode p = findNode(root, pVal);
        TreeNode q = findNode(root, qVal);

        // 执行核心算法
        TreeNode res = fun(root, p, q);

        System.out.println(res != null ? res.val : "null");
    }

    public static TreeNode fun(TreeNode root, TreeNode p, TreeNode q) {
        // 如果找到节点 p 或 q，或者遍历到叶子节点外，向上返回
        if(root == null || root == p || root == q) {
            return root;
        }

        // 递归左子树
        TreeNode left = fun(root.left, p, q);
        // 递归右子树
        TreeNode right = fun(root.right, p, q);

        if(left == null ) return right;
        if(right == null) return left;

        return root;
    }

    // --- 辅助工具：根据值查找树中的节点 ---
    public static TreeNode findNode(TreeNode root, int val) {
        if (root == null || root.val == val) return root;
        TreeNode left = findNode(root.left, val);
        if (left != null) return left;
        return findNode(root.right, val);
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
