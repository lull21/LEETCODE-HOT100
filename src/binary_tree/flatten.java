package binary_tree;
import sun.security.krb5.internal.crypto.Aes128CtsHmacSha1EType;

import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/01 09:40
 * @Description 114. 二叉树展开为链表 【中等】
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。(前序遍历)
 * 输入：root = 1 2 5 3 4 null 6
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * 题解：
 * 方法一：递归前序遍历加入list
 * 方法二：原地操作
 * 1.对于当前节点 root：
 * 如果它有左子树，那么先序遍历中，root 右子树的所有节点一定紧跟在左子树的最右节点（前驱节点）后面。
 * 2.步骤：
 * 找到 root 左子树的最右边的节点（即左子树中最后被访问的节点）。
 * 将 root 的右子树整个接到这个“最右节点”的右边。
 * 将 root 的左子树转移到右边。
 * 将 root 的左边置空。
 * 移向下一个右节点，重复上述过程。
 **/

public class flatten {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] parts = sc.nextLine().split("\\s+");
        TreeNode root = buildTree(parts);

        //方法一：前序遍历将其加入链表中
        List<TreeNode> list = new ArrayList<>();
        fun1(root, list);
        int size = list.size();
        for(int i = 1; i <size; i++) {
            TreeNode prev = list.get(i-1), cur = list.get(i);
            prev.left = null;
            prev.right = cur;
        }

        // 方法二：原地操作
//        fun2(root); // 原地操作

        printTree(root);
    }

    public static void fun1(TreeNode root, List<TreeNode> list) {
        if(root != null) {
            list.add(root);
            fun1(root.left, list);
            fun1(root.right, list);
        }
    }

    public static void fun2(TreeNode root) {
        TreeNode curr = root;
        while(curr != null) {
            if(curr.left != null) {
                // 1. 找到左子树的最右节点
                TreeNode predecessor = curr.left;
                while(predecessor.right != null) {
                    predecessor = predecessor.right;
                }

                // 2. 将原来的右子树接到左子树最右节点的右边
                predecessor.right = curr.right;

                // 3. 将左子树插到右边，并清空左边
                curr.right = curr.left;
                curr.left = null;
            }
            // 4.继续处理下一个右节点
            curr = curr.right;
        }
    }

    public static void printTree(TreeNode root) {
        TreeNode curr = root;
        while(curr != null) {
            System.out.print(curr.val + (curr.right != null ? " " : ""));
            curr = curr.right;
        }
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
