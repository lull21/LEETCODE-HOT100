package heap;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/12 16:15
 * @Description 215. 数组中的第K个最大元素 【中等】
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 * 题解：最小堆
 * 因为堆移除元素时，只能移除堆顶元素，默认为最小堆，遇到求最大值/最多问题时，常用最小堆
 * 所以这里用最小堆，维护k个元素，堆顶就是第k个最大元素
 **/

public class findKthLargest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int k = sc.nextInt();

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for(int num : nums){
            heap.offer(num);
            if(heap.size() > k){
                heap.poll();
            }
        }

        System.out.println(heap.peek());
    }
}
