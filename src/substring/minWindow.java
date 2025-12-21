package substring;

import java.util.*;

/**
 * @Author 2hang1iang
 * @Date 2025/12/20 18:45
 * @Description *76.最小覆盖子串-困难
 * 给定两个字符串 s 和 t，长度分别是 m 和 n，返回 s 中的 最短窗口 子串，使得该子串包含 t 中的每一个字符（包括重复字符）。
 * 如果没有这样的子串，返回空字符串 ""。
 * 测试用例保证答案唯一。
 * 输入：
 * ADOBECODEBANC
 * ABC
 * 输出：
 * BANC
 * 题解：
 * 滑动窗口 + 计数器
 * 我们使用“左右指针”维护一个窗口，这个窗口会像毛毛虫一样移动：
 * 1. 右指针（Right）扩张：寻找可行解。不断扩大窗口，直到窗口包含了 t 中所有的字符。
 * 2. 左指针（Left）收缩：优化可行解。在保证窗口依然包含 t 中所有字符的前提下，尽可能缩小窗口，更新最短长度。
 * 解析；
 * 1. valid 变量是灵魂
 * 不要去数总字符数，要数“达标的种类”。
 * 比如 t 是 "ABC"，need.size() 就是 3。
 * 当窗口里有 2 个 'A'，1 个 'B'，1 个 'C' 时，valid 才是 3。这能精准处理重复字符。
 * 2. 为什么用 equals 而不是 ==？
 * 在 Java 中，Map.get() 返回的是 Integer 对象。
 * 对于大于 127 的数值，用 == 比较的是地址。为了稳妥，必须用 .equals() 比较数值。
 **/

public class minWindow {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String t = sc.next();

        // 1. 准备：need 记录 t 中需要的字符及频率，window 记录窗口内已有的字符及频率
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char c : t.toCharArray())
            need.put(c, need.getOrDefault(c, 0) + 1);

        int left = 0, right = 0;
        int valid = 0; // 记录窗口中已经满足 need 条件的字符种类数

        // 记录最小覆盖子串的起始位置及长度
        int start = 0, minLen = Integer.MAX_VALUE;

        while (right < s.length()) {
            char c = s.charAt(right); // c 是移入窗口的字符
            right++; // 右移窗口

            // 2. 更新窗口数据
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                // 只有当窗口内该字符数量和 t 中完全一致时，valid 才增加
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            // 3. 判断左侧窗口是否要收缩：当 valid 等于 need 的大小时，说明涵盖了所有字符
            while (valid == need.size()) {
                // 更新最小子串
                if (right - left < minLen) {
                    start = left;
                    minLen = right - left;
                }

                char d = s.charAt(left); // d 是将移出窗口的字符
                left++; // 左移窗口

                // 4. 更新窗口数据
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }

        String res = minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
        System.out.println(res);
    }

    /** HashMap 涉及对象的包装（Integer）、哈希碰撞处理以及扩容等开销。
     * 由于 ASCII 字符集只有 128 个字符，直接使用 int[128] 数组作为简易哈希表，性能会提升数倍。
     */
    public String minWindow2(String s, String t) {
        // 1. 使用数组代替 HashMap
        int[] need = new int[128];   // 记录 t 中字符出现的次数
        int[] window = new int[128]; // 记录窗口内字符出现的次数

        for (char c : t.toCharArray()) {
            need[c]++;
        }

        // 统计 t 中一共有多少种不同的字符（这是收缩窗口的触发门槛）
        int needSize = 0;
        for (int count : need) {
            if (count > 0) needSize++;
        }

        int left = 0, right = 0;
        int valid = 0; // 窗口中满足 need 条件的字符种类数
        int start = 0, minLen = Integer.MAX_VALUE;

        while (right < s.length()) {
            char c = s.charAt(right);
            right++;

            // 2. 右移动窗口时的逻辑
            if (need[c] > 0) {
                window[c]++;
                // 当窗口内该字符的数量达到 t 所需的数量时，达标种类 +1
                if (window[c] == need[c]) {
                    valid++;
                }
            }

            // 3. 判断左侧窗口是否要收缩
            while (valid == needSize) {
                // 更新最小子串的结果
                if (right - left < minLen) {
                    start = left;
                    minLen = right - left;
                }

                char d = s.charAt(left);
                left++;

                // 4. 左移窗口时的逻辑
                if (need[d] > 0) {
                    // 如果移除前该字符刚好达标，移除后就不达标了，valid -1
                    if (window[d] == need[d]) {
                        valid--;
                    }
                    window[d]--;
                }
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}