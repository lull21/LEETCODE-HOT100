package list;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2025/12/26 08:54
 * @Description 2. 两数相加 中等
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 输入：
 * 7 4
 * 9 9 9 9 9 9 9 9 9
 * 9 9 9 9
 * 输出：
 * 8 9 9 9 0 0 0 1
 * 题解：【记录进位】模拟小学数学的竖式计算
 * 1.逐位相加：从两个链表的头节点（个位）开始往后走。
 * 2.处理进位：设置一个变量 carry 存储进位（0 或 1）。
 * 3.补位对齐：如果一个链表较短，缺失的位看作 0。
 * 4.最后进位：如果最高位相加后还有进位，记得补一个 1。
 **/

public class addTwoNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();

        ListNode dummy1 = new ListNode(0);
        ListNode tail1 = dummy1;
        for(int i = 0; i < m; i++) {
            tail1.next = new ListNode(sc.nextInt());
            tail1 = tail1.next;
        }

        ListNode dummy2 = new ListNode(0);
        ListNode tail2 = dummy2;
        for(int i = 0; i < n; i++) {
            tail2.next = new ListNode(sc.nextInt());
            tail2 = tail2.next;
        }

        ListNode res = fun(dummy1.next, dummy2.next);
        while(res != null) {
            System.out.print(res.val + ((res.next != null) ? " " : ""));
            res = res.next;
        }
    }

    public static ListNode fun(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0; // 进位

        // 只要还有位没算完，或者还有进位，就继续循环
        while(l1 != null || l2 != null || carry != 0) {
            // 获取当前位的值，如果链表走完了则补0
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;

            int sum = x + y + carry;
            carry = sum / 10; // 更新进位
            curr.next = new ListNode(sum % 10);// 创建新节点存放个位

            curr = curr.next;
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }
        return dummy.next;
    }
}