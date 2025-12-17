# 头文件

`import java.util.*;`
`import java.io.*;`

# 输入API

## 数组输入

```
Scanner sc = new Scanner(System.in)
Integer a = sc.nextInt();
Double b = sc.nextInt();
long c = sc.nextInt();
short d = sc.nextShort();
System.out.print(a + " " + b + " " + c + " " + d);
```

## 字符串输入

sc.next() 从缓存区接收字符遇到空格后停止。

sc.nextLine() 从缓存区接受字符，并且接收空格，遇到换行后才停止，并且会自动舍弃换行。

```
Scanner sc = new Scanner(System.in);
String str1 = sc.next();
String str2 = sc.next();
System.out.println(str1);
System.out.ptintln(str2);
```

```
>> abc ddd fgsfs ggg1 222
>> abc
>> ddd fgsfs ggg1 222 
```

# 输出API

输出有三种形式

System.out.print(); // 最后打印结果不会加换行

System.out.println(); // 最后打印结果换行

System.out.printf(); // 类似于C语言中printf

```
System.out.printf("%d", i);
System.out.print(nums[i], + " ");
System.out.println(str, " ");
```

# 字符串数组处理

## 提供字符串长度并且字符串之间以空格分割

**输入**

```
  Scanner sc = new Scanner(System.in);
  int n = sc.nextInt();
  String[] strs = new String[n];
  for(int i = 0; i < n; i++) {
     strs[i] = sc.next();
  }
```

**输出** ： 同组字符串输出以空格分割，不同组用换行分割

```
for (List<String> group : result) {
            for (int i = 0; i < group.size(); i++) {
                System.out.print(group.get(i));
                if (i < group.size() - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
```

## 不提供字符串数组长度

**输入：提供三种方式，采用trim()函数分割**

```

        // 读取输入
        Scanner scanner = new Scanner(System.in);
  
        // 读取字符串数组
        String line = scanner.nextLine().trim();
  
        // 解析输入，支持多种格式：
        // 1. ["eat","tea","tan","ate","nat","bat"]
        // 2. eat,tea,tan,ate,nat,bat
        // 3. eat tea tan ate nat bat
        String[] strs;
  
        if (line.startsWith("[") && line.endsWith("]")) {
            // 格式1: ["eat","tea","tan","ate","nat","bat"]
            line = line.substring(1, line.length() - 1);
            line = line.replaceAll("\"", ""); // 去除引号
            strs = line.split(",");
        } else if (line.contains(",")) {
            // 格式2: eat,tea,tan,ate,nat,bat
            strs = line.split(",");
        } else {
            // 格式3: eat tea tan ate nat bat
            strs = line.split(" ");
        }
```

**输出：**

```
        StringBuilder sb = new StringBuilder("[");
  
        for (int i = 0; i < result.size(); i++) {
            List<String> group = result.get(i);
            sb.append("[");
  
            for (int j = 0; j < group.size(); j++) {
                sb.append("\"").append(group.get(j)).append("\"");
                if (j < group.size() - 1) {
                    sb.append(",");
                }
            }
  
            sb.append("]");
            if (i < result.size() - 1) {
                sb.append(",");
            }
        }
  
        sb.append("]");
```
