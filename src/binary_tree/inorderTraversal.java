package binary_tree;

import java.util.*;

/**
 * @Author 2hang1iang
 * @Date 2025/12/28 12:11
 * @Description 94.二叉树的中序遍历 简单
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 * 输入：
 * 1 null 2 3
 * 输出：
 * 1 3 2
 * 题解：
 * 中序遍历，左子树--根节点--右子树，对于二叉搜索树是升序，这一特性要记住
 * 方法一：递归(首选)
 * 方法二：迭代
 **/

public class inorderTraversal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String line = sc.nextLine();
        String[] parts = line.split("\\s+");

        TreeNode root = bulidTree(parts);

        List<Integer> res = new ArrayList<>();
//        inorder1(root, res); // 递归
        inorder2(root, res); // 迭代

        for(int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i) + (i != res.size() - 1 ? " " : ""));
        }
    }

    public static void inorder1(TreeNode root, List<Integer> list) {
        if(root == null) return;
        inorder1(root.left, list); // 左
        list.add(root.val);       // 中
        inorder1(root.right, list);// 右
    }

    public static void inorder2(TreeNode root, List<Integer> list) {
        if(root == null) return;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;

        while(!stack.isEmpty() || cur != null) {
            while(cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            list.add(cur.val);
            cur = cur.right;
        }
        return;
    }

    public static TreeNode bulidTree(String[] parts) {
        if (parts.length == 0 || parts[0].equals("null")) return null;

        TreeNode root = new TreeNode(Integer.parseInt(parts[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (!queue.isEmpty() && i < parts.length) {
            TreeNode node = queue.poll();

            // 处理左孩子
            if (i < parts.length && !parts[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(parts[i]));
                queue.add(node.left);
            }
            i++;

            // 处理右孩子
            if (i < parts.length && !parts[i].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(parts[i]));
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }
}
