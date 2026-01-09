package backtracking;
import java.util.*;

/**
 * @Author 2hang1iang
 * @Date 2026/01/08 22:26
 * @Description 131.分割回文串 【中等】
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。返回 s 所有可能的分割方案。
 * 输入: "aab"
 * 输出:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 * 题解：回溯
 * 关键：是要记录当前搜索到的字符位置 start，当 start 等于字符串长度时，说明已经分割完所有字符，将当前路径加入结果集。
 * 正常回溯，判断当前子串是否是回文串，如果是，则继续回溯，否则剪枝。
 **/

public class partition {
    private static List<List<String>> res = new ArrayList<>();
    private static List<String> path = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        backtrack(s, 0 );
        System.out.println(res);
    }
    private static void backtrack(String s, int start) {
        // 如果已经到达字符串末尾，说明找到了一个有效的分割方案
        if (start == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 尝试从当前位置开始的所有可能的子串
        for (int i = start; i < s.length(); i++) {
            // 截取从start到i的子串
            String substr = s.substring(start, i + 1);

            // 如果是回文串，则继续回溯
            if (isPalindrome(substr)) {
                path.add(substr);  // 添加到当前路径
                backtrack(s, i + 1);  // 递归处理剩余部分
                path.remove(path.size() - 1);  // 回溯，撤销选择
            }
        }
    }

    // 判断字符串是否是回文串
    private static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
