package org.about.zhaozt.algorithm;

import java.util.Scanner;

/**
 * @author zhaozt
 * @date 2019年11月9日 上午9:32:41
 * describe : java实现队列数据结构
 */
public class Queue {

	public static void main(String[] args) {
		//创建一个队列
		QueueArray queueArray = new QueueArray(3);
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
				queueArray.showQueue();
				break;
			case 'q':
				scanner.close();
				loop = false;
				break;
			case 'a':
				System.out.println("请输入一个数：");
				int num = scanner.nextInt();
				queueArray.addQueue(num);
				break;
			case 'o':
				try {
					int elem = queueArray.getNum();
					System.out.printf("取出队列元素：%d\n", elem);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'h':
				try {
					int headNum = queueArray.headQueue();
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

class QueueArray{
	private int[] queue;
	private int maxSize;
	private int front;
	private int rear;
	
	//创建队列构造器
	public QueueArray(int arrMaxSize){
		maxSize = arrMaxSize;
		queue = new int[maxSize];
		front = -1;
		rear = -1;
	}
	
	//判断队列是否为空
	public boolean isEmpty() {
		return front == rear;
	}
	
	//判断队列是否已满
	public boolean isFully() {
		return rear == maxSize - 1;
	}
	
	//添加队列
	public void addQueue(int num) {
		if(isFully()) {
			System.out.println("队列已满！");
			return;
		}
		rear++;
		queue[rear] = num;
	}
	
	//从队列中取数据
	public int getNum() {
		if(isEmpty()) {
			throw new RuntimeException("队列为空！");
		}
		front++;
		return queue[front];
	}
	
	//展示队列元素
	public void showQueue() {
		if(isEmpty()) {
			System.out.println("队列为空！");
			return;
		}
		for (int i = 0; i < queue.length; i++) {
			System.out.printf("queue[%d] = %d\n",i,queue[i]);
		}
	}
	
	//显示队首元素
	public int headQueue() {
		if(isEmpty()) {
			throw new RuntimeException("队列为空！");
		}
		return queue[front+1];
	}
	
}
