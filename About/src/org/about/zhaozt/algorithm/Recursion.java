package org.about.zhaozt.algorithm;
/**
 * @author zhaozt
 * @date 2019年11月16日 上午10:29:48
 * describe :
 */
public class Recursion {

	public static void main(String[] args) {
		int reslut = factorial(9);
		System.out.println(reslut);
		printOrder(5);
	}
	
	//打印问题
	public static void printOrder(int n) {
		if(n >2) {
			printOrder(n-1);
		}
		System.out.println(n);
	}
	
	//阶乘问题
	public static int factorial(int n) {
		if(n == 1) {
			return 1;
		}else {
			return factorial(n-1)*n;
		}
	}
}

