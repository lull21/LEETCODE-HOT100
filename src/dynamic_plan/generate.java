package dynamic_plan;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/14 10:48
 * @Description 118. 杨辉三角 【简单】
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * 题解：
 * 3. 对于每一行，第一个元素和最后一个元素都是 1，其他元素是上一行对应位置的元素和上一行对应位置的前一个元素的和。
 **/

public class generate {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numRows = sc.nextInt();
        List<List<Integer>> res = new ArrayList<>();
        res.add(Arrays.asList(1));
        for(int i = 1; i < numRows; i++) {
            List<Integer> path = new ArrayList<>();
            for(int j = 0; j <= i; j++) {
                if(j == 0 || j == i) {
                    path.add(1);
                } else {
                    path.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                }
            }
            res.add(path);
        }
        System.out.println(res);
    }
}
