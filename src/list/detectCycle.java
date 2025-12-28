package list;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2025/12/25 09:06
 * @Description 142. 环形链表 II 中等
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 不允许修改链表。
 * 输入：
 * 3 2 0 -4
 * 1
 * 输出：2
 * 题解：快慢指针+起点和相遇点同时运动，再次相遇点即为入口点
 * 步骤一：判断是否有环，并【找到相遇点】并记录下来meetNode
 * 步骤二：寻找入口点
 *  一个从头出发，一个从相遇点出发，步长都为 1
 **/

public class detectCycle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 输入链表为字符串形式
        String line = sc.nextLine();

        String[] vals = line.split(" ");
        int pos = sc.nextInt();
        // 构造链表
        ListNode head = buildList(vals, pos);

        // 寻找入口
        ListNode entrance = fun(head);
        if(entrance == null) {
            System.out.println("no cycle");
        } else {
            System.out.println("entrance.val = " + entrance.val);
        }

    }

    public static ListNode fun(ListNode head) {
        // 步骤一：查看是否有环
        ListNode slow = head;
        ListNode fast = head;

        ListNode meetNode = null;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast) {
                meetNode = slow;
                break;
            }
        }

        // 如果没有相遇点，说明无环
        if (meetNode == null) return null;

        // 步骤二：寻找入口点
        // 一个从头出发，一个从相遇点出发，步长都为 1
        ListNode ptr1 = head;
        ListNode ptr2 = meetNode;

        while(ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        return ptr1;
    }

    public static ListNode buildList(String[] vals, int pos) {
        if(vals.length == 0) return null;
        List<ListNode> nodes = new ArrayList<>();
        for(String v : vals) {
            nodes.add(new ListNode(Integer.parseInt(v)));
        }
        for(int i = 0; i < nodes.size() - 1; i++) {
            nodes.get(i).next = nodes.get(i + 1);
        }
        if(pos != -1 && pos < nodes.size()) {
            nodes.get(nodes.size() - 1).next = nodes.get(pos);
        }
        return nodes.get(0);
    }
}
