package org.about.zhaozt.algorithm;
/**
 * @author zhaozt
 * @date 2019年11月13日
 * describe : 数组实现栈
 */
public class Stack {

	public static void main(String[] args) {
		
		ArrayStack stack = new ArrayStack(5);
		stack.push(1);
		stack.push(3);
		stack.push(5);
		stack.push(7);
		stack.push(2);
		stack.push(9);
		stack.list();
		System.out.println("---------------------");
		stack.pop();
		stack.list();
	}

}

class ArrayStack{
	//定义栈结构
	private int maxSize;
	private int[] stack;
	private int top = -1;
	//构造栈
	public ArrayStack(int maxSize) {
		this.maxSize = maxSize;
		stack = new int[this.maxSize];
	}
	//判断栈满
	public boolean isFully() {
		return top == maxSize-1;
	}
	//判断栈空
	public boolean isEmpty() {
		return top == -1;
	}
	//添加栈元素
	public void push(int num) {
		if(isFully()) {
			System.out.println("栈元素已满！");
			return;
		}
		top++;
		stack[top] = num;
	}
	//出栈
	public int pop() {
		if(isEmpty()) {
			throw new RuntimeException("栈为空！");
		}
		int value = stack[top];
		top--;
		return value;
	}
	//遍历栈
	public void list() {
		if(isEmpty()) {
			System.out.println("栈为空！");
			return;
		}
		for (int i = top; i >= 0 ; i--) {
			System.out.printf("栈元素为:a[%d]=%d\n",i,stack[i]);
		}
	}
}

