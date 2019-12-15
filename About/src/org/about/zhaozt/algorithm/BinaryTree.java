package org.about.zhaozt.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaozt
 * @date 2019年12月8日 下午3:13:54
 * describe :
 */
public class BinaryTree {
	
	public static int[] array = {1,2,3,4,5,6,7,8};
	public static List<Integer> preList = new ArrayList<Integer>();

	public static void main(String[] args) {
		//创建二叉树
		BinaryTreeStructure tree = new BinaryTreeStructure();
		//初始化二叉树节点数据
		TreeNode root = new TreeNode(1, "张三");
		TreeNode node1 = new TreeNode(2, "李四");
		TreeNode node2 = new TreeNode(3, "王五");
		TreeNode node3 = new TreeNode(4, "赵六");
		TreeNode node4 = new TreeNode(5, "申七");
		
		root.setLeftChild(node1);
		root.setRightChild(node2);
		node1.setLeftChild(node3);
		node1.setRightChild(node4);
		tree.setRoot(root);
		
		System.out.println("前序遍历:");
		tree.preList();
		System.out.println("中序遍历:");
		tree.infixList();
		System.out.println("后序遍历:");
		tree.postList();
		
		System.out.println("前序遍历查找:");
		TreeNode treeNode4 = tree.preSearch(4);
		System.out.println(treeNode4);
		System.out.println("中序遍历查找:");
		TreeNode treeNode3 = tree.infixSearch(3);
		System.out.println(treeNode3);
		System.out.println("后序遍历查找:");
		TreeNode treeNode5 = tree.postSearch(5);
		System.out.println(treeNode5);
		
		tree.delete(2);
		System.out.println("前序遍历:");
		tree.preList();
		
		storePre(0);
		System.out.println(preList);
	}
	
	//顺序存储二叉树
	public static void storePre(int index) {
		if(array == null || array.length ==0) {
			System.out.println("该数组为空");
			return ;
		}
		preList.add(array[index]);
		if((index*2 + 1)<array.length) {
			storePre(index*2 + 1);
		}
		if((index*2 + 2)<array.length) {
			storePre(index*2 + 2);
		}
	}

}

class BinaryTreeStructure{
	
	private TreeNode root;
	
	public void setRoot(TreeNode root) {
		this.root = root;
	}
	
	//前序遍历
	public void preList() {
		if(this.root != null) {
			this.root.preList();
		}else {
			System.out.println("该二叉树为空");
		}
	}
	
	//中序遍历
	public void infixList() {
		if(this.root != null) {
			this.root.infixList();
		}else {
			System.out.println("该二叉树为空");
		}
	}
	
	//后序遍历
	public void postList() {
		if(this.root != null) {
			this.root.postList();
		}else {
			System.out.println("该二叉树为空");
		}
	}
	
	//前序遍历查找
	public TreeNode preSearch(int id) {
		if(root != null) {
			return root.preSearch(id);
		}
		return null;
	}
	//中序遍历查找
	public TreeNode infixSearch(int id) {
		if(root != null) {
			return root.infixSearch(id);
		}
		return null;
	}
	//后序遍历查找
	public TreeNode postSearch(int id) {
		if(root != null) {
			return root.postSearch(id);
		}
		return null;
	}
	//删除
	public void delete(int id) {
		if(root != null) {
			if(root.getUserId() == id) {
				root = null;
			}else {
				root.delete(id);
			}
		}else {
			System.out.println("该二叉树为空");
		}
	}
	
}

class TreeNode{
	
	public int userId;
	public String userName;
	public TreeNode leftChild;
	public TreeNode rightChild;
	
