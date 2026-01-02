package binary_tree;
import sun.reflect.generics.tree.Tree;

import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2025/12/29 08:34
 * @Description 226. 翻转二叉树 【简单】
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 * 输入：
 * 4,2,7,1,3,6,9
 * 输出：
 * 4,7,2,9,6,3,1
 * 题解：【前序遍历】递归分治 - 对于树中的每一个节点，交换其左子节点和右子节点。
 *
 **/

public class invertTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] parts = sc.nextLine().split("\\s+");

        TreeNode root = buildTree(parts);

        TreeNode res = invert(root);

        printTree(res);

    }

    public static TreeNode invert(TreeNode root) {
        if(root == null) return null;

        // 单层逻辑：交换左右孩子
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invert(root.left);
        invert(root.right);

        return root;
    }

    public static void printTree(TreeNode root) {
        if(root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<String> res = new ArrayList<>();

        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node != null) {
                res.add(String.valueOf(node.val));
                queue.add(node.left);
                queue.add(node.right);
            } else {
                res.add("null");
            }
        }

        // 移除末尾多余的 null 以符合常规格式
        int last = res.size() - 1;
        while(last >= 0 && res.get(last).equals("null")) {
            res.remove(last--);
        }
        System.out.println(String.join(" ",res));
    }

    public static TreeNode buildTree(String[] parts) {
        if(parts.length == 0 || parts[0] == "null") return null;

        TreeNode root = new TreeNode(Integer.parseInt(parts[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while(!queue.isEmpty() && i < parts.length) {
            TreeNode node = queue.poll();

            // 左子树
            if(i < parts.length && parts[i] != "null") {
                node.left = new TreeNode(Integer.parseInt(parts[i]));
                queue.add(node.left);
            }
            i++;

            // 右子树
            if(i < parts.length && parts[i] != "null") {
                node.right = new TreeNode(Integer.parseInt(parts[i]));
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }
}
