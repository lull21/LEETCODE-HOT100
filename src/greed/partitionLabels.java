package greed;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/12 21:00
 * @Description 763. 划分字母区间 【中等】
 * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
 * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
 * 返回一个表示每个字符串片段的长度的列表。
 * 输入：s = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 题解：字母，用数组 int[26] 记录每个字符最后出现的下标
 * 每次更新 end
 * 每次检查 i== end ,再更新start
 **/

public class partitionLabels {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int[] last = new int[26];
        for(int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        List<Integer> res = new ArrayList<>();
        int start = 0, end = 0;
        for(int i = 0; i < s.length(); i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if(i == end) {
                res.add(end - start + 1);
                start = end + 1;
            }
        }
        System.out.println(res);
    }
}
