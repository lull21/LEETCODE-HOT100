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



## Map.Entry

### 1. Map.Entry 是什么？

`Map.Entry`是Java中Map接口的一个内部接口，它表示Map中的一个键值对（key-value pair）。每个Map.Entry对象包含：

- 一个键（key）
- 一个值（value）

### 2. 泛型参数的含义

`Map.Entry<Integer, Integer>`中的两个`Integer`是泛型参数：

- **第一个`Integer`**：键（key）的类型
- **第二个`Integer`**：值（value）的类型

### 3. 为什么这样设计？

场景：统计元素频率

在`topKFrequent.java`中，这种设计非常适合**统计元素出现频率**的问题：

```
// 示例：统计数组中每个数字出现的频率
int[] nums = {1, 1, 1, 2, 2, 3};
Map<Integer, Integer> frequencyMap = new HashMap<>();

for (int num : nums) {
    frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
}

// 此时frequencyMap的内容：
// key=1, value=3  (数字1出现3次)
// key=2, value=2  (数字2出现2次)  
// key=3, value=1  (数字3出现1次)
```

### 4. 实际应用示例

```
// 创建频率统计Map
Map<Integer, Integer> freqMap = new HashMap<>();
freqMap.put(1, 3);  // 数字1出现3次
freqMap.put(2, 2);  // 数字2出现2次
freqMap.put(3, 1);  // 数字3出现1次

// 使用Map.Entry来遍历键值对
for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
    int number = entry.getKey();     // 获取数字
    int frequency = entry.getValue(); // 获取出现次数
    System.out.println("数字 " + number + " 出现了 " + frequency + " 次");
}
```

### 5. 在优先队列中的应用

在`topKFrequent.java`中，使用`Map.Entry<Integer, Integer>`的原因：

```
PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(
    (a, b) -> a.getValue() - b.getValue()
);

// 这样我们可以同时访问数字和它的频率：
for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
    heap.offer(entry);
    if (heap.size() > k) {
        heap.poll(); // 移除频率最小的元素
    }
}

// 最终堆中保留的是频率最大的K个数字及其频率
```

### 6. 替代方案对比

如果不使用`Map.Entry`，我们需要其他方式：

**方案1：使用两个数组**

```
int[] numbers = new int[n];
int[] frequencies = new int[n];
// 需要手动维护对应关系，容易出错
```

**方案2：自定义类**

```
class NumberFrequency {
    int number;
    int frequency;
    // 需要额外定义类和方法
}
```

**方案3：使用Map.Entry（推荐）**

- ✅ 内置Java类，无需额外定义
- ✅ 类型安全，泛型保证
- ✅ 提供标准的getKey()/getValue()方法
- ✅ 与Map完美配合

### 7. 其他可能的类型组合

根据不同的应用场景，`Map.Entry`可以有各种类型组合：

```
// 字符串频率统计
Map.Entry<String, Integer>  // key:字符串, value:频率

// 学生成绩统计  
Map.Entry<String, Double>   // key:学生姓名, value:成绩

// 单词计数
Map.Entry<Character, Integer> // key:字符, value:出现次数
```

### 8. 总结

`Map.Entry<Integer, Integer>`这样设计是因为：

1. **语义清晰**：明确表示"整数-整数"的键值对关系
2. **类型安全**：泛型确保编译时类型检查
3. **高效便捷**：内置方法直接操作键值对
4. **标准规范**：符合Java集合框架的设计模式
5. **适用场景**：完美匹配频率统计类问题的需求

这种设计让代码既简洁又类型安全，是处理键值对数据的标准方式。