	public TreeNode(int userId, String userName) {
		super();
		this.userId = userId;
		this.userName = userName;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public TreeNode getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(TreeNode leftChild) {
		this.leftChild = leftChild;
	}
	public TreeNode getRightChild() {
		return rightChild;
	}
	public void setRightChild(TreeNode rightChild) {
		this.rightChild = rightChild;
	}
	
	@Override
	public String toString() {
		return "TreeNode [userId=" + userId + ", userName=" + userName + "]";
	}
	static List<TreeNode> infixList = new ArrayList<TreeNode>();
	//中序遍历
	public void infixArrayList() {
		//递归遍历左子节点
		if(this.leftChild != null) {
			this.leftChild.infixArrayList();
		}
		//输出父节点
		System.out.println(this);
		infixList.add(this);
		//遍历输出右子节点
		if(this.rightChild != null) {
			this.rightChild.infixArrayList();
		}
	}
	
	//前序遍历
	public void preList() {
		//输出当前父节点
		System.out.println(this);
		//输出左子节点
		if(this.leftChild != null) {
			this.leftChild.preList();
		}
		//输出右子节点
		if(this.rightChild != null) {
			this.rightChild.preList();
		}
	}
	//中序遍历
	public void infixList() {
		//递归遍历左子节点
		if(this.leftChild != null) {
			this.leftChild.infixList();
		}
		//输出父节点
		System.out.println(this);
		//遍历输出右子节点
		if(this.rightChild != null) {
			this.rightChild.infixList();
		}
	}
	//后序遍历
	public void postList() {
		//遍历左子节点
		if(this.leftChild != null) {
			this.leftChild.postList();
		}
		//遍历右子节点
		if(this.rightChild != null) {
			this.rightChild.postList();
		}
		//输出当前父节点
		System.out.println(this);
	}
	
	//前序遍历查找
	public TreeNode preSearch(int id) {
		//当前父节点
		if(this.userId == id) {
			return this;
		}
		//左递归查找
		TreeNode tree = null;
		if(this.leftChild != null) {
			tree = this.leftChild.preSearch(id);
		}
		
		if(tree != null) {
			return tree;
		}
		
		//右递归查找
		if(this.rightChild != null) {
			tree = this.rightChild.preSearch(id);
		}
		
		return tree;
	}
	
	//中序遍历查找
	public TreeNode infixSearch(int id) {
		
		//左递归查找
		TreeNode tree = null;
		if(this.leftChild != null) {
			tree = this.leftChild.infixSearch(id);
		}
		
		if(tree != null) {
			return tree;
		}
		
		//当前父节点
		if(this.userId == id) {
			return this;
		}
		
		//右递归查找
		if(this.rightChild != null) {
			tree = this.rightChild.infixSearch(id);
		}
		
		return tree;
	}
	
	//后序遍历查找
	public TreeNode postSearch(int id) {
		
		//左递归查找
		TreeNode tree = null;
		if(this.leftChild != null) {
			tree = this.leftChild.postSearch(id);
		}
		
		if(tree != null) {
			return tree;
		}
		
		//右递归查找
		if(this.rightChild != null) {
			tree = this.rightChild.postSearch(id);
		}
		
		if(tree != null) {
			return tree;
		}
		
		//当前父节点
		if(this.userId == id) {
			return this;
		}
		
		return tree;
	}
	
	//删除
	public void delete(int id) {
		
		//查找id在左子树中时
		if(this.leftChild != null && this.leftChild.userId == id) {
			//该节点为叶子节点，则直接删除
			if(this.leftChild.leftChild == null && this.leftChild.rightChild == null) {
				this.leftChild = null;
				return;
			}
			//如果该节点有左子节点
			if(this.leftChild.leftChild != null) {
				if(this.leftChild.rightChild == null) {
					this.leftChild = this.leftChild.leftChild;
					return;
				}else {
					//被删除元素存在左右子节点时，找出该节点的前驱（或后继）做替换
					infixArrayList();
					int index = infixList.lastIndexOf(this.leftChild);
					//得到前驱节点值
					TreeNode preNode = infixList.get(index-1);
					//如果前驱节点的父节点为待删除节点，则直接将前驱节点替换为待删除节点
					if(this.leftChild.leftChild.userId == preNode.userId) {
						preNode.rightChild = this.leftChild.rightChild;
						this.leftChild = preNode;
						return;
					}else {
						if(preNode.leftChild != null) {
							//得到前驱节点的父节点或左子节点
							TreeNode leftPreNode = infixList.get(index-2);
							//得到前驱节点的父节点值  
							TreeNode parentPreNode = infixList.get(index-3);
							//替换前驱节点
							parentPreNode.rightChild = leftPreNode;
						}else {
							//得到前驱节点的父节点或左子节点
							TreeNode leftPreNode = infixList.get(index-2);
							//当前驱节点不存在子节点时，删除前驱节点
							leftPreNode.rightChild = null;
						}
						//将前驱节点替换要删除节点
						preNode.leftChild = this.leftChild.leftChild;
						preNode.rightChild = this.leftChild.rightChild;
						this.leftChild = preNode;
						return;
					}
					
				}
				
			}
			//如果该节点只有右子节点
			if(this.leftChild.leftChild == null && this.leftChild.rightChild != null) {
				this.leftChild = this.leftChild.rightChild;
				return;
			}
		}
		
		//查找id在右子树中时
		if(this.rightChild != null && this.rightChild.userId == id) {
			//该节点为叶子节点，则直接删除
			if(this.rightChild.leftChild == null && this.rightChild.rightChild == null) {
				this.rightChild = null;
				return;
			}
			//如果该节点有左子节点
			if(this.rightChild.leftChild != null) {
				if(this.rightChild.rightChild == null) {
					this.rightChild = this.rightChild.leftChild;
					return;
				}else {
					//被删除元素存在左右子节点时，找出该节点的前驱（或后继）做替换
					infixArrayList();
					int index = infixList.lastIndexOf(this.rightChild);
					//得到前驱节点值
					TreeNode preNode = infixList.get(index-1);
					//如果前驱节点的父节点为待删除节点，则直接将前驱节点替换为待删除节点
					if(this.rightChild.leftChild.userId == preNode.userId) {
						preNode.rightChild = this.rightChild.rightChild;
						this.rightChild = preNode;
						return;
					}else {
						if(preNode.leftChild != null) {
							//得到前驱节点的父节点或左子节点
							TreeNode leftPreNode = infixList.get(index-2);
							//得到前驱节点的父节点值
							TreeNode parentPreNode = infixList.get(index-3);
							//替换前驱节点
							parentPreNode.rightChild = leftPreNode;
						}else {
							//得到前驱节点的父节点或左子节点
							TreeNode leftPreNode = infixList.get(index-2);
							//当前驱节点不存在子节点时，删除前驱节点
							leftPreNode.rightChild = null;
						}
					}
					//将前驱节点替换要删除节点
					preNode.leftChild = this.rightChild.leftChild;
					preNode.rightChild = this.rightChild.rightChild;
					this.rightChild = preNode;
				}
			}
			//如果该节点只有右子节点
			if(this.rightChild.leftChild == null && this.rightChild.rightChild != null) {
				this.rightChild = this.rightChild.rightChild;
				return;
			}
		}
		
		if(this.leftChild != null) {
			this.leftChild.delete(id);
		}
		if(this.rightChild != null) {
			this.rightChild.delete(id);
		}
	}
	
}

