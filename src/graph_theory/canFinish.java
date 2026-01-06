package graph_theory;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/03 15:16
 * @Description 207. 课程表 【中等】
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 题解：检测有向图中是否存在环,先找没要求的课上，上完之后，看哪些课的要求被满足了，再接着上。
 * 拓扑排序 (Topological Sort)，通常使用 Kahn 算法（广度优先搜索 BFS）。
 * 1. 关键概念：入度 (In-degree)
 *  入度：指向该节点的边的数量。在本项目中，入度代表该课程还有多少门先修课没上。
 *  如果一门课的入度为 $0$，说明它没有先修课，或者先修课已经全部修完了，现在可以立即开始修这门课。
 *  2. 算法步骤
 * 2.1建图：统计每个节点的入度，并用邻接表存储图的结构。
 * 2.2找起点：将所有入度为 0 的节点放入队列（这些课可以直接上）。
 * 2.3处理队列：
 *   从队列中弹出一门课。
 *   计数器加 1（表示完成了一门课）。
 *   遍历这门课的所有“后续课程”，将它们的入度减 1。
 *   如果某个后续课程的入度减到了 0，说明它的先修要求已满足，将其加入队列。
 * 2.4判断结果：最后看完成的课程数是否等于总课程数。如果相等，说明无环，返回 true；否则返回 false。
 * 1. 先遍历一遍先修课程对，将所有课程的先修课程加入【邻接表】。
 * 2. 然后从所有课程中选择那些【没有先修课程的课程】，加入队列。
 * 3. 然后从队列中取出课程，将其【所有的后续课程的先修课程数】减一。
 * 4. 如果后续课程的先修课程数为0，则将其加入队列。
 * 5. 重复步骤3和4，直到队列为空。
 * 6. 如果所有课程都被加入了队列，则返回true；否则返回false。
 **/

public class canFinish {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numCourses = sc.nextInt();
        int[][] prerequisites = new int[numCourses][2];
        for (int i = 0; i < numCourses; i++) {
            prerequisites[i][0] = sc.nextInt();
            prerequisites[i][1] = sc.nextInt();
        }

        // 1. 准备数据结构
        int[] indegree = new int[numCourses]; // 入度表
        List<List<Integer>> adj = new ArrayList<>(); // 邻接表
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        // 2. 建图：计算入度和填充邻接表
        for (int[] pair : prerequisites) {
            int course = pair[0];
            int pre = pair[1];
            indegree[course]++;
            adj.get(pre).add(course); // pre -> course
        }

        // 3. 将所有入度为 0 的节点入队
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 4. BFS 过程
        int count = 0; // 统计学完的课程数
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            count++;

            // 遍历当前课程的所有后续课程
            for (int neighbor : adj.get(curr)) {
                indegree[neighbor]--; // 先修课学完，入度减 1
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // 5. 如果学完的课程数等于总数，说明没有环
        System.out.println(count == numCourses);
    }
}
