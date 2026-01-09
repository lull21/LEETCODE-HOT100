package backtracking;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/06 20:54
 * @Description 78.子集 【中等】
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 题解：全排列是关注“顺序”（如 [1,2] 和 [2,1] 不同），而子集关注“组合”（如 [1,2] 和 [2,1] 是同一个子集，且解集不能重复）。
 *一个变量 startIndex，每次递归时只处理当前数字之后的元素。
 *
 **/

public class subsets {
    static List<List<Integer>> res = new ArrayList<>();
    static List<Integer> path = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];

        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        backtrack(nums, 0);

        System.out.println(res);
    }

    private static void backtrack(int[] nums, int startIndex) {
        res.add(new ArrayList<>(path));
        for(int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);
            backtrack(nums, i + 1);
            path.removeLast(); // jdk21
//            path.remove(path.size() - 1); // jdk8，11
        }
    }
}
