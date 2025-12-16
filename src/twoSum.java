import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
// 1. 两数之和
public class twoSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int target = sc.nextInt();
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            if(map.containsKey(target - nums[i])) {
                System.out.println(map.get(target - nums[i]) + " " + i);
            }
            map.put(nums[i], i);
        }
    }
}