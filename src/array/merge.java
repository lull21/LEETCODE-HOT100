package array;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2025/12/21 16:58
 * @Description 56.合并区间-中等
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * 输入：
 * 5
 * 4 2
 * 1 3
 * 2 6
 * 8 10
 * 15 18
 * 输出：
 * 1 6
 * 8 10
 * 15 18
 * 题解：排序 (Sorting) 和 贪心 (Greedy)。
 * 如果区间是乱序的，我们很难判断哪些区间可以合并。
 * 因此，第一步必须是按照区间的起始位置（start）进行升序排序。
 * 关键逻辑：
 * 排序后，我们只需要关心当前区间和下一个区间的关系：
 * 1. 有重叠：如果“下一个区间”的开头 ≤ “当前已合并区间”的结尾。
 *  做法：合并。更新“当前已合并区间”的结尾为两者的最大值（取 max）。
 * 2. 没重叠：如果“下一个区间”的开头 > “当前已合并区间”的结尾。
 *  做法：无法合并。将“当前区间”存入结果，并把“下一个区间”作为新的基准继续比较。
 **/

public class merge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] intervals = new int[n][2];
        for(int i = 0; i < n; i++) {
            intervals[i][0] = sc.nextInt();
            intervals[i][1] = sc.nextInt();
        }

        // 2. 核心步骤：按照每个区间的 start 升序排序
        // 记忆点：没有排序，后续的“一次遍历”逻辑就不成立
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));

        List<int[]> list = new ArrayList<>();

        int leftEdge = intervals[0][0];
        int rightEdge = intervals[0][1];

        for(int i = 1; i < n; i++) {
            if(intervals[i][0] > rightEdge) {
                list.add(new int[]{leftEdge, rightEdge});
                leftEdge = intervals[i][0];
                rightEdge = intervals[i][1];
            } else {
                rightEdge = Math.max(rightEdge, intervals[i][1]);
            }
        }
        list.add(new int[]{leftEdge, rightEdge});

        int[][] res = list.toArray(new int[list.size()][]);

        System.out.println(res);

    }
}
