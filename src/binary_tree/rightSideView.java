package binary_tree;

import java.util.*;

/**
 * @Author 2hang1iang
 * @Date 2025/12/31 09:12
 * @Description 199. 二叉树的右视图 【中等】
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1,3,4]
 * 题解：【迭代法】站在二叉树的右侧。你能看到的节点，其实就是每一层中最右边的那个节点。
 * 层序遍历（BFS）：我们按照从上到下、从左到右的顺序访问每一层。
 * 核心技巧：在处理每一层节点时，我们记录当前层的节点个数 size。
 * 当我们遍历到该层的第 size 个节点（即最后一个节点）时，这个节点就是从右侧能看到的。
 **/

public class rightSideView {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] parts = sc.nextLine().split("\\s+");
        TreeNode root = buildTree(parts);

        List<Integer> res = fun(root);

        System.out.println(res);
    }

    public static List<Integer> fun(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                // i
                if(i == size - 1) {
                    res.add(node.val);
                }

                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
        }
        return res;
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
