package list;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author 2hang1iang
 * @Date 2025/12/24 10:46
 * @Description 234.回文链表-简单
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 * 输入：：
 * 4
 * 1 2 2 1
 * 输出：
 * true
 * 题解：链表数值导入list中，然后同时从前和后往中间遍历比较每一次的值，如果相同就继续不同就不是回文
 **/

public class isPalindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        ListNode dummy = new ListNode(0);// 用来当接收链表的头节点的前一个虚拟节点
        ListNode tail = dummy;
        for(int i = 0; i < n; i++) {
            tail.next = new ListNode(sc.nextInt());
            tail = tail.next;
        }

        boolean result = fun(dummy.next);

        System.out.println(result);
    }

    public static boolean fun(ListNode head) {
        ListNode cur = head;
        List<Integer> list = new ArrayList<>();
        // 一般都是判断curr != null 而不是curr.next != null
        while(cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        int left = 0;
        int right = list.size() - 1;
        while(left < right) {
            if(!list.get(left).equals(list.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
