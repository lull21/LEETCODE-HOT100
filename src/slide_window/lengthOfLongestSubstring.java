package slide_window;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2025/12/19 16:31
 * @Description 3.无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。(子串-->要求连续)
 * 输入：
 * abcabcbb
 * 输出：
 * 3 （abc）
 * 题解：
 * 右端（right）：负责向右伸展，探索新的字符。
 * 左端（left）：负责在遇到重复字符时，向右收缩，直到窗口内不再有重复。
 * 哈希集合（HashSet）：用来记录当前“窗口”里都有哪些字符。
 **/

public class lengthOfLongestSubstring {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        Set<Character> set = new HashSet<>(); // 唯一，无序
        int left = 0;
        int maxLen = 0;
        for(int right = 0; right < str.length(); right++) {
            char c = str.charAt(right);
            // 3. 遇到重复字符，缩小窗口，从左边开始删除
            // 要删除到确保，当前窗口中没有与当前字符相同的字符为止;
            while(set.contains(c)) {
                set.remove(str.charAt(left));
                left++;
            }
            // 4. 将当前字符加入窗口
            set.add(c);
            // 5. 更新最大长度（当前窗口长度 = right - left + 1）
            maxLen = Math.max(maxLen, right - left  + 1);
        }
        System.out.println(maxLen);
    }

}
