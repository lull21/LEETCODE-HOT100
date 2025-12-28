package list;

import java.util.Scanner;

/**
 * @Author 2hang1iang
 * @Date 2025/12/28 09:25
 * @Description 23.合并K个升序链表 【困难】
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * // 模拟输入：假设第一行输入 k，表示有 k 个链表
 * // 接下来每行输入一个链表的所有元素，以空格分隔
 * 3
 * 1 4 5
 * 1 3 4
 * 2 6
 * 输出：[1,1,2,3,4,4,5,6]
 * 题解：
 * 方法一：遍历两两有序链表合并
 * res = merge(res, list[i]);
 * 方法二：二分法+合并
 **/

public class mergeKLists {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        sc.nextLine(); // 添加这行来消耗换行符
        int n = Integer.parseInt(sc.nextLine());

        ListNode[] list = new ListNode[n];
        for(int i = 0; i < n; i++) {
            String line = sc.nextLine();
            list[i] = buildList(line);
        }

        ListNode res = null;
//        for(int i = 0; i < n; i++) {
//            res = fun1(res, list[i]);
//        }
        res = fun2(list, 0, n - 1);

        while(res != null) {
            System.out.print(res.val + ((res.next != null) ? " " : ""));
            res = res.next;
        }
    }

    public static ListNode fun2(ListNode[] list, int left, int right){
        if(left == right) {
            return list[left];
        }
        if(left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        return fun1(fun2(list, mid + 1, right),fun2(list, left, mid));
    }

    public static ListNode fun1(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null) {
            return l1 != null ? l1 : l2;
        }
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy, ptr1 = l1, ptr2 = l2;
        while(ptr1 != null && ptr2 != null) {
            if(ptr1.val < ptr2.val) {
                tail.next = ptr1;
                ptr1 = ptr1.next;
            } else {
                tail.next = ptr2;
                ptr2 = ptr2.next;
            }
            tail = tail.next;
        }
        tail.next = (ptr1 != null) ? ptr1 : ptr2;
        return dummy.next;
    }

    public static ListNode buildList(String line) {
        if(line == null || line.trim().isEmpty()) return null;
        String[] parts = line.trim().split("\\s+");
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for(String p : parts) {
            curr.next = new ListNode(Integer.parseInt(p));
            curr = curr.next;
        }
        return dummy.next;
    }
}
