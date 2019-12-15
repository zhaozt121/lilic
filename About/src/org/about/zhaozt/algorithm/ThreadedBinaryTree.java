package org.about.zhaozt.algorithm;
/**
 * @author zhaozt
 * @date 2019年12月12日 下午8:41:18
 * describe : 线索化二叉树
 */
public class ThreadedBinaryTree {

	public static void main(String[] args) {
		//创建二叉树
		ThreadedBinaryNode tree = new ThreadedBinaryNode();
		//初始化二叉树节点数据
		ThreadedNode root = new ThreadedNode(0, "张三");
		ThreadedNode node1 = new ThreadedNode(1, "李四");
		ThreadedNode node2 = new ThreadedNode(2, "王五");
		ThreadedNode node3 = new ThreadedNode(3, "赵六");
		ThreadedNode node4 = new ThreadedNode(4, "申七");
		
		root.setLeftChild(node1);
		root.setRightChild(node2);
		node1.setLeftChild(node3);
		node1.setRightChild(node4);
		tree.setRoot(root);

		//线索化
		tree.threadedNode();
		ThreadedNode threadedNode1 = node4.getLeftChild();
		System.out.println(threadedNode1);
		ThreadedNode threadedNode2 = node4.getRightChild();
		System.out.println(threadedNode2);
		
		//遍历线索二叉树
		System.out.println("中序遍历线索二叉树");
		tree.infixList();
	}

}

class ThreadedBinaryNode{
	
	private ThreadedNode root;
	//前驱节点
	private ThreadedNode pre = null;
	
	
	public void setRoot(ThreadedNode root) {
		this.root = root;
	}
	
	public void threadedNode() {
		this.threadedNode(root);
	}
	
	//中序线索化二叉树
	public void threadedNode(ThreadedNode node) {
		
		if(node == null) {
			return;
		}
		threadedNode(node.getLeftChild());
		//线索化当前节点的前驱节点
		if(node.getLeftChild() == null) { 
			node.setLeftChild(pre);
			node.setLeft(1);
		}
		//线索化当前节点的后继节点
		if(pre != null && pre.getRightChild() == null) {
			pre.setRightChild(node);
			pre.setRight(1);
		}
		//将当前节点给前驱节点
		pre = node;
		
		threadedNode(node.getRightChild());
	}
	
	//中序遍历
	public void infixList() {
		//从root开始遍历
		ThreadedNode node = root;
		while(node != null) {
			while(node.getLeft() == 0) {
				node = node.getLeftChild();
			}
			
			System.out.println(node);
			while(node.getRight() == 1) {
				node = node.getRightChild();
				System.out.println(node);
			}
			
			node = node.getRightChild();
			
		}
	}
}

class ThreadedNode{
	private int id;
	private String name;
	private ThreadedNode leftChild;
	private ThreadedNode rightChild;
	//节点是否指向前驱,0-指向子树 1-指向前驱
	private int left;
	private int right;
	
	public ThreadedNode(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ThreadedNode getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(ThreadedNode leftChild) {
		this.leftChild = leftChild;
	}
	public ThreadedNode getRightChild() {
		return rightChild;
	}
	public void setRightChild(ThreadedNode rightChild) {
		this.rightChild = rightChild;
	}
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getRight() {
		return right;
	}
	public void setRight(int right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "ThreadedNode [id=" + id + ", name=" + name + "]";
	}
	
}


