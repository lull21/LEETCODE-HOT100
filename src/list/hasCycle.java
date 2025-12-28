package list;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2025/12/25 09:05
 * @Description 141. 环形链表 简单
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
 * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
 * 输入：
 * 4
 * 3,2,0,-4
 * 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 题解：快慢指针
 * 快指针每次走两步，慢指针每次走一步，如果有环，快指针一定会追上慢指针。
 * 本题中，while循环判断条件是【关键】
 **/

public class hasCycle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for(int i = 0; i < n; i++) {
            tail.next = new ListNode(sc.nextInt());
            tail = tail.next;
        }

        // 构造环形
        int pos = sc.nextInt();
        ListNode curr = dummy.next;
        for(int i = 0; i <= pos; i++) {
            curr = curr.next;
        }
        tail.next = curr;
        System.out.println("head.val=" + dummy.next.val);
        System.out.println("tail.val=" + tail.val);
        System.out.println(fun(dummy.next));
    }

    public static boolean fun(ListNode head) {
        if(head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        // 因为末尾节点也会指向节点，因此需要判断next
        // while循环判断条件是【关键】
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if(slow == fast) {
                System.out.println("meetNode.val = " + slow.val);
                return true;
            }
        }
        return false;
    }
}
