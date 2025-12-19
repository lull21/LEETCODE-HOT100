package slide_window;

import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2025/12/19 17:03
 * @Description 438.找到字符串中所有字母异位词
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，
 * 返回这些子串的起始索引。不考虑答案输出的顺序。
 * 输入：
 * cbaebabacd
 * abc
 * 输出
 * [0, 6]
 * 题解：
 * 使用int[26]数组最高效
 * 窗口大小是固定的
 * 想象你有两个频率计数器（在代码中我们用数组实现）：
 * 目标计数器 (pCount)：记录字符串 p 中每个字母出现的次数。
 * 窗口计数器 (sCount)：记录当前滑动窗口中每个字母出现的次数。
 *
 * 窗口的宽度始终等于 p.length()。
 * 窗口每向右移动一格：加一个新字符，扔一个旧字符。
 * 每次移动后，对比两个计数器是否完全一样。如果一样，记录起始索引。
 **/

public class findAnagrams {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.next();
        String p = sc.next();

        int sLen = s.length();
        int pLen = p.length();

        List<Integer> res = new ArrayList<>();
        if(sLen < pLen) System.out.println(res);

        int[] pCount = new int[26];
        int[] sCount = new int[26];

        // 2. 初始化：统计 p 的频率，以及 s 中第一个窗口的频率
        for (int i = 0; i < pLen; i++) {
            pCount[p.charAt(i) - 'a']++;
            sCount[s.charAt(i) - 'a']++;
        }

        // 3. 检查第一个窗口是否匹配
        if (Arrays.equals(pCount, sCount)) {
            res.add(0);
        }

        // 4. 开始滑动：i 代表窗口的右边界
        for (int i = pLen; i < sLen; i++) {
            // 进一个：新字符进入窗口右侧
            sCount[s.charAt(i) - 'a']++;

            // 出一个：旧字符离开窗口左侧（索引为 i - pLen）
            sCount[s.charAt(i - pLen) - 'a']--;

            // 检查当前窗口是否与 pCount 相等
            if (Arrays.equals(pCount, sCount)) {
                res.add(i - pLen + 1);
            }
        }
        System.out.println(res);
    }
}
