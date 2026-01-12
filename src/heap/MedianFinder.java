package heap;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/12 17:34
 * @Description 295. 数据流的中位数 【困难】
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * 例如，
 * [2,3,4] 的中位数是 3
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * 设计一个支持以下两种操作的数据结构：
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 输入:
 * ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
 * [[],[1],[2],[],[3],[]]
 * 输出:
 * [null,null,null,1.50000,null,2.00000]
 * 题解：【最小堆 + 最大堆】 （最大堆的元素数≥最小堆的元素数，且最多一个） 大堆存小数，
 * 最大堆：存储数据流中较小的一半元素，堆顶元素为最大元素
 * 最小堆：存储数据流中较大的一半元素，堆顶元素为最小元素
 * 当数据流中元素个数为奇数时，最大堆的元素个数比最小堆多一个，中位数为最大堆的堆顶元素
 * 当数据流中元素个数为偶数时，最大堆和最小堆的元素个数相等，中位数为最大堆的堆顶元素和最小堆的堆顶元素的平均值
 **/

public class MedianFinder {
    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;
    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a, b) -> b - a); // 最大堆
    }
    // 加入时，【全部先加入最大堆】，再将最大堆的堆顶元素加入最小堆
    // 加入后，若最大堆的元素数小于最小堆的元素数，将最小堆的堆顶元素加入最大堆
    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }
    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }
}
