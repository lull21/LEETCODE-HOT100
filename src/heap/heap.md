## 1. Java中堆的实现方式

### 主要类：

- **PriorityQueue**（最常用）
- TreeSet/TreeMap（也可实现堆的部分功能）

### 基本用法：

java

```
// 最小堆（默认）
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// 最大堆（使用Comparator反转顺序）
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
// 或者
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

// 自定义比较器
PriorityQueue<int[]> heap = new PriorityQueue<>(
    (a, b) -> a[0] - b[0]  // 按第一个元素升序（最小堆）
);
```



### 常用方法：

java

```
heap.offer(element);    // 添加元素，O(log n)
heap.poll();            // 移除并返回堆顶元素，O(log n)
heap.peek();            // 查看堆顶元素，O(1)
heap.size();            // 获取元素个数
heap.isEmpty();         // 判断是否为空
```



## 2. 堆的特性

### 时间复杂度：

- **插入**：O(log n)
- **删除堆顶**：O(log n)
- **获取堆顶**：O(1)
- **构建堆**：O(n)

### 特性总结：

1. **完全二叉树**结构，通常用数组实现
2. **堆序性质**：父节点值 ≥（或 ≤）子节点值
3. **内存布局紧凑**，访问效率高
4. **不支持快速查找**任意元素（需要O(n)）
5. **非稳定排序**：相等元素的顺序可能改变

### 堆的类型：

- **最大堆**：父节点 ≥ 子节点，堆顶为最大值
- **最小堆**：父节点 ≤ 子节点，堆顶为最小值

## 3. 适用题目类型

### 高频应用场景：

#### 1. **Top K 问题**

java

```
// 前K个最大元素 - 使用最小堆保持K个元素
public int[] topK(int[] nums, int k) {
    PriorityQueue<Integer> heap = new PriorityQueue<>();
    for (int num : nums) {
        heap.offer(num);
        if (heap.size() > k) {
            heap.poll();
        }
    }
    return heap.stream().mapToInt(i -> i).toArray();
}
```



#### 2. **流数据的中位数**

- 用两个堆：最大堆存较小一半，最小堆存较大一半

#### 3. **区间合并/调度**

- 会议室II、合并区间等

#### 4. **Dijkstra最短路径**

- 优先队列优化BFS

#### 5. **多路归并**

- 合并K个排序链表/数组

### 典型题目：

| 题型     | 例题                | 堆的作用       |
| :------- | :------------------ | :------------- |
| Top K    | #215 数组第K大元素  | 维护K大小的堆  |
| 调度     | #253 会议室II       | 按结束时间排序 |
| 中位数   | #295 数据流中位数   | 双堆平衡       |
| 链表合并 | #23 合并K个升序链表 | 多路归并       |
| 贪心     | #630 课程表III      | 按截止时间处理 |

## 4. 使用技巧

### 选择最大堆还是最小堆？

- 找 **最大/最多** → 通常用 **最小堆**（保持K个元素时淘汰小的）
- 找 **最小/最少** → 通常用 **最大堆**（保持K个元素时淘汰大的）

### 处理对象数组：

java

```
// 存储[index, value]对，按value排序
PriorityQueue<int[]> heap = new PriorityQueue<>(
    (a, b) -> a[1] - b[1]  // 按value最小堆
);

// 存储自定义对象
class Node {
    int val;
    int index;
}
PriorityQueue<Node> heap = new PriorityQueue<>(
    (a, b) -> a.val - b.val
);
```



### 内存优化：

当堆很大时，考虑：

1. 只存储必要信息
2. 使用基本类型而非包装类
3. 及时清理已处理元素

## 总结

堆在算法题中常用于需要**动态维护极值**的场景，特别是当数据流式到达或需要实时更新时。Java的`PriorityQueue`使用方便，但要注意它不支持直接修改堆内元素值，通常需要先删除再插入。



## HashMap-Heap



```java
PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(
按键升序	(a, b) -> a.getKey() - b.getKey()	堆顶是最小的键
按键降序	(a, b) -> b.getKey() - a.getKey()	堆顶是最大的键
按值升序	(a, b) -> a.getValue() - b.getValue()	堆顶是最小的值
按值降序	(a, b) -> b.getValue() - a.getValue()	堆顶是最大的值
);
```

哈希表的遍历：

```
 Map.Entry<,> 表示哈希表中的一个键值对 多用来遍历哈希表
 for(Map.Entry<Integer, Integer> entry : map.entrySet()){
     System.out.println(entry.getKey() + " " + entry.getValue());
 }
```

