package org.about.zhaozt.algorithm;
/**
 * @author zhaozt
 * @date 2019年11月7日 下午10:52:52
 * describe: 二维数组<=>稀疏数组
 */
public class SparseArray {
	
	public static void main(String[] args) {
		
		//创建二维数组
		System.out.println("---------------创建二维数组----------------");
		int chessArray[][] = new int[12][12];
		chessArray[2][3] = 1;
		chessArray[4][5] = 2;
		chessArray[7][5] = 34;
		chessArray[6][11] = 12;
		//输出二维数组
		for (int[] row:chessArray) {
			for (int data:row) {
				System.out.printf("%d\t", data);
			}
			System.out.println();
		}
		
		System.out.println("-------------转变成稀疏数组------------------");
		int[][] sparse = arrayToSparse(chessArray);
		printArray(sparse);
		
		System.out.println("---------------还原源数组----------------");
		int[][] arr = sparseToArray(sparse);
		printArray(arr);
	}
	
	static int[][] sparseToArray(int[][] sparse){
		
		//创建二维数组
		int row = sparse[0][0];
		int col = sparse[0][1];
		int arr[][] = new int[row][col];
		for (int i = 1; i < sparse.length; i++) {
			arr[sparse[i][0]][sparse[i][1]] = sparse[i][2];
		}
		return arr;
	}
	
	static int[][] arrayToSparse(int[][] arr){
		int sum = 0;
		//遍历二维数组
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if(arr[i][j] != 0) {
					sum++;
				}
			}
		}
		//创建稀疏数组
		int sparse[][] = new int[sum+1][3];
		//稀疏数组赋值
		sparse[0][0] = arr.length;
		sparse[0][1] = arr[0].length;
		sparse[0][2] = sum;
		int count = 0;  //非零数计数
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if(arr[i][j] != 0) {
					count++;
					sparse[count][0] = i;
					sparse[count][1] = j;
					sparse[count][2] = arr[i][j];
				}
			}
		}
		
		return sparse;
	}
	
	//打印数组
	static void printArray(int[][] arr) {
		for (int[] row:arr) {
			for (int data:row) {
				System.out.printf("%d\t", data);
			}
			System.out.println();
		}
	}
}

