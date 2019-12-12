package org.about.zhaozt.algorithm;

import java.util.Scanner;

/**
 * @author zhaozt
 * @date 2019年12月7日 下午8:38:53
 * describe : 散列函数
 */
public class HashTable {

	public static void main(String[] args) {
		//
		HashTab hashTab = new HashTab(8);
		
		String str = "";
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("添加请用户输入:add,显示所有用户输入:list,查询用户输入:find,删除用户输入delete,退出系统请输入:exit");
			
			str = scanner.next();
			switch (str) {
			case "add":
				System.out.println("输入用户id：");
				int id = scanner.nextInt();
				System.out.println("输入用户名:");
				String name = scanner.next();
				User user = new User(id, name);
				hashTab.add(user);
				break;
			case "list":
				hashTab.list();
				break;
			case "find":
				System.out.println("输入用户id：");
				int num = scanner.nextInt();
				hashTab.find(num);
				break;
			case "delete":
				System.out.println("输入用户id：");
				int no = scanner.nextInt();
				hashTab.delete(no);
				break;
			case "exit":
				scanner.close();
				System.exit(0);
				break;
			default:
				break;
			}
		}
		
	}

}

//创建HashTable
class HashTab{
	private int size;
	private LinkedUser[] linkedUsers;
	
	public HashTab(int size) {
		this.size = size;
		linkedUsers = new LinkedUser[size];
		//初始化每个链表
		for (int i = 0; i < size; i++) {
			linkedUsers[i] = new LinkedUser();
		}
	}
	//增加方法
	public void add(User user) {
		//根据用户id,散列分布到数组中
		int arrIndex = hashFunction(user.userId);
		//添加
		linkedUsers[arrIndex].add(user);
	}
	//遍历
	public void list() {
		for (int i = 0; i < size; i++) {
			linkedUsers[i].list(i);
		}
	}
	
	//查询
	public void find(int id) {
		int arrIndex = hashFunction(id);
		User user = linkedUsers[arrIndex].find(id);
		if(user != null) {
			System.out.printf("用户为 {userId:%d, userName:%s}\t",user.userId,user.userName);
			System.out.println();
		}else {
			System.out.println("用户id不存在");
		}
	}
	
	//删除
	public void delete(int id) {
		int arrIndex = hashFunction(id);
		linkedUsers[arrIndex].delete(id);
	}
	
	//散列函数
	public int hashFunction(int id) {
		return id % size;
	}
}

class User{
	public int userId;
	public String userName;
	public User next;
	public User(int userId, String userName) {
		super();
		this.userId = userId;
		this.userName = userName;
	}
}

class LinkedUser{
	//创建链表
	private User head;
	//增加链表数据
	public void add(User user) {
		//增加第一个元素
		if(head == null) {
			head = user;
			return;
		}
		User userTemp = head;
		while(true) {
			if(userTemp.next == null) {
				break;
			}
			userTemp = userTemp.next;
		}
		userTemp.next = user;
	}
	//遍历链表
	public void list(int num) {
		if(head == null) {
			System.out.println("链表"+num+"为空");
			return;
		}
		User userTemp = head;
		System.out.printf("链表"+num+"为 ");
		while(true) {
			System.out.printf("{userId:%d,userName:%s}\t",userTemp.userId,userTemp.userName);
			if(userTemp.next == null) {
				break;
			}
			userTemp = userTemp.next;
		}
		System.out.println();
	}
	
	//根据id查询
	public User find(int id) {
		if(head == null) {
			System.out.println("链表为空");
			return null;
		}
		
		User userTemp = head;
		while(true) {
			if(userTemp.userId == id) {
				break;
			}
			if(userTemp.next == null) {
				userTemp = null;
				break;
			}
			userTemp = userTemp.next;
		}
		return userTemp;
	}
	
	//删除用户信息
	public  void delete(int id) {
		if(head == null) {
			System.out.println("链表为空");
			return ;
		}
		
		User userTemp = head;
		User userPre = head;
		while(true) {
			if(head.userId == id) {
				head = head.next;
				System.out.println("删除"+id+"成功");
				break;
			}
			if(userTemp.userId == id) {
				System.out.println("删除"+id+"成功");
				userPre.next = userTemp.next;
				break;
			}
			if(userTemp.next == null) {
				System.out.println("该用户不存在");
				break;
			}
			userPre = userTemp;
			userTemp = userTemp.next;
		}
	}
}