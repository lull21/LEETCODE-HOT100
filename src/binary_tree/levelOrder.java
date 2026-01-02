package binary_tree;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2025/12/30 08:58
 * @Description 102. 二叉树的层序遍历 【中等】
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。
 * （即逐层地，从左到右访问所有节点）。
 * 输入：3,9,20,null,null,15,7
 * 输出：[[3],[9,20],[15,7]]
 * 题解：使用【队列（Queue）】-层序遍历实际考察-BFS（广度优先搜索）
 * 1.初始化：创建一个队列，将根节点 root 入队。
 * 2.循环处理：只要队列不为空，就继续执行：
 * （1）获取当前层长度：记录当前队列的大小 size（这代表了当前这一层有多少个节点）。
 * （2）遍历当前层：循环 size 次：
 *      从队列中弹出一个节点。
 *      将该节点的值加入当前层的搜索结果列表。
 *      左子节点入队：如果左孩子存在，将其加入队列。
 *      右子节点入队：如果右孩子存在，将其加入队列。
 * 3.结果存储：每一层遍历结束后，将该层的列表存入最终的总列表。
 **/

public class levelOrder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] parts = sc.nextLine().split("\\s+");

        TreeNode root = buildTree(parts);

        List<List<Integer>> res = fun(root);

        for(List<Integer> curr : res) {
            int currSize = curr.size();
            for(int i = 0; i < currSize - 1; i++) {
                System.out.print(curr.get(i) + " ");
            }
            System.out.println(curr.get(currSize - 1));
        }
    }

    public static List<List<Integer>> fun(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currLevel = new ArrayList<>();

            for(int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currLevel.add(node.val);

                // 将下一层节点加入队列
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            res.add(currLevel);
        }
        return res;
    }

    public static TreeNode buildTree(String[] parts) {
        if(parts.length == 0 || "null".equals(parts[0])) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(parts[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (i < parts.length && !queue.isEmpty()) {
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
