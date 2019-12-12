package org.about.zhaozt.algorithm;

import java.util.Stack;

/**
 * @author zhaozt
 * @date 2019年11月10日 下午1:28:59
 * describe : 单向链表
 */
public class SingleLinked {

	public static void main(String[] args) {
		
		UserNode userNode1 = new UserNode(1, "蔡徐坤", 2);
		UserNode userNode2 = new UserNode(2, "蔡英文", 3);
		UserNode userNode3 = new UserNode(3, "蔡依林", 4);
		UserNode userNode4 = new UserNode(4, "蔡元培", 5);
		//添加节点元素
		SingleLinkedList singleLinkedList = new SingleLinkedList();
		singleLinkedList.addLinkNode(userNode1);
		singleLinkedList.addLinkNode(userNode3);
		singleLinkedList.addLinkNode(userNode2);
		singleLinkedList.addLinkNode(userNode4);
		singleLinkedList.listLink();
		System.out.println("-----------反向打印单链表-----------");
		singleLinkedList.reversePrintf();
		//修改节点元素
		UserNode userNode01 = new UserNode(1, "蔡徐坤", 0);
		singleLinkedList.update(userNode01);
		System.out.println("-----------修改后-----------");
		singleLinkedList.listLink();
		System.out.println("-----------链表的第index位值-----------");
		UserNode userNode = singleLinkedList.getIndexNode(1);
		System.out.println(userNode);
		System.out.println("-----------链表的长度-----------");
		int legth = singleLinkedList.getLength();
		System.out.println("该链表长度为：" + legth);
		System.out.println("-----------链表反转-----------");
		singleLinkedList.reverse();
		singleLinkedList.listLink();
		//删除节点
		System.out.println("-----------删除节点-----------");
		singleLinkedList.delete(1);
		singleLinkedList.delete(4);
//		singleLinkedList.delete(2);
//		singleLinkedList.delete(3);
		singleLinkedList.listLink();
		int legth1 = singleLinkedList.getLength();
		System.out.println("该链表长度为：" + legth1);
	}

}

class SingleLinkedList{
	//初始化头节点
	private UserNode head = new UserNode(0, "", 0);
	//添加节点到单向链表
	public void add(UserNode userNode) {
		//head节点不动,最后next节点指向新节点
		UserNode temp = head;
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
		temp.next = userNode;
	}
	
	//按编号顺序添加链表
	public void addLinkNode(UserNode userNode) {
		//
		UserNode temp = head;
		boolean flag = false; //编号id是否存在，默认为false
		while(true) {
			if(temp.next == null) {
				break;
			}
			if(temp.next.id > userNode.id) {
				break;
			}else if(temp.next.id == userNode.id) {
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if(flag) {
			System.out.printf("输入的用户已存在！id=%d", userNode.id);
		}else {
			//插入到链表中
			userNode.next = temp.next;
			temp.next = userNode;
		}
	}
	
	//显示链表
	public void listLink() {
		if(head.next == null) {
			System.out.println("链表为空！");
			return;
		}
		UserNode temp = head.next;
		while(true) {
			if(temp == null) {
				break;
			}
			System.out.println(temp);
			temp = temp.next;
		}
	}
	
	//修改节点信息,根据编号id修改节点
	public void update(UserNode userNode) {
		if(head.next == null) {
			System.out.println("该链表为空");
			return;
		}
		UserNode temp = head.next;
		boolean flag = false; //是否找到该节点
		while(true) {
			//遍历结束
			if(temp == null) {
				break;
			}
			//找到该节点元素
			if(temp.id == userNode.id) {
				flag = true;
				break;
			}
			temp = temp.next;
		}
		
		if(flag) {
			temp.name = userNode.name;
			temp.score = userNode.score;
		}else {
			System.out.printf("未找到id=%d的节点!",userNode.id);
		}
	}
	
	//删除节点元素
	public void delete(int id) {
		//先找到需要删除的节点的前一个节点
		UserNode temp = head;
		boolean flag = false; //是否找到该节点
		while(true) {
			//遍历结束
			if(temp.next == null) {
				break;
			}
			//找到该节点元素
			if(temp.next.id == id) {
				flag = true;
				break;
			}
			temp = temp.next;
		}

		if(flag) {
			temp.next = temp.next.next;
		}else {
			System.out.printf("未找到要删除的id=%d的节点!",id);
		}
	}
	
	//获取链表长度(不含头节点)
	public int getLength() {
		if(head.next == null) {
			System.out.println("该链表为空");
		}
		UserNode cur = head.next;
		int length = 0;
		while(cur != null) {
			length++;
			cur = cur.next;
		}
		return length;
	}
	
	//查找第k个节点元素 index从1开始
	public UserNode getIndexNode(int index) {
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
		UserNode cur = head.next;
		for (int i = 1; i < index; i++) {
			cur = cur.next;
		}
		return cur;
	}
	
	//链表的反转
	public void reverse() {
		//判断链表是否为空
		if(head.next == null || head.next.next == null) {
			return;
		}
		//遍历原有的链表
		UserNode cur = head.next;
		UserNode nextNode = null;
		UserNode userNodeTemp = new UserNode(0, "", 0);
		while(cur != null) {
			nextNode = cur.next;
			cur.next = userNodeTemp.next;
			userNodeTemp.next = cur;
			cur = nextNode;
		}
		head.next = userNodeTemp.next;
	}
	
	//反向打印单链表
	public void reversePrintf() {
		
		Stack<UserNode> stack = new Stack<UserNode>();
		UserNode cur = head.next;
		//将链表压入栈中
		while(cur != null) {
			stack.push(cur);
			cur = cur.next;
		}
		//打印栈中元素
		while(stack.size()>0) {
			System.out.println("反序输出为：" + stack.pop());
		}
	}
	
}

class UserNode{
	public int id;
	public String name;
	public int score;
	public UserNode next;
	
	public UserNode(int id, String name, int score) {
		this.id = id;
		this.name = name;
		this.score = score;
	}

	@Override
	public String toString() {
		return "UserNode [id=" + id + ", name=" + name + ", score=" + score + "]";
	}
	
}

