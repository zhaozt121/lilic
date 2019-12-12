package org.about.zhaozt.algorithm;

import java.util.Scanner;

/**
 * @author zhaozt
 * @date 2019年11月10日 上午10:15:55
 * describe :环形队列
 */
public class CircleQueue {

	public static void main(String[] args) {
		//测试数组模拟环形队列
		//创建一个队列,队列有效值size-1
		CircleArray circleArray = new CircleArray(4);
		char key = ' ';
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		//操作
		while(loop) {
			System.out.println("s-显示队列：");
			System.out.println("q-退出程序：");
			System.out.println("a-添加元素：");
			System.out.println("o-出队列：");
			System.out.println("h-输出队列头元素：");
			key = scanner.next().charAt(0);
			switch(key) {
			case 's':
				circleArray.showQueue();
				break;
			case 'q':
				scanner.close();
				loop = false;
				break;
			case 'a':
				System.out.println("请输入一个数：");
				int num = scanner.nextInt();
				circleArray.addQueue(num);
				break;
			case 'o':
				try {
					int elem = circleArray.getQueue();
					System.out.printf("取出队列元素：%d\n", elem);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'h':
				try {
					int headNum = circleArray.headQueue();
					System.out.printf("队列头部元素为：%d\n", headNum);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			default:
				break;
			}
		}
		System.out.println("程序已退出");

	}

}
class CircleArray{
	
	private int[] arr;
	private int maxSize;
	private int front;	//指向队列的第一个元素，front初始值为0
	private int rear;	//指向队列的最后一个元素的后一个位置，rear的初始值为0
	
	//创建队列构造器
	public CircleArray(int arrMaxSize){
		maxSize = arrMaxSize;
		arr = new int[maxSize];
	}
	
	//判断队列是否为空
	public boolean isEmpty() {
		return front == rear;
	}
	
	//判断队列是否已满
	public boolean isFully() {
		return (rear + 1) % maxSize == front;
	}
	
	//添加队列
	public void addQueue(int num) {
		if(isFully()) {
			System.out.println("队列已满！");
			return;
		}
		arr[rear] = num;
		rear = (rear + 1) % maxSize;
	}
	
	//从队列中取数据
	public int getQueue() {
		if(isEmpty()) {
			throw new RuntimeException("队列为空！");
		}
		int value = arr[front];
		front = (front + 1) % maxSize;
		return value;
	}
	
	//展示队列元素
	public void showQueue() {
		if(isEmpty()) {
			System.out.println("队列为空！");
			return;
		}
		//从front遍历
		for (int i = front; i < front + size(); i++) {
			System.out.printf("arr[%d] = %d\n",i % maxSize,arr[i % maxSize]);
		}
	}
	
	//当前队列有效数据个数
	public int size() {
		return (rear + maxSize - front) % maxSize;
	}
	
	//显示队首元素
	public int headQueue() {
		if(isEmpty()) {
			throw new RuntimeException("队列为空！");
		}
		return arr[front];
	}
		
}

