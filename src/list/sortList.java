package list;

import java.util.Scanner;

/**
 * @Author 2hang1iang
 * @Date 2025/12/27 10:08
 * @Description 148.排序链表 中等
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * 输入：
 * 4
 * 4 2 1 3
 * 输出：
 * 1 2 3 4
 * 题解：【归并排序】-递归合并
 * 1.切分 (Split)：使用【快慢指针】找到链表的中点，将链表断开成左右两部分。
 * 2.递归 (Recursion)：递归地对左半部分和右半部分进行排序，直到节点数为 1 或 0。
 * 3.合并 (Merge)：将两个已经排好序的子链表合并成一个有序链表。
 **/

public class sortList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

         ListNode dummy = new ListNode(0);
         ListNode tail = dummy;
         for(int i = 0; i < n; i++) {
             tail.next = new ListNode(sc.nextInt());
             tail = tail.next;
         }

         ListNode res = fun(dummy.next);

         while(res != null) {
             System.out.print(res.val + ((res.next != null) ? " " : ""));
             res = res.next;
         }
    }
    public static ListNode fun(ListNode head) {
        if(head == null || head.next == null) {
            return head; // 这里不是直接返回null，是因为判断条件里有head.next，所以当链表只有一个节点时，也会返回该节点
        }

        // 1.找到中点切断
        ListNode prev = null, slow = head, fast = head;
        while(fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null; // 【重要】断开链表，分为左右两段

        // 2. 递归排序左右两段
        ListNode l1 = fun(head);
        ListNode l2 = fun(slow);

        // 3.合并
        return merge(l1, l2);
    }

    /**
     * 合并两个有序链表
     */
    private static ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while(l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        if(l1 != null) curr.next = l1;
        if(l2 != null) curr.next = l2;

        return dummy.next;
    }
}
