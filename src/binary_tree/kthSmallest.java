package binary_tree;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2025/12/31 09:12
 * @Description 230. 二叉搜索树中第K小的元素 【中等】
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 * 输入：root = [3,1,4,null,2], k = 1
 * 输出：1
 * 题解：二叉搜索树有一个天然的特性：它的中序遍历（左 -> 根 -> 右）序列是严格递增的。
 * 两种实现方式
 * 递归法：代码简洁，符合直觉。通过一个全局计数器k，每访问到一个节点就k--，直到k=0。
 * 迭代法（推荐）：使用显式栈（Stack）。这种方式可以实现“早停（Early Stop）”——一旦找到第k个，立即返回，不需要遍历整棵树，效率更高。
 **/

public class kthSmallest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] parts = sc.nextLine().split("\\s+");
        TreeNode root = buildTree(parts);

        int k = sc.nextInt();

        // 2. 寻找第 k 小元素（使用迭代法）
        int result = findKthSmallest(root, k);

        // 3. 输出结果
        System.out.println(result);
    }

    /**
     * 核心逻辑：利用栈进行中序遍历（迭代法）
     */
    private static int findKthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();

        while(true) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(--k == 0) return root.val;
            root = root.right;
        }

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
