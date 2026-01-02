# 头文件

`import java.util.*;`
`import java.io.*;`

# 输入API

## 数组输入

```
Scanner sc = new Scanner(System.in)
Integer a = sc.nextInt();
Double b = sc.nextInt();
long c = sc.nextInt();
short d = sc.nextShort();
System.out.print(a + " " + b + " " + c + " " + d);
```

## 字符串输入

sc.next() 从缓存区接收字符遇到空格后停止。

sc.nextLine() 从缓存区接受字符，并且接收空格，遇到换行后才停止，并且会自动舍弃换行。

```
Scanner sc = new Scanner(System.in);
String str1 = sc.next();
String str2 = sc.next();
System.out.println(str1);
System.out.ptintln(str2);
```

```
>> abc ddd fgsfs ggg1 222
>> abc
>> ddd fgsfs ggg1 222 
```

# 输出API

输出有三种形式

System.out.print(); // 最后打印结果不会加换行

System.out.println(); // 最后打印结果换行

System.out.printf(); // 类似于C语言中printf

```
System.out.printf("%d", i);
System.out.print(nums[i], + " ");
System.out.println(str, " ");
```

# 字符串数组处理

## 提供字符串长度并且字符串之间以空格分割

**输入**

```
  Scanner sc = new Scanner(System.in);
  int n = sc.nextInt();
  String[] strs = new String[n];
  for(int i = 0; i < n; i++) {
     strs[i] = sc.next();
  }
```

**输出** ： 同组字符串输出以空格分割，不同组用换行分割

```
for (List<String> group : result) {
            for (int i = 0; i < group.size(); i++) {
                System.out.print(group.get(i));
                if (i < group.size() - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
```

## 不提供字符串数组长度

**输入：提供三种方式，采用trim()函数分割**

```

        // 读取输入
        Scanner scanner = new Scanner(System.in);
  
        // 读取字符串数组
        String line = scanner.nextLine().trim();
  
        // 解析输入，支持多种格式：
        // 1. ["eat","tea","tan","ate","nat","bat"]
        // 2. eat,tea,tan,ate,nat,bat
        // 3. eat tea tan ate nat bat
        String[] strs;
  
        if (line.startsWith("[") && line.endsWith("]")) {
            // 格式1: ["eat","tea","tan","ate","nat","bat"]
            line = line.substring(1, line.length() - 1);
            line = line.replaceAll("\"", ""); // 去除引号
            strs = line.split(",");
        } else if (line.contains(",")) {
            // 格式2: eat,tea,tan,ate,nat,bat
            strs = line.split(",");
        } else {
            // 格式3: eat tea tan ate nat bat
            strs = line.split(" ");
        }
```



**输入一串分割**

```java
// 处理输入：假设输入为两行，每行数字以空格分隔
        if (!sc.hasNextLine()) return;
        int[] preorder = parseArray(sc.nextLine());
        if (!sc.hasNextLine()) return;
        int[] inorder = parseArray(sc.nextLine());

	private static int[] parseArray(String line) {
        String[] parts = line.trim().split("\\s+");
        int[] res = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            res[i] = Integer.parseInt(parts[i]);
        }
        return res;
    }
```



**输出：**

```
        StringBuilder sb = new StringBuilder("[");
  
        for (int i = 0; i < result.size(); i++) {
            List<String> group = result.get(i);
            sb.append("[");
  
            for (int j = 0; j < group.size(); j++) {
                sb.append("\"").append(group.get(j)).append("\"");
                if (j < group.size() - 1) {
                    sb.append(",");
                }
            }
  
            sb.append("]");
            if (i < result.size() - 1) {
                sb.append(",");
            }
        }
  
        sb.append("]");
```





# 链表

## 构造ACM模式链表测试场景

```
public static void main(String[] args) {
        // ACM 模式通常需要处理输入，但在面试中，核心逻辑在于构造测试场景
        // 这里手动构造一个相交链表示例：
        // A: 1 -> 2 \
        //             6 -> 7
        // B: 3 -> 4 -> 5 /
        
        ListNode common = new ListNode(6);
        common.next = new ListNode(7);
        
        ListNode headA = new ListNode(1);
        headA.next = new ListNode(2);
        headA.next.next = common;
        
        ListNode headB = new ListNode(3);
        headB.next = new ListNode(4);
        headB.next.next = new ListNode(5);
        headB.next.next.next = common;
        
        ListNode result = getIntersectionNode(headA, headB);
}
```



## 提供的链表示例,构造链表

如果要提供两个相较链表，通常题目会这样描述：

> 第一行输入两个整数 n 和 m，分别代表链表 A 和 B 的长度。
>
> 第二行输入 n 个整数，代表链表 A 的节点值。
>
> 第三行输入 m 个整数，代表链表 B 的节点值。
>
> 第四行输入一个索引（或偏移量），表示从哪个位置开始相交。

这段代码展示了如何利用 `Scanner` 读取数据并**从零构建**出题目要求的链表结构。

```java
import java.util.Scanner;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Main {
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
        ListNode result = getIntersectionNode(headA, headB);
        
        // 6. 按照要求输出
        if (result != null) System.out.println(result.val);
        else System.out.println("null");
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

    // 核心算法：双指针
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }
        return pA;
    }
}
```



接收数据**构造链表**和**打印链表**一种比较简单的方法：

```java
Scanner sc = new Scanner(System.in);
int n = sc.nextInt();
ListNode dummy = new ListNode(0);// 用来当接收链表的头节点的前一个虚拟节点
ListNode tail = dummy;
for(int i = 0; i < n; i++) {
	tail.next = new ListNode(sc.nextInt());
	tail = tail.next;
}
```





