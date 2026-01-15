package stack;
import java.util.*;
/**
 * @Author 2hang1iang
 * @Date 2026/01/11 13:25
 * @Description 155. 最小栈 【简单】
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * 实现 MinStack 类:
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 * 示例 1:
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 * 题解：两个栈，一个正常栈，一个最小栈
 *  正常栈：正常入栈出栈，正常维护每一个元素
 *  最小栈：当入栈元素小于等于最小栈栈顶元素时，入栈
 *  出栈时，若出栈元素等于最小栈栈顶元素时，最小栈出栈
 *  最小栈：栈顶元素为当前最小元素
 *  入栈和出栈时，才会操作最小栈，判断最小栈的栈顶元素为当前最小元素
 **/

public class MinStack {
    private Deque<Integer> stack;
    private Deque<Integer> minStack;
    public MinStack() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    public void push(int val) {
        stack.push(val);
        if(minStack.isEmpty() || val <= minStack.peek()) { // 等于时也入栈，出栈时需要判断是否等于最小栈栈顶元素
            minStack.push(val);
        }
    }

    public void pop() {
        if(stack.pop().equals(minStack.peek())) { // 不能用==，因为栈中元素为Integer类型，需要用equals方法
            minStack.pop();
        }
    }

    public int top(){
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // -3
        minStack.pop();
        System.out.println(minStack.top()); // 0
        System.out.println(minStack.getMin()); // -2
    }
}
