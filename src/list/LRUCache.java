package list;

import java.util.*;

/**
 * @Author 2hang1iang
 * @Date 2025/12/28 09:26
 * @Description 146.LRU 缓存 【中等】
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。
 * 如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * 输入：
 * 2
 * 6
 * put 1 1
 * put 2 2
 * get 1
 * put 3 3
 * get 2
 * put 4 4
 * 输出：
 * null
 * null
 * 1
 * null
 * -1
 * null
 * 题解：双向链表+哈希表
 * 操作逻辑：
 * 1.get(key)：
 *   如果 Hash 中不存在，返回 -1。
 *   如果存在，通过 Hash 找到节点，将其移动到链表头部，返回其值。
 * 2.put(key, value)：
 *   如果 Key 已存在，更新值，并将其移动到链表头部。
 *   如果 Key 不存在：
 *     若达到容量上限，删除链表尾部节点，并同步从 Hash 中移除。
 *     创建新节点，插入到链表头部，并存入 Hash。
 **/
public class LRUCache {
    static class Node {
        int key, value;
        Node prev, next;
        Node() {}
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, Node> cache = new HashMap<>();
    private int size;
    private int capacity;
    private Node head, tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 使用伪头部和伪尾部节点，简化插入和删除逻辑
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) return -1;
        // 如果存在，先移动到头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if(node == null) {
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            size++;
            if (size > capacity) {
                // 如果超出容量，删除尾部
                Node tail = removeTail();
                cache.remove(tail.key);
                size--;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    // --- 内部辅助方法：链表操作的四大金刚 ---

    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private Node removeTail() {
        Node res = tail.prev;
        removeNode(res);
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 示例输入处理
        // 第一行输入容量，第二行输入操作指令总数
        if (!sc.hasNextInt()) return;
        int capacity = sc.nextInt();
        LRUCache lru = new LRUCache(capacity);

        int ops = sc.nextInt();
        for (int i = 0; i < ops; i++) {
            String op = sc.next();
            if (op.equals("put")) {
                int key = sc.nextInt();
                int val = sc.nextInt();
                lru.put(key, val);
                System.out.println("null");
            } else if (op.equals("get")) {
                int key = sc.nextInt();
                System.out.println(lru.get(key));
            }
        }
    }

}
