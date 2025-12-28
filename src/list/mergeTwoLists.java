package list;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2025/12/25 09:08
 * @Description 21. 合并两个有序链表 简单
 * 将两个升序链表合并为一个新的 升序 链表并返回。
 * 新链表是通过拼接给定的两个链表的所有节点组成的。
 * 输入：
 * 3 3
 * 1 2 4
 * 1 3 4
 * 输出：
 * 1 1 2 3 4 4
 * 题解：双指针“拉链式”合并
 * 先创建一个新链表来存放合并后的链表
 * 【最后一个节点别忘了，要加上】
 * 创建节点时，
 *  对于在后续要赋值的，初始化为 ListNode node = null;
 *  对于要当虚拟头节点的，初始化为 ListNode dummy = new ListNode(0);
 **/

public class mergeTwoLists {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        // 构造每个链表都要新new一个虚拟头节点dummy，一个尾节点tail
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

        ListNode head = fun(dummy1.next, dummy2.next);

        ListNode printNode = head;
        while(printNode != null) {
            System.out.print(printNode.val + " ");
            printNode = printNode.next;
        }
    }

    public static ListNode fun(ListNode list1, ListNode list2) {
        // 技巧：使用哑节点 (Dummy Node) 作为新链表的起点
        // 这样可以避免判断头节点为空的特殊情况
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while(list1 != null && list2 != null) {
            if(list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }

        // 4. 处理剩余部分：如果其中一个链表走完了，直接连接另一个
        curr.next = (list1 != null) ? list1 : list2;

        return dummy.next;
    }
}
