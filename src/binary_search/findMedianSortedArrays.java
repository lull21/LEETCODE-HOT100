package binary_search;
import java.util.Scanner;
/**
 * @Author 2hang1iang
 * @Date 2026/01/09 14:39
 * @Description 4. 寻找两个正序数组的中位数 【困难】
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 题解； 中位数本质上就是寻找第 k 小的数。
 * 1.为什么可以排除元素？
 * 假设我们要找第k小的元素，比较 nums1[k/2-1] 和 nums2[k/2-1]：
 * (1)如果 nums1[k/2-1] <= nums2[k/2-1]：
 *  在合并排序的数组中，nums1[k/2-1] 最多比 (k/2-1) + (k/2-1) = k-2 个元素大
 *  所以 nums1[k/2-1] 不可能是第k小的元素，最多是第k-1小
 *  因此可以安全排除 nums1 的前 k/2 个元素
 * (2)对称情况：如果 nums2[k/2-1] 更小，则排除 nums2 的前 k/2 个元素
 * 2.处理数组长度不足的情况
 *  当某个数组剩余长度小于 k/2 时，我们取该数组的最后一个元素进行比较。
 *  如果这个值较小，那么该数组的所有剩余元素都可以被排除，因为即使加上另一个数组的前 k/2-1 个元素，总数也不到k个。
 **/

public class findMedianSortedArrays {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int[] nums1 = new int[m];
        for (int i = 0; i < m; i++) {
            nums1[i] = sc.nextInt();
        }
        int n = sc.nextInt();
        int[] nums2 = new int[n];
        for (int i = 0; i < n; i++) {
            nums2[i] = sc.nextInt();
        }

        double res = findFun(nums1, nums2);
        System.out.println(res);
    }

    // 中位数实际上是合并后数组的第 k 小元素（或两个第k小元素的平均）
    private static double findFun(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int total = m + n;

        if (total % 2 == 1) {
            // 奇数：返回第 (total+1)/2 个元素
            return findKth(nums1, nums2, (total + 1) / 2);
        } else {
            // 偶数：返回第 total/2 和 total/2+1 个元素的平均值
            int left = findKth(nums1, nums2, total / 2);
            int right = findKth(nums1, nums2, total / 2 + 1);
            return (left + right) / 2.0;
        }
    }
    private static int findKth(int[] nums1, int[] nums2, int k) {
        int start1 = 0, start2 = 0; // 当前查找的起始位置

        while (true) {
            // 边界情况1：nums1已全部排除
            if (start1 == nums1.length) {
                return nums2[start2 + k - 1];
            }
            // 边界情况2：nums2已全部排除
            if (start2 == nums2.length) {
                return nums1[start1 + k - 1];
            }
            // 边界情况3：k=1，返回当前最小的元素
            if (k == 1) {
                return Math.min(nums1[start1], nums2[start2]);
            }

            // 正常情况：比较两个数组的第k/2个元素
            // 注意：如果数组剩余长度不足k/2，则取最后一个元素
            int step1 = Math.min(k / 2, nums1.length - start1);
            int step2 = Math.min(k / 2, nums2.length - start2);

            int val1 = nums1[start1 + step1 - 1];
            int val2 = nums2[start2 + step2 - 1];

            // 关键步骤：排除较小的那一部分
            if (val1 <= val2) {
                // 排除nums1的前step1个元素
                start1 += step1;
                k -= step1;
            } else {
                // 排除nums2的前step2个元素
                start2 += step2;
                k -= step2;
            }
        }
    }
}
