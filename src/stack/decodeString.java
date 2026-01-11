package stack;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/11 13:37
 * @Description 394. 字符串解码 【中等】
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 题解: 栈,两个栈来分别存储数字（重复次数）和字符串（解码过程中的前缀
 * 1.初始化：
 * 数字栈 numStack：用于存储遇到的重复次数 k。
 * 字符串栈 strStack：用于存储当前已解码的前缀字符串。
 * 当前数字 num：用于累积多位数字，初始为 0。
 * 当前字符串 res：使用 StringBuilder 存储正在构建的字符串，初始为空。
 * 2.遍历字符串的每个字符：
 * 如果是数字（'0'~'9'）：
 *  更新当前数字：num = num * 10 + (c - '0')，用于处理多位数字。
 * 如果是字母：
 *  直接将字母追加到当前 res 中。
 * 如果是 '['：
 *  表示开始一个新的嵌套子串，需要将当前数字 num 和当前字符串 res 分别入栈。
 *  然后重置 num = 0，res = new StringBuilder()，准备处理括号内的内容。
 * 如果是 ']'：
 *  表示当前嵌套子串结束，需要从栈中弹出重复次数 repeat 和前缀字符串 prev。
 *  将当前 res 重复 repeat 次，得到重复后的字符串 temp。
 *  将 temp 拼接到 prev 后面，然后将 res 指向 prev（即 res = prev）。
 * 3.遍历结束：
 * 当前 res 即为最终解码结果，返回 res.toString()。
 **/

public class decodeString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(decode(s));
    }

    private static String decode(String s) {
        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<StringBuilder> strStack = new ArrayDeque<>();
        int num = 0;
        StringBuilder res = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                // 累积数字
                num = num * 10 + (c - '0'); // "123[abc]"
            } else if (c == '[') {
                // 将当前数字和字符串入栈，并重置
                numStack.push(num);
                strStack.push(res);
                num = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                // 弹出数字和字符串，进行重复拼接
                int repeat = numStack.pop();
                StringBuilder prev = strStack.pop();
                StringBuilder temp = new StringBuilder();
                for (int i = 0; i < repeat; i++) {
                    temp.append(res);
                }
                res = prev.append(temp);
            } else {
                // 字母直接追加
                res.append(c);
            }
        }

        return res.toString();
    }
}