```
import java.util.Scanner;

// 定义链表节点结构
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 1. 读取输入
        // 假设输入格式为：第一行 n 表示节点个数，第二行 n 个整数表示节点值
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        
        // 2. 构建链表 (使用尾插法和虚拟头节点)
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        for (int i = 0; i < n; i++) {
            tail.next = new ListNode(sc.nextInt());
            tail = tail.next;
        }
        
        // 3. 执行反转算法
        ListNode newHead = reverseList(dummy.next);
        
        // 4. 输出反转后的链表
        printList(newHead);
    }

    // 核心算法：迭代反转
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null; // 初始化前驱为 null
        ListNode curr = head;
        
        while (curr != null) {
            ListNode nextTemp = curr.next; // 1. 暂存后继节点
            curr.next = prev;             // 2. 翻转指针方向
            prev = curr;                  // 3. prev 后移
            curr = nextTemp;              // 4. curr 后移
        }
        return prev; // 最终 prev 指向原链表的末尾，即新链表的头
    }

    // 辅助方法：打印链表
    private static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + (curr.next != null ? " " : ""));
            curr = curr.next;
        }
        System.out.println();
    }
}
```



## 环形链表构造

### 1.链表元素以数组形式输入

```java
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
```



### 2.链表元素以字符串形式输入

`List<ListNode> nodes = new ArrayList<>();`

`node.get(nodes.size() - 1).next = nodes.get(pos);`

```java
	Scanner sc = new Scanner(System.in);

    // 处理输入：这里假设输入格式为两行
    // 第一行：链表节点的值（如：3 2 0 -4）
    // 第二行：pos (环连接到的索引，-1 表示无环)
    while (sc.hasNextLine()) {
        String line = sc.nextLine();
        if (line.isEmpty()) break;
            
        String[] vals = line.split(" ");
        int pos = Integer.parseInt(sc.nextLine());
        // 2. 构建链表
        ListNode head = buildLinkedListWithCycle(vals, pos);

	/**
     * 辅助方法：根据输入构建带环链表
     */
    private static ListNode buildLinkedListWithCycle(String[] vals, int pos) {
        if (vals.length == 0) return null;

        List<ListNode> nodes = new ArrayList<>();
        for (String val : vals) {
            nodes.add(new ListNode(Integer.parseInt(val)));
        }

        // 连接节点
        for (int i = 0; i < nodes.size() - 1; i++) {
            nodes.get(i).next = nodes.get(i + 1);
        }

        // 如果 pos != -1，将最后一个节点连到指定的索引位置
        if (pos != -1 && pos < nodes.size()) {
            nodes.get(nodes.size() - 1).next = nodes.get(pos);
        }

        return nodes.get(0);
    }
```



## 接收数字后再接收字符串会，换行符也会占用

**问题原因：** 当你使用 `sc.nextInt()` 读取第一个数字后，输入缓冲区中会留下一个换行符。然后 `sc.nextLine()` 会立即读取这个换行符，导致第一个链表读取为空字符串，因此第一个链表没有被正确处理。

### 解决方案

在读取第一个数字后，添加一个 `sc.nextLine()` 来消耗掉换行符：

```
public class mergeKLists {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine(); // 添加这行来消耗换行符
        
        ListNode[] list = new ListNode[n];
        for(int i = 0; i < n; i++) {
            String line = sc.nextLine();
            list[i] = buildList(line);
        }
        
        // ... 其余代码不变
    }
}
```

或者数字也按照字符串接收

```java
		int n = Integer.parseInt(sc.nextLine());

        ListNode[] list = new ListNode[n];
        for(int i = 0; i < n; i++) {
            String line = sc.nextLine();
            list[i] = buildList(line);
        }
```



## 接收多个链表-按照字符串接收处理

```
		int n = Integer.parseInt(sc.nextLine());

        ListNode[] list = new ListNode[n];
        for(int i = 0; i < n; i++) {
            String line = sc.nextLine();
            list[i] = buildList(line);
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
```



# 二叉树



```java
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

```



## 普通二叉树输入构造

- **`==` 和 `!=`**：比较对象引用，如果字符串不是同一个对象（即使内容相同），比较会失败
- **`.equals()`**：比较字符串内容，确保正确判断字符串是否为 "null"

```
		Scanner sc = new Scanner(System.in);

        String line = sc.nextLine();
        String[] parts = line.split("\\s+");

        TreeNode root = bulidTree(parts);
        
        public static TreeNode bulidTree(String[] parts) {
            if (parts.length == 0 || parts[0].equals("null")) return null;

            TreeNode root = new TreeNode(Integer.parseInt(parts[0]));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            int i = 1;
            while (!queue.isEmpty() && i < parts.length) {
                TreeNode node = queue.poll();

                // 处理左孩子
                if (i < parts.length && !parts[i].equals("null")) {
                    node.left = new TreeNode(Integer.parseInt(parts[i]));
                    queue.add(node.left);
                }
                i++;

                // 处理右孩子
                if (i < parts.length && !parts[i].equals("null")) {
                    node.right = new TreeNode(Integer.parseInt(parts[i]));
                    queue.add(node.right);
                }
                i++;
            }
            return root;
        }
```



## 二叉树遍历输出



```
/**
     * 层序打印（用于输出验证）
     */
    public static void printTree(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<String> res = new ArrayList<>();
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                res.add(String.valueOf(node.val));
                queue.add(node.left);
                queue.add(node.right);
            } else {
                res.add("null");
            }
        }
        
        // 移除末尾多余的 null 以符合常规格式
        int last = res.size() - 1;
        while (last >= 0 && res.get(last).equals("null")) {
            res.remove(last--);
        }
        System.out.println(String.join(" ", res));
    }
```

