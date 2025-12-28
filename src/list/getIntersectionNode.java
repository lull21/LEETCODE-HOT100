package list;

import java.util.Scanner;

/**
 * @Author 2hang1iang
 * @Date 2025/12/24 09:22
 * @Description 160.相交链表-简单
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。
 * 如果两个链表不存在相交节点，返回 null 。
 * 输入：
 * 5 3 代表链表 A 和 B 的长度
 * 1 9 1 2 4
 * 3 2 4
 * 2 第四行输入一个索引（或偏移量），表示从哪个位置开始相交。
 * 输出：
 * 2
 * 题解：双指针
 * 1.创建两个指针 pA 和 pB，分别指向 headA 和 headB。
 * 2.两个指针同时向后移动。
 * 3.当 pA 到达链表末尾时，将其重定向到 headB；同理，当 pB 到达末尾时，将其重定向到 headA。
 * 4.原理： 假设 A 链表非公共部分长为 a，B 链表非公共部分长为 b，公共部分长为 c。
 * 指针 A 走过的路径：a + c + b指针 B 走过的路径：b + c + a它们会在相交点相遇，因为此时路径长度相等。
 * 如果不相交，它们最终会同时指向 null。
 **/
//class ListNode {
//    int val;
//    ListNode next;
//    ListNode(int x) {val = x;}
//}

public class getIntersectionNode {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. 读取长度
        int n = sc.nextInt();
        int m = sc.nextInt();

        // 2. 构建链表 A
        ListNode headA = buildList(sc, n);

        // 3. 构建链表 B
        ListNode headB = buildList(sc, m);

        // 4. (特殊处理) 模拟相交逻辑
        // 在实际 ACM 题目中，通常会告知第几个节点开始相交，需要手动连接
        int intersectVal = sc.nextInt(); // 假设输入告知相交节点的值
        if (intersectVal != 0) {
            connect(headA, headB, intersectVal);
        }

        // 5. 调用算法
        ListNode result = fun(headA, headB);

        // 6. 按照要求输出
        if (result != null) System.out.println(result.val);
        else System.out.println("null");
    }

    // 2.核心算法 双指针
    public static ListNode fun(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;

        ListNode pA = headA;
        ListNode pB = headB;

        // 只要两个指针不相等，就继续循环
        // 如果相交，它们会在第一个公共节点相遇
        // 如果不相交，它们最终都会变成 null，跳出循环
        while(pA != pB) {
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }

        return pA;
    }

    // 辅助方法：根据输入构建单链表
    private static ListNode buildList(Scanner sc, int size) {
        if (size == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int i = 0; i < size; i++) {
            cur.next = new ListNode(sc.nextInt());
            cur = cur.next;
        }
        return dummy.next;
    }

    // 辅助方法：手动制造相交（为了测试算法）
    private static void connect(ListNode a, ListNode b, int val) {
        ListNode target = a;
        while (target != null && target.val != val) target = target.next;

        ListNode tailB = b;
        while (tailB.next != null) tailB = tailB.next;

        tailB.next = target; // 让 B 的尾部指向 A 中的某个节点
    }
}
