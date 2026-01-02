package binary_tree;
import javax.management.QueryEval;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2025/12/31 09:11
 * @Description 98. 验证二叉搜索树 【中等】
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 输入：root = [2,1,3]
 * 输出：true
 * 题解：【中序遍历】
 * 二叉搜索树（BST）有一个最重要的性质：它的中序遍历序列是严格递增的。
 * 验证方法： 我们只需在遍历过程中，记录前一个访问节点的值 prev。
 * 如果当前节点的值小于或等于 prev，说明不满足严格递增，即不是有效的 BST。
 **/

public class isValidBST {
    // 记录前一个节点的值，使用 Long 防止 int 边界溢出
    public static long prev = Long.MIN_VALUE;
    public static boolean isValid = true;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] parts = sc.nextLine().split("\\s+");
        TreeNode root = buildTree(parts);

        // 验证 BST
        isValid = true;
        prev = Long.MIN_VALUE;
        checkBST(root);

        // 输出结果
        System.out.println(isValid);
    }

    public static void checkBST(TreeNode root) {
        if(root == null || !isValid) return;

        // 访问左子树
        checkBST(root.left);

        if(root.val <= prev) {
            isValid = false;
            return;
        }

        prev = root.val;

        // 访问右子树
        checkBST(root.right);
    }

    public static TreeNode buildTree(String[] parts) {
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

            if(i < parts.length && !"null".equals(parts[i])) {
                node.right = new TreeNode(Integer.parseInt(parts[i]));
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }
}
