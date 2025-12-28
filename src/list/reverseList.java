package list;

import java.util.Scanner;

/**
 * @Author 2hang1iang
 * @Date 2025/12/24 10:45
 * @Description 206.反转链表-简单
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * 输入：
 * 5
 * 1 2 3 4 5
 * 输出：
 * 5 4 3 2 1
 * 题解：双指针迭代
 * 反转链表的精髓在于“原地切断并重连”。我们只需要两个指针即可完成：
 *  curr：指向当前正在处理的节点。
 *  prev：指向 curr 的前驱节点（反转后 curr 将指向它）。
 * 逻辑步骤：
 * 1.保存 curr.next（防止断开后找不到后面的路）。
 * 2.将 curr.next 指向 prev（实现反转）。
 * 3.prev 移到 curr 位置。
 * 4.curr 移到之前保存的下一个节点位置。
 **/

public class reverseList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        // 2. 构建链表 (使用尾插法和虚拟头节点)
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        for(int i = 0; i < n; i++) {
            tail.next = new ListNode(sc.nextInt());
            tail = tail.next;
        }

        ListNode newHead = reverse(dummy.next);

        printList(newHead);
    }

    public static ListNode reverse(ListNode head) {
        ListNode prev = null; // 初始化前驱为 null
        ListNode curr = head;

        // 一般都是判断curr != null 而不是curr.next != null
        while(curr != null) {
            ListNode nextTemp = curr.next; // 1. 暂存后继节点
            curr.next = prev;// 2. 翻转指针方向
            prev = curr; // 3. prev 后移
            curr = nextTemp; // 4. curr 后移
        }
        return prev; // 最终 prev 指向原链表的末尾，即新链表的头
    }

    public static void printList(ListNode head) {
        ListNode curr = head;
        while(curr != null) {
            System.out.print(curr.val + (curr.next != null ? " " : ""));
            curr = curr.next;
        }
    }
}
