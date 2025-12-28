package list;

import java.util.Scanner;

/**
 * @Author 2hang1iang
 * @Date 2025/12/27 10:08
 * @Description 25.K个一组翻转链表 【困难】
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * 输入：
 * 5
 * 1 2 3 4 5
 * 3
 * 输出：
 * 3 2 1 4 5
 * 题解：分而治之
 * 1.找区间：从当前起点开始，向后走 k-1 步，找到这组的末尾（tail）。如果不足 k 个，直接保持原样返回。
 * 2.翻转区间：将这 k 个节点进行翻转（这是一个独立的子函数）。
 * 3.重连链表：将翻转后的子链表接回到原链表中，并准备处理下一组。
 **/

public class reverseKGroup {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for(int i = 0; i < n; i++) {
            tail.next = new ListNode(sc.nextInt());
            tail = tail.next;
        }

        int k = sc.nextInt();

        ListNode res = fun(dummy.next, k);

        while(res != null) {
            System.out.print(res.val + ((res.next != null) ? " " : ""));
            res = res.next;
        }
    }
    public static ListNode fun(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // prev 始终指向待翻转小组的前驱节点
        ListNode prev = dummy;

        while (head != null) {
            ListNode tail = prev;
            // 1. 查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return dummy.next; // 不足 k 个，原样返回
                }
            }

            // 记录下一组的起点
            ListNode nextStart = tail.next;

            // 2. 翻转当前小组 [head, tail]
            // reverse 方法返回后，head 变成了尾部，tail 变成了头部
            ListNode[] reversed = reverse(head, tail);
            head = reversed[0];
            tail = reversed[1];

            // 3. 把翻转后的子链表接回原链表
            prev.next = head;
            tail.next = nextStart;

            // 4. 为下一轮做准备
            prev = tail;
            head = nextStart;
        }

        return dummy.next;

    }

    // 辅助函数：翻转 [head, tail] 区间的节点
    private static ListNode[] reverse(ListNode head, ListNode tail) {
        ListNode prev = null;
        ListNode curr = head;
        // 注意：翻转到 tail.next 为止
        ListNode stop = tail.next;
        while (curr != stop) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        // 返回新头和新尾（原 tail 是新头，原 head 是新尾）
        return new ListNode[]{tail, head};
    }
}
