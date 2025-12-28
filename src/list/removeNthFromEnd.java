package list;

import java.util.Scanner;

/**
 * @Author 2hang1iang
 * @Date 2025/12/26 08:55
 * @Description 19.删除链表的倒数第N个结点 中等
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 输入:
 * 5
 * 1 2 3 4 5
 * 2
 * 输出：
 * 1 2 3 5
 * 题解：快慢指针的“间距法” 【技巧题】
 * 1.准备阶段：先让“快指针”（fast）先跑 n 步。
 * 2.同步阶段：让“慢指针”（slow）和“快指针”同时开始跑。
 * 3.结束阶段：当快指针到达终点（null）时，快慢指针之间始终保持着 n 的距离。此时，慢指针恰好指向倒数第 n 个节点的前驱节点。
 *
 * 或者采用先计算链表中长度num,然后遍历到num-n为被删除的前一个节点
 **/

public class removeNthFromEnd {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int L = sc.nextInt();

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for(int i = 0; i < L; i++) {
            tail.next = new ListNode(sc.nextInt());
            tail = tail.next;
        }

        int n = sc.nextInt();

        ListNode res = fun1(dummy.next, n);
//        ListNode res = fun2(dummy.next, n);
        while(res != null) {
            System.out.print(res.val + ((res.next != null) ? " " : ""));
            res = res.next;
        }
    }

    public static ListNode fun1(ListNode head, int n) {
        // 依然使用虚拟头节点，这是处理链表删除（尤其是删除头节点）的万能保险
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        // 1. 快指针先走 n + 1 步
        // 为什么要走 n + 1 步？因为我们要让 slow 停在被删节点的前一个位置
        for(int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // 2. 快慢指针同时移动，直到快指针走到头
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 3. 此时 slow 位于待删除节点的前驱，执行删除
        slow.next = slow.next.next;

        return dummy.next;
    }
    public static ListNode fun2(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;

        int num = 0;
        while(curr.next != null) {
            num++;
            curr = curr.next;
        }

        curr = dummy;
        for(int i = 0; i < num - n; i++) {
            curr = curr.next;
        }
        curr.next = curr.next.next;
        return dummy.next;
    }
}
