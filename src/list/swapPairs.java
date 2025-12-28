package list;

import java.util.Scanner;

/**
 * @Author 2hang1iang
 * @Date 2025/12/26 08:55
 * @Description 24.两两交换链表中的节点 中等
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 * 输入：
 * 4
 * 1 2 3 4
 * 输出
 * 2 1 4 3
 * 题解：交换时需要操作【三个相邻节点】之间的指针关系
 * 假设我们要交换节点 A 和 B，我们需要一个 temp 指针指向它们的前驱节点。
 * 交换步骤（以 temp -> A -> B -> next... 为例）：
 * 1.指向目标：让 temp.next 指向 B。
 * 2.接上后驱：让 A.next 指向 B.next（防止后面的链表丢失）。
 * 3.完成交换：让 B.next 指向 A。
 * 完成这三步后，节点顺序变为 temp -> B -> A -> next...。随后将 temp 移动到 A 的位置，继续下一轮交换。
 **/

public class swapPairs {
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
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode temp = dummy;

        // 必须有两个节点才能交换
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;     // A
            ListNode node2 = temp.next.next; // B

            // 开始“大风车”式交换
            // 交换顺序，是按照原来链表中curr->node1->node2的顺序来依次变换他们的下一个节点
            temp.next = node2;          // 步骤1：前驱指向B
            node1.next = node2.next;    // 步骤2：A指向B的后面
            node2.next = node1;         // 步骤3：B指向A

            // 移动 temp，准备下一对交换（注意：现在 node1 是交换后的第二个节点）
            temp = node1;
        }

        return dummy.next;
    }
}
