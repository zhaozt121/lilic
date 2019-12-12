package org.about.zhaozt.algorithm;
/**
 * @author zhaozt
 * @date 2019年11月13日
 * describe : 输入一个运算串，使用一个栈存放运算符，另一个栈存放数字，计算表达式结果
 */
public class Calculator {

	public static void main(String[] args) {
		String expression = "9*2/6-1+31-6/3";
		//定义两个栈，一个存放数字，一个存放操作符
		NumStack numStack = new NumStack(expression.length());
		OperStack operStack = new OperStack(expression.length());
		//
		int index = 0;
		int num1 = 0;
		int num2 = 0;
		String number = ""; //操作数
		char oper = 0; //运算符
		int result = 0; //运算结果
		//循环遍历expression字符串
		char ch = 0;
		while(true) {
			ch = expression.substring(index, index+1).charAt(0);
			//如果是操作符
			if(operStack.isOper(ch)) {
				//当操作符栈为空时，直接入栈
				if(operStack.isEmpty()) {
					operStack.push(ch);
				}else {
					//当操作符栈不为空时，判断该操作符与栈顶元素的优先级,优先级小于等于栈顶元素时进行如下计算
					if(operStack.priority(operStack.peek()) >= operStack.priority(ch)) {
						oper = operStack.pop();
						num2 = numStack.pop();
						num1 = numStack.pop();
						result = operStack.calc(num1, num2, oper);
						numStack.push(result);
						operStack.push(ch);
					}else {
						operStack.push(ch);
					}
				}
				ch = 0;
			}else { //如果操作符为数字
				//多位数相加
				number = number.concat(ch+"");
				//如果已到表达式的末尾
				if(index == expression.length()-1) {
					numStack.push(Integer.parseInt(number));
					number = "";
					break;
				}
				//如果后面为操作符则入数栈
				if(operStack.isOper(expression.charAt(index+1))) {
					numStack.push(Integer.parseInt(number));
					number = "";
				}
			}
			index++;
			if(index >= expression.length()) {
				break;
			}
		}
		
		//expression遍历完毕，从栈中取出数据进行计算
		while(true) {
			if(operStack.isEmpty()) {
				break;
			}
			oper = operStack.pop();
			num2 = numStack.pop();
			num1 = numStack.pop();
			result = numStack.calc(num1, num2, oper);
			numStack.push(result);
		}
		//最后数据栈中的出栈，为所求结果
		result = numStack.pop();
		System.out.println("计算结果为：" + result);
	}

}

class NumStack{
	//定义栈结构
	private int maxSize;
	private int[] stack;
	private int top = -1;
	//构造栈
	public NumStack(int maxSize) {
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
	
	//返回运算符的优先级
	public int priority(int oper) {
		if(oper == '*' || oper == '/') {
			return 1;
		}
		if(oper == '+' || oper == '-') {
			return 0;
		}else {
			return -1;
		}
	}
	
	//判断是不是运算符
	public boolean isOper(char oper) {
		return oper == '+' || oper == '-' || oper == '*' || oper == '/';
	}
	
	//计算
	public int calc(int num1, int num2, int oper) {
		int value = 0;
		switch (oper) {
		case '+':
			value = num1 + num2;
			break;
		case '-':
			value = num1 - num2;
			break;
		case '*':
			value = num1 * num2;
			break;
		case '/':
			value = num1 / num2;
			break;
		default:
			break;
		}
		return value;
	}
	//显示栈顶元素
	public int peek() {
		return stack[top];
	}
	
}

class OperStack{
	//定义栈结构
	private int maxSize;
	private char[] stack;
	private int top = -1;
	//构造栈
	public OperStack(int maxSize) {
		this.maxSize = maxSize;
		stack = new char[this.maxSize];
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
	public void push(char ch) {
		if(isFully()) {
			System.out.println("栈元素已满！");
			return;
		}
		top++;
		stack[top] = ch;
	}
	//出栈
	public char pop() {
		if(isEmpty()) {
			throw new RuntimeException("栈为空！");
		}
		char value = stack[top];
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
	
	//返回运算符的优先级
	public int priority(char oper) {
		if(oper == '*' || oper == '/') {
			return 1;
		}
		if(oper == '+' || oper == '-') {
			return 0;
		}else {
			return -1;
		}
	}
	
	//判断是不是运算符
	public boolean isOper(char oper) {
		return oper == '+' || oper == '-' || oper == '*' || oper == '/';
	}
	
	//计算
	public int calc(int num1, int num2, char oper) {
		int value = 0;
		switch (oper) {
		case '+':
			value = num1 + num2;
			break;
		case '-':
			value = num1 - num2;
			break;
		case '*':
			value = num1 * num2;
			break;
		case '/':
			value = num1 / num2;
			break;
		default:
			break;
		}
		return value;
	}
	//显示栈顶元素
	public char peek() {
		return stack[top];
	}
	
}


