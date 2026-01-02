package binary_tree;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/01 09:40
 * @Description 105. 从前序与中序遍历序列构造二叉树 【中等】
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历，
 * inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * 输入：
 * 5 5
 * 3 9 20 15 7
 * 9 3 15 20 7
 * 输出：
 * 3 9 20 null null 15 7
 * 题解：先序遍历确定根节点，利用中序遍历确定左右子树的边界。
 *要构造一棵树，我们需要知道三个要素：根节点是什么、左子树包含哪些节点、右子树包含哪些节点。
 * 1.确定根节点：先序遍历 preorder 的第一个元素永远是当前的根节点。
 * 2.划分左右子树：在中序遍历 inorder 中找到这个根节点的位置（假设索引为 i）。
 *  i 左边的所有元素都属于左子树。
 *  i 右边的所有元素都属于右子树。
 * 3.计算子树大小：根据中序遍历中左子树节点的个数（i - inorder_start），我们可以在先序遍历中跳过相同数量的节点，从而找到右子树在先序数组中的起始位置。
 * 4.递归构建：对左子树和右子树重复上述过程。
 **/

public class buildTree {
    // 全局变量或传递 Map 以提高查找效率
    private static Map<Integer, Integer> inorderMap = new HashMap<>();
    private static int[] preorderGlobal;
    private static int preorderIndex = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[] preorder = new int[m];
        int[] inorder = new int[n];
        for(int i = 0; i < m; i++) {
            preorder[i] = sc.nextInt();
        }
        for(int j = 0; j < n; j++) {
            inorder[j] = sc.nextInt();
        }

        // 执行算法
        TreeNode root = buildTree(preorder, inorder);

        printTree(root);
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        preorderGlobal = preorder;
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return helper(0, inorder.length - 1);
    }

    /**
     * @param inStart  当前子树在 inorder 中的左边界
     * @param inEnd    当前子树在 inorder 中的右边界
     */
    public static TreeNode helper(int inStart, int inEnd) {
        // 递归终止条件
        if (inStart > inEnd) {
            return null;
        }

        // 1. 创建根节点
        TreeNode root = new TreeNode(preorderGlobal[preorderIndex]);

        // 2. 找到根节点在 inorder 中的位置
        int inIndex = inorderMap.get(root.val);

        // 3.前序遍历指针++
        preorderIndex++;

        // 4. 递归构建左右子树
        // 左子树根节点在 preorder 中就是 preStart + 1
        root.left = helper(inStart, inIndex - 1);

        // 右子树根节点在 preorder 中是 preStart + (左子树长度) + 1
        root.right = helper(inIndex + 1, inEnd);

        return root;
    }

    private static void printTree(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<String> res = new ArrayList<>();

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                res.add(String.valueOf(node.val));
                queue.add(node.left);
                queue.add(node.right);
            } else {
                res.add("null");
            }
        }

        // 移除末尾多余的 null 以符合常规格式
        int last = res.size() - 1;
        while (last >= 0 && res.get(last).equals("null")) {
            res.remove(last--);
        }
        System.out.println(String.join(" ", res));
    }
}
