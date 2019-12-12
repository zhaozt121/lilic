package org.about.zhaozt.algorithm;
/**
 * @author zhaozt
 * @date 2019年11月6日 下午4:55:11
 * desc 八皇后问题
 */
public class Queen {

	public static void main(String[] args) {
		
		Queen queen = new Queen();
		queen.layQueen(0);
		queen.showChesseQueen();
		System.out.println("-------------------------------");
		//改进方法
		queen.lay(0);
		System.out.printf("共有%d种摆法。",count);
	}
	
	final static int MAX_COUNT = 8;
	int[][] chesseBoard = new int[MAX_COUNT][MAX_COUNT];
	
	//检查摆放位置是否合规
	boolean check(int x, int y) {
		
		//循环 y行
		for (int i = 0; i < y; i++) {
			//如果上方有皇后
			if(chesseBoard[x][i]==1) {
				return false;
			}
			//如果左上方有皇后
			if(x-1-i>=0 && chesseBoard[x-1-i][y-1-i]==1) {
				return false;
			}
			//如果右上方有皇后
			if(x+1+i<MAX_COUNT && chesseBoard[x+1+i][y-1-i]==1) {
				return false;
			}
		}
		return true;
	}
	
	//回溯递归
	boolean layQueen(int y) {
		//遍历行数超过MAX_COUNT时 结束
		if(y == MAX_COUNT) {
			return true;
		}
		//遍历行
		for (int i = 0; i < MAX_COUNT; i++) {
			//清空当前行摆放的皇后
			for (int x = 0; x < MAX_COUNT; x++) {
				chesseBoard[x][y] = 0;
			}
			//检查皇后摆放是否合规，归并
			if(check(i, y)) {
				chesseBoard[i][y] = 1;
				//递归调用
				if(layQueen(y+1)) {
					return true;
				}
			}
		}
		return false;
	}
	
	//打印棋盘皇后的位置
	void showChesseQueen() {
		for (int i = 0; i < MAX_COUNT; i++) {
			for (int j = 0; j < MAX_COUNT; j++) {
				System.out.print(chesseBoard[j][i] + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 *   方法改进 使用一维数组代替，数组下标i表示行,array[i]的值表示列
	 */
	int[] array = new int[MAX_COUNT];
	static int count = 0;
	//打印皇后
	public void print() {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		count++;
		System.out.println();
	}
	//检查放置第n个皇后是否合规
	public boolean judge(int n) {
		for (int i = 0; i < n; i++) {
			//如果在同一列,或在同一斜线上
			if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n]-array[i])) {
				return false;
			}
		}
		return true;
	}
	//放置皇后
	public void lay(int n) {
		if(n == MAX_COUNT) {
			print();
			return;
		}
		for (int i = 0; i < MAX_COUNT; i++) {
			array[n] = i;
			if(judge(n)) {
				lay(n+1);
			}
		}
	}
}

