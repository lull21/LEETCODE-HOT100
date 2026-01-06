package graph_theory;

import java.util.Scanner;

/**
 * @Author 2hang1iang
 * @Date 2026/01/03 15:16
 * @Description 208. 实现 Trie (前缀树) 【中等】
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 * 示例:
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 想象一下，如果你要在一堆单词里找某个前缀，最笨的方法是遍历所有单词。
 * 而前缀树的核心思想是：利用字符串的公共前缀来减少查询时间，最大限度地减少无谓的字符串比较。
 * 1. 节点设计 (TrieNode)
 * 前缀树的每一个节点并不直接存储字符，而是存储对下一个字符的引用（指针）。
 * Children (子节点数组)：大小通常为 26（代表 'a' 到 'z'）。children[0] 不为空表示有指向 'a' 的路径。
 * isEnd (结束标记)：一个布尔值，用来标记“到这个节点为止，是否构成了一个完整的单词”。
 * 2. 三大基本操作逻辑
 * 插入 (insert)：从根节点出发，遍历单词的每个字符。如果子节点不存在就新建，存在就跳过去。最后在末尾节点标记 isEnd = true。
 * 查询 (search)：同样从根节点出发。如果路径中某个字符找不到，返回 false；如果找完了，必须检查最后一个节点的 isEnd 是否为 true。
 * 前缀查询 (startsWith)：逻辑与查询一致，但最后不需要检查 isEnd。只要路径存在，就说明存在该前缀。
 *
 **/

// 定义前缀树节点
class TrieNode {
    TrieNode[] children;
    boolean isEnd;

    public TrieNode() {
        // 假设只包含小写字母 a-z
        children = new TrieNode[26];
        isEnd = false;
    }
}

public class TrieMain {
    private static TrieNode root = new TrieNode();

    // 1. 插入单词
    public static void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    // 2. 查找单词（必须完全匹配）
    public static boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    // 3. 查找前缀
    public static boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    // 辅助方法：沿着路径走，返回最后的节点
    private static TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }

    // ACM 模式主入口
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 假设输入格式：第一行操作数 n，后续 n 行操作（指令 参数）
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            String op = sc.next();
            String arg = sc.next();

            switch (op) {
                case "insert":
                    insert(arg);
                    System.out.println("null");
                    break;
                case "search":
                    System.out.println(search(arg));
                    break;
                case "startsWith":
                    System.out.println(startsWith(arg));
                    break;
            }
        }
        sc.close();
    }

}
