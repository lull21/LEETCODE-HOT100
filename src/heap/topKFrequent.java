package heap;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/12 16:46
 * @Description 347. 前 K 个高频元素 【中等】
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 题解：最小堆 + HashMap
 * Map.Entry<Integer, Integer> 表示哈希表中的一个键值对 多用来遍历哈希表
 **/

public class topKFrequent {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int k = sc.nextInt();

        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 最小堆，按照键值对中的值（频率）升序排序
        // Map.Entry<,> 表示哈希表中的一个键值对 多用来遍历哈希表
        // for(Map.Entry<Integer, Integer> entry : map.entrySet()){
        //     System.out.println(entry.getKey() + " " + entry.getValue());
        // }
        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(
                (a, b) -> a.getValue() - b.getValue()
//        (a, b) -> b.getValue() - a.getValue()  // 按照哈希表中【值】降序排列
        );
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            heap.offer(entry);
            if(heap.size() > k){
                heap.poll();
            }
        }

        int[] res = new int[k];
        for(int i = 0; i < k; i++){
            res[i] = heap.poll().getKey();
        }
        System.out.println(Arrays.toString(res));
        }
}
