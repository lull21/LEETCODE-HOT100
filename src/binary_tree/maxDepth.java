package binary_tree;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2025/12/29 08:34
 * @Description 104. 二叉树的最大深度 【简单】
 * 给定一个二叉树 root ，返回其最大深度。
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 * 输入：
 * 3 9 20 null null 15 7
 * 输出：
 * 3
 * 题解：【后序遍历】 - (递归)
 * 整棵树的最大深度 = max(左子树深度, 右子树深度) + 1（这里的 +1 是算上根节点自己）。
 * 这是一个典型的后序遍历逻辑：先求左右，再处理根。
 * 2. 递归三部曲
 * （1）确定递归函数的参数和返回值： 参数是当前节点，返回值是当前节点为根的树的深度。
 * （2）确定终止条件： 如果节点为空（null），深度为 0。
 * （3）确定单层递归的逻辑：
 *   递归计算左子树深度。
 *   递归计算右子树深度。
 *   取两者最大值再加 1。
 **/

public class maxDepth {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] parts = sc.nextLine().split("\\s+");

        TreeNode root = buildTree(parts);

        int res = getDepth(root);
        System.out.println(res);
    }

    public static int getDepth(TreeNode root) {
        if(root == null) return 0;

        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        int depth = Math.max(leftDepth, rightDepth) + 1;
        return depth;
    }

    public static TreeNode buildTree(String[] parts) {
        if(parts.length == 0 || "null".equals(parts[0])) return null;

        TreeNode root = new TreeNode(Integer.parseInt(parts[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while(!queue.isEmpty() && i < parts.length) {
            TreeNode node = queue.poll();

            if(i < parts.length && !parts[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(parts[i]));
                queue.add(node.left);
            }
            i++;

            if(i < parts.length && !parts[i].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(parts[i]));
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }
}
