package org.about.zhaozt.algorithm;
/**
 * @author zhaozt
 * @date 2019年11月12日 上午7:16:45
 * describe :双向链表
 */
public class DoubleLinked {

	public static void main(String[] args) {
		CustNode custNode1 = new CustNode(1, "蔡徐坤", 2);
		CustNode custNode2 = new CustNode(2, "蔡英文", 3);
		CustNode custNode3 = new CustNode(3, "蔡依林", 4);
		CustNode custNode4 = new CustNode(4, "蔡元培", 5);
		//添加节点元素
		DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
		doubleLinkedList.add(custNode1);
		doubleLinkedList.add(custNode3);
		doubleLinkedList.add(custNode2);
		doubleLinkedList.add(custNode4);
		doubleLinkedList.listLink();
		//修改节点元素
		CustNode CustNode01 = new CustNode(1, "蔡徐坤", 0);
		doubleLinkedList.update(CustNode01);
		System.out.println("-----------修改后-----------");
		doubleLinkedList.listLink();
		System.out.println("-----------链表的第index位值-----------");
		CustNode CustNode = doubleLinkedList.getIndexNode(1);
		System.out.println(CustNode);
		System.out.println("-----------反向打印单链表-----------");
		doubleLinkedList.reversePrintf();
		System.out.println("-----------链表的长度-----------");
		int legth = doubleLinkedList.getLength();
		System.out.println("该链表长度为：" + legth);
		//删除节点
		System.out.println("-----------删除节点-----------");
		doubleLinkedList.delete(1);
		doubleLinkedList.delete(4);
//		doubleLinkedList.delete(2);
//		doubleLinkedList.delete(3);
		doubleLinkedList.listLink();
		int legth1 = doubleLinkedList.getLength();
		System.out.println("该链表长度为：" + legth1);

	}

}

class DoubleLinkedList{
	//初始化头节点
	private CustNode head = new CustNode(0, "", 0);
	//添加节点到单向链表
	public void add(CustNode custNode) {
		//head节点不动,最后next节点指向新节点
		CustNode temp = head;
		//遍历队列
		while(true) {
			//链表尾部
			if(temp.next == null) {
				break;
			}
			//不是链表尾部，后移
			temp = temp.next;
		}
		//退出循环时，链表temp指向最后
		temp.next = custNode;
		custNode.pre = temp;
	}
	
	//修改节点信息,根据编号id修改节点
	public void update(CustNode custNode) {
		if(head.next == null) {
			System.out.println("该链表为空");
			return;
		}
		CustNode temp = head.next;
		boolean flag = false; //是否找到该节点
		while(true) {
			//遍历结束
			if(temp == null) {
				break;
			}
			//找到该节点元素
			if(temp.id == custNode.id) {
				flag = true;
				break;
			}
			temp = temp.next;
		}
		
		if(flag) {
			temp.name = custNode.name;
			temp.score = custNode.score;
		}else {
			System.out.printf("未找到id=%d的节点!",custNode.id);
		}
	}
	
	//删除节点元素
	public void delete(int id) {
		//先找到需要删除的节点的前一个节点
		CustNode temp = head;
		boolean flag = false; //是否找到该节点
		while(true) {
			//遍历结束
			if(temp.next == null) {
				break;
			}
			//找到该节点元素
			if(temp.id == id) {
				flag = true;
				break;
			}
			temp = temp.next;
		}

		if(flag) {
			//让删除元素的前一个元素的next指向删除元素的后一个元素
			temp.pre.next = temp.next;
			//让删除元素的后一个元素的pre指向删除元素的前一个元素
			//但当遍历到最后一个元素时不需要此操作
			if(temp.next != null) {
				temp.next.pre = temp.pre;
			}
		}else {
			System.out.printf("未找到要删除的id=%d的节点!",id);
		}
	}
	
	//获取链表长度(不含头节点)
	public int getLength() {
		if(head.next == null) {
			System.out.println("该链表为空");
		}
		CustNode cur = head.next;
		int length = 0;
		while(cur != null) {
			length++;
			cur = cur.next;
		}
		return length;
	}
	
	//显示链表
	public void listLink() {
		if(head.next == null) {
			System.out.println("链表为空！");
			return;
		}
		CustNode temp = head.next;
		while(true) {
			if(temp == null) {
				break;
			}
			System.out.println(temp);
			temp = temp.next;
		}
	}
	
	//查找第k个节点元素 index从1开始
	public CustNode getIndexNode(int index) {
		int length = this.getLength();
		if(head.next == null) {
			System.out.println("该链表为空");
			return null;
		}
		
		if(index <= 0 || index > length) {
			System.out.println("超出链表长度！");
			return null;
		}
		
		//遍历链表
		CustNode cur = head.next;
		for (int i = 1; i < index; i++) {
			cur = cur.next;
		}
		return cur;
	}
	
	//双向链表逆序打印
	public void reversePrintf() {
		if(head.next == null) {
			System.out.println("链表为空！");
			return;
		}
		//找出最后一个节点元素
		CustNode cur = head.next;
		while(cur.next != null) {
			cur = cur.next;
		}
		while (cur.id != head.id) {
			System.out.println(cur);
			cur = cur.pre;
		}
		
	}
}

class CustNode{
	public int id;
	public String name;
	public int score;
	public CustNode next;
	public CustNode pre;
	
	public CustNode(int id, String name, int score) {
		this.id = id;
		this.name = name;
		this.score = score;
	}

	@Override
	public String toString() {
		return "CustNode [id=" + id + ", name=" + name + ", score=" + score + "]";
	}
	
}
