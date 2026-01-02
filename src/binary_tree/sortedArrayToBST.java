package binary_tree;

import java.util.*;

/**
 * @Author 2hang1iang
 * @Date 2025/12/30 08:58
 * @Description 108. 将有序数组转换为二叉搜索树 【简单】
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 * 输入：
 * 5
 * -10,-3,0,5,9
 * 输出：
 * 0,-3,9,-10,null,5
 * 题解：“取中点做根节点” + 递归
 * 递归分治流程：
 * 1.找中点：mid = (left + right) / 2。
 * 2.建根：以 nums[mid] 创建新节点。
 * 3.分治：
 *  左区间 [left, mid - 1]递归生成左子树。
 *  右区间 [mid + 1, right]递归生成右子树。
 * 4.出口：当left>right时，返回null。
 **/

public class sortedArrayToBST {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        TreeNode root = fun(nums, 0, n -1);

        printTree(root);
    }

    public static TreeNode fun(int[] nums, int left, int right) {
        if(left > right) return null;

        // 选取中间位置，防止溢出
        int mid = left + (right - left) / 2;

        TreeNode node = new TreeNode(nums[mid]);
        // 递归构建左右子树
        node.left = fun(nums, left, mid - 1);
        node.right = fun(nums, mid + 1, right);

        return node;
    }

    public static void printTree(TreeNode root) {
        if(root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<String> res = new ArrayList<>();

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node != null) {
                res.add(String.valueOf(node.val));
                queue.add(node.left);
                queue.add(node.right);
            }else {
                res.add("null");
            }
        }

        int i = res.size() - 1;
        while(i >= 0 && res.get(i).equals("null")) {
            res.remove(i--);
        }
        System.out.println(String.join(" ", res));
    }
}
