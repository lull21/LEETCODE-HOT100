package stack;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/09 15:03
 * @Description 20. 有效的括号 【简单】
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 * 输入：()[]{}
 * 输出：true
 **/

public class isValid {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        System.out.println(fun(s));
    }

    private static boolean fun(String s) {
        if(s.length() % 2 != 0)return false;
        Deque<Character> queue = new ArrayDeque<>();
        for(char c : s.toCharArray()) {
            if(c == '[') {
                queue.push(']');
            } else if(c == '(') {
                queue.push(')');
            }else if (c == '{') {
                queue.push('}');
            } else if(queue.isEmpty() || queue.peek() != c) { // 防止右括号先出现 || 左右不匹配
                return false;
            } else {
                queue.pop();
            }
        }
        return queue.isEmpty();
    }
}
