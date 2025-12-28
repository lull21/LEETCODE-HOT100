package list;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author 2hang1iang
 * @Date 2025/12/27 10:08
 * @Description 138.随机链表的复制 中等
 * 给你一个长度为n的链表，每个节点包含一个额外增加的随机指针random，该指针可以指向链表中的任何节点或空节点。
 * 构造这个链表的【深拷贝】。深拷贝应该正好由n个全新节点组成，其中每个新节点的值都设为其对应的原节点的值。
 * 新节点的next指针和random指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。
 * 复制链表中的指针都不应指向原链表中的节点 。
 * 输入：
 * 5
 * 7 -1 13 0 11 4 10 2 1 0
 * 输出：
 * 7 -1 13 0 11 4 10 2 1 0
 * 题解：
 * 方法一：采用HashMap
 * 原链表和新链表用Map存储映射
 * 方法二：
 * 这种方法不需要额外的哈希表，逻辑非常精妙：
 * 1.复制节点并插入：在每个原始节点后面立即插入它的克隆节点。A-B-C 变成 A-A‘-B-B'-C-C'。
 * 2.设置克隆节点的 random：克隆节点 A' 的 random 应该指向原节点 A 的 random 的后继节点（即 A.random.next）。
 * 3.拆分链表：将交织在一起的链表拆开，恢复原链表，并提取出克隆链表。
 **/

public class copyRandomList {
    static class Node{
        int val;
        Node next;
        Node random;
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        // 1. 先创建所有节点并存入数组，方便后续根据索引找节点
        Node[] nodes = new Node[n];
        int[][] inputData = new int[n][2]; // 暂存输入：[val, random_index]

        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            int randomIndex = sc.nextInt();
            nodes[i] = new Node(val);
            inputData[i][0] = val;
            inputData[i][1] = randomIndex;
        }

        // 2. 串联 next 和 random 指针
        for (int i = 0; i < n; i++) {
            if (i < n - 1) nodes[i].next = nodes[i + 1];

            int randomIndex = inputData[i][1];
            if (randomIndex != -1) {
                nodes[i].random = nodes[randomIndex]; // 指向已存在的节点
            }
        }

        Node res = fun1(nodes[0]);
//        Node res = fun2(dummy.next);

        while(res != null) {
            int rVal = (res.random != null) ? res.random.val : -1;
            System.out.print(res.val + " " + rVal + ((res.next != null) ? " " : ""));
            res = res.next;
        }
    }

    public static Node fun1(Node head) {
        if(head == null) return null;

        Map<Node, Node> map = new HashMap<>();
        Node curr = head;

        while(curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }

        curr = head;
        while(curr != null) {
            map.get(curr).next = map.get(curr.next); //括号内外都一样
            map.get(curr).random = map.get(curr.random);
            curr = curr.next;
        }
        return map.get(head);
    }

    public static Node fun2(Node head) {
        if (head == null) return null;

        // 第一步：复制节点并交织 A -> A' -> B -> B'
        Node curr = head;
        while (curr != null) {
            Node newNode = new Node(curr.val);
            newNode.next = curr.next;
            curr.next = newNode;
            curr = newNode.next;
        }

        // 第二步：设置克隆节点的 random 指针
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                // curr.next 是克隆节点，curr.random.next 是 random 指向节点的克隆
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }

        // 第三步：拆分链表
        curr = head;
        Node dummy = new Node(0);
        Node res = dummy;
        while (curr != null) {
            // 提取克隆节点
            res.next = curr.next;
            res = res.next;

            // 恢复原链表
            curr.next = curr.next.next;
            curr = curr.next;
        }

        return dummy.next;
    }
}
