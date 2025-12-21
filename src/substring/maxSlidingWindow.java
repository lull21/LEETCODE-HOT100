package substring;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2025/12/20 18:40
 * @Description *239.滑动窗口最大值-困难
 * 一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回 滑动窗口中的最大值 。
 * 输入：
 * 8
 * 1 3 -1 -3 5 3 6 7
 * 3
 * 输出：
 * 3 3 5 5 6 7
 * 题解：
 * 双端队列 - ArrayDeque，维护一个单调递减的队列。
 * 双端队列存储元素【下标】
 * 1.新人入场：每当一个新数字进入窗口，它会把队列里所有比它小的老数字全部“踢走”（因为有了更强、更年轻的新数字，老的小数字永远不可能成为之后的最大值了）。
 * 2.老人退场：随着窗口移动，如果队首的那个“最强王者”已经超出了窗口范围，就把它从队首踢出。
 * 3.取最大值：经过上述两步，队首永远是当前窗口内最大的那个数。
 * 尾部去小、头部去过期
 * 记住ArrayDeque的各种方法，peek,poll,offer
 **/

public class maxSlidingWindow {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int k = sc.nextInt();

        int[] res = new int[n-k+1];
        int resIdx = 0;

        // 双端队列，存储的是元素的【下标】
        Deque<Integer> deque = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            // 1. 维护“老人退场”：如果队首下标已经不在窗口内了，移除它
            // i - k + 1表示当前i为终点的窗口的起点坐标位置。
            // 头部元素不在窗口范围就移除
            if(!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // 2. 维护“优胜劣汰”：只要当前数比队尾的大，队尾就没用了，直接踢走
            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // 3. 将当前数下标加入队尾
            deque.offerLast(i);

            // 4. 当【窗口形成】后（i >= k-1），队首就是最大值
            if(i - k + 1 >= 0) {
                res[resIdx++] = nums[deque.peekFirst()];
                System.out.print(nums[deque.peekFirst()] + " ");
            }
        }
    }

}
