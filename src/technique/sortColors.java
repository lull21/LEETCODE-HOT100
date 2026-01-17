package technique;

import java.util.Scanner;

/**
 * @Author 2hang1iang
 * @Date 2026/01/17 11:12
 * @Description 75. 颜色分类 【中等】0，1，2排序，荷兰国旗问题
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 必须在不使用库的sort函数的情况下解决这个问题。
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * 题解：【三个指针 while循环（cur<=right） cur指针在0或1时++，left指针在0时++，right指针在2时--】
 * 1. 初始化： 左指针 left = 0 ，右指针 right = n-1 ，当前指针 cur = 0；
 * 2. 循环： 当 cur <= right 时，执行循环；
 * 3. 当 nums[cur] == 0 时，交换 nums[left] 和 nums[cur] ，并将 left 和 cur 都加 1 ；
 * 4. 当 nums[cur] == 2 时，交换 nums[right] 和 nums[cur] ，并将 right 减 1 ；
 * 5. 当 nums[cur] == 1 时，将 cur 加 1 ；
 * 6. 返回值： 返回排序后的数组 nums ；
 **/

public class sortColors {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int left = 0, right = n-1, cur = 0;
        while(cur <= right) {
            if(nums[cur] == 0) {
                int temp = nums[left];
                nums[left] = nums[cur];
                nums[cur] = temp;
                left++;
                cur++;
            } else if(nums[cur] == 2) {
                int temp = nums[right];
                nums[right] = nums[cur];
                nums[cur] = temp;
                right--;
            } else {
                cur++;
            }
        }
        for(int i = 0; i < n; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}

