package backtracking;
import java.io.FilterOutputStream;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/07 21:09
 * @Description 39.组合总和 【中等】
 *给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates中的同一个数字可以【无限制重复被选取】 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 题解：回溯--数组排序+重复用i
 **/

public class combinationSum {

    private static int target;
    private static int sum = 0;
    private static List<List<Integer>> res = new ArrayList<>();
    private static List<Integer> path = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < nums.length; i++) {
            nums[i] = sc.nextInt();
        }
        target = sc.nextInt();

        Arrays.sort(nums);

        backtrack(nums, 0);

        System.out.println(res);
    }

    private static void backtrack(int[] nums, int startIndex) {
        if(sum == target){
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i = startIndex; i < nums.length; i++) {
            if(sum + nums[i] > target) break;

            sum += nums[i];
            path.add(nums[i]);
            backtrack(nums, i);
            sum -= nums[i];
            path.removeLast();
        }
    }
}
