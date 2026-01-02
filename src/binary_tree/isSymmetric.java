package binary_tree;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2025/12/29 08:34
 * @Description 101. 对称二叉树 【简单】
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * 输入：
 * 1 2 2 3 4 4 3
 * 输出：
 * true
 * 题解：
 * 确定单层递归的逻辑（镜像比较）：
 *  比较L的左孩子和R的右孩子 是否对称（外侧比较）。
 *  比较L的右孩子和R的左孩子 是否对称（内侧比较）。
 *  如果两者都对称，则返回 true。
 **/

public class isSymmetric {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] parts = sc.nextLine().split("\\s+");

        TreeNode root = buildTree(parts);

        boolean res = check(root.left, root.right);

        System.out.println(res);
    }

    public static boolean check(TreeNode left, TreeNode right) {
        // 情况 1: 都为空，对称
        if(left == null && right == null) return true;

        // 情况 2: 其中一个为空或值不等，不对称
        if (left == null || right == null || left.val != right.val) return false;

        // 递归：左的左 vs 右的右（外侧） && 左的右 vs 右的左（内侧）
        return check(left.left, right.right) && check(left.right, right.left);
    }

    public static TreeNode buildTree(String[] parts) {
        if(parts.length == 0 || "null".equals(parts[0])) {
            return null;
        }

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

            if(i < parts.length && !"null".equals(parts[i])) {
                node.right = new TreeNode(Integer.parseInt(parts[i]));
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }
}
