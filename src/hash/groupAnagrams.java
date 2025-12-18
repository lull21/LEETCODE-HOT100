package hash;

import java.util.*;

/**
 * @Author 2hang1iang
 * @Date 2025/12/16 22:17
 * @Description 49.字母异位词分组
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 输入：
 * 6
 * eat tea tan ate nat bat
 * 输出：
 * bat
 * nat tan
 * ate eat tea
 * 题解：
 * 用HashMap,Map<String, List<String>>。
 * 其中map的key值，将字符串转换为字符数组，然后字符数组重排序得到唯一键。
 * map中各个key对应的的value值
 *  Arrays.sort(chars);
 *  String key = new String(chars); // 获得唯一键
 *  List<String> list = map.getOrDefault(key, new ArrayList<String>());
 *  list.add(s);
 *  map.put(key, list);
 **/

public class groupAnagrams {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] strs = new String[n];
        for(int i = 0; i < n; i++) {
            strs[i] = sc.next();
        }
        Map<String, List<String>> map = new HashMap<>();
        for(String s : strs) {
            // 字符串数组化
            char[] chars = s.toCharArray();
            // 数组排序
            Arrays.sort(chars);
            String key = new String(chars); // 获得唯一键
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(s);
            map.put(key, list);
        }
        List<List<String>> res = new ArrayList<>();
        for(List<String> list : map.values()) {
            res.add(list);
        }
//        System.out.println(res); // 方法一输出
        // 方法二输出
        for (List<String> group : res) {
            for (int i = 0; i < group.size(); i++) {
                System.out.print(group.get(i));
                if (i < group.size() - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
// 6
//eat tea tan ate nat bat

//[[eat, tea, ate], [bat], [tan, nat]] System.out.println(res);

//eat tea ate 方法二输出49
//bat
//tan nat