package org.about.zhaozt.algorithm;
/**
 * @author zhaozt
 * @date 2019年11月14日 下午4:50:36
 * describe : 逆波兰计算器()
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
//改进 numStack可以使用ArrayList替换
public class PolandNotation {

	public static void main(String[] args) {
		
		//将一个中缀表达式放入列表中
		String infixExpression = "(19-(3+2)*2)*  2+6 /3+12"; //[19, 3, 2, +, 2, *, -, 2, *, 6, 3, /, +, 12, +] =>23
		System.out.println(infixExpression);
		List<String> InfixList = listInfixExpression(infixExpression);
		System.out.println(InfixList);
		
		//将中缀表达式转化为后缀表达式
		List<String> suffixList = infixToSuffix(InfixList);
		System.out.println(suffixList);
		
		//计算逆波兰表达式
		int value = calc(suffixList);
		System.out.println("逆波兰表达式计算结果为：" + value);
		
	}
	
	//将中缀表达式串存入列表中
	public static List<String> listInfixExpression(String expression){
		
		char[] chs = expression.toCharArray();
		//将后缀表达式存入列表中   
		List<String> list = new ArrayList<String>();
		String number = ""; //多位数
		for(char ch:chs) {
			if(" ".equals(ch+"")) {
				continue;
			}
			//为操作符
			if(!(ch+"").matches("\\d+")) {
				//如果数字不为空，加入列表
				if(!"".equals(number)) {
					list.add(number);
				}
				number = "";
				list.add(ch+"");
			}else {
				number = number + ch;     
			}
		}
		list.add(number);
		return list;
	}
	
	//将中缀表达式转化为后缀表达式列表
	public static List<String> infixToSuffix(List<String> list){
		//初始化符号栈和数字栈
		Stack<String> operStack = new Stack<String>();
		Stack<String> numStack = new Stack<String>();
		List<String> suffixList = new ArrayList<String>();
		for(String str:list) {
			if(str.matches("\\d+")) {
				numStack.push(str);
			}else if("(".equals(str)){
				//如果str为'('则直接入栈,
				operStack.push(str);
			}else if(")".equals(str)){
				//如果为')'则一次弹出operStack栈顶元素，压入numStack栈中,直到遇到'('为止,废弃掉一对()
				while(!"(".equals(operStack.peek())) {
					numStack.push(operStack.pop());
				}
				operStack.pop();
			}else {
				//如果operStack为空或栈顶元素为'(' 则直接入栈
				//如果优先级小于或等于operStack的栈顶元素，将operStack栈顶元素弹出压入到numStack栈中，再次与operStack栈顶元素比较
				while(!operStack.isEmpty() && priority(operStack.peek()) >= priority(str)) {
					numStack.push(operStack.pop());
				}
				operStack.push(str);
			}
		}
		
		//将operStack栈中剩余的元素依次弹出，压入到numStack栈中
		while(!operStack.isEmpty()) {
			numStack.push(operStack.pop());
		}
		
		//将numStack栈中元素放入list中
		while(!numStack.isEmpty()) {
			String num = numStack.pop();
			operStack.push(num);
		}
		
		while(!operStack.isEmpty()) {
			String num = operStack.pop();
			suffixList.add(num);
		}
		return suffixList;
	}
	
	//返回运算符的优先级
	public static int priority(String oper) {
		if("*".equals(oper) || "/".equals(oper)) {
			return 1;
		}
		if("+".equals(oper) || "-".equals(oper)) {
			return 0;
		}else {
			return -1;
		}
	}
	
	//计算逆波兰表达式
	public static int calc(List<String> list) {
		Stack<Integer> stack = new Stack<Integer>();
		for(String str:list) {
			if(str.matches("\\d+")) {
				stack.push(Integer.parseInt(str));
			}else {
				int num2 = stack.pop();
				int num1 = stack.pop();
				int result = 0;
				switch (str) {
				case "+":
					result = num1 + num2;
					break;
				case "-":
					result = num1 - num2;
					break;
				case "*":
					result = num1 * num2;
					break;
				case "/":
					result = num1 / num2;
					break;
				default:
					break;
				}
				stack.push(result);
			}
		}
		return stack.pop();
	}
	

}

