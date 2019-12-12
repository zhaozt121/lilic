package org.about.zhaozt.algorithm;
/**
 * @author zhaozt
 * @date 2019年11月12日 下午7:36:09
 * describe : 与瑟夫问题（单向环形链表）
 */
public class Josephu {

	public static void main(String[] args) {
		
		CircleSingleLinkedList josephu = new CircleSingleLinkedList();
		josephu.addStu(7);
		josephu.showStuNode();
		josephu.outStuNode(4, 3, 7);
	}

}

class CircleSingleLinkedList{
	
	//初始化单向环形链表
	private StuNode first = new StuNode(-1);
	public void addStu(int num) {
		if(num<1) {
			System.out.println("输入值不正确！");
			return;
		}
		StuNode cur = null;
		for (int i = 1; i <= num; i++) {
			//创建节点
			StuNode stuNode = new StuNode(i);
			if(i == 1) {
				first = stuNode;
				first.setNext(first);
				cur = first;
			}else {
				cur.setNext(stuNode);
				stuNode.setNext(first);
				cur = stuNode;
			}
		}
	}
	
	//遍历环形链表
	public void showStuNode() {
		if(first == null) {
			System.out.println("链表为空！");
			return;
		}
		StuNode cur = first;
		while(true) {
			System.out.printf("学生编号为%d\n",cur.getId());
			if(cur.next == first) {
				break;
			}
			cur = cur.getNext();
		}
	}
	
	//出列
	public void outStuNode(int startNum, int countNum, int maxNum) {
		
		if(first == null || startNum < 1 || startNum > maxNum) {
			System.out.println("输入参数有误！");
			return;
		}
		//first指向初始化第一个节点，helper指向最后一个节点，也就是first的前一节点
		StuNode helper = first;
		while(true) {
			if(helper.getNext() == first) {
				break;
			}
			helper = helper.getNext();
		}
		//让first指向单项环形队列startNum元素位置
		for (int i = 1; i < startNum; i++) {
			first = first.getNext();
			helper = helper.getNext();
		}
		//出列
		while(true) {
			if (helper == first) {
				break;
			}
			for (int i = 1; i < countNum; i++) {
				first = first.getNext();
				helper = helper.getNext();
			}
			//
			System.out.println("出列学生编号为：" + first.getId());
			first = first.getNext();
			helper.setNext(first);
		}
		System.out.println("最后出列的学生编号为：" + first.getId());
		
	}
}

class StuNode{
	public int id;
	public StuNode next;
	
	public StuNode(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StuNode getNext() {
		return next;
	}

	public void setNext(StuNode next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "StuNode [id=" + id + "]";
	}
}
