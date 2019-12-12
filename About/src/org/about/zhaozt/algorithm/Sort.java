package org.about.zhaozt.algorithm;

import java.util.Arrays;

/**
 * @author zhaozt
 * @date 2019年11月10日 上午10:15:55
 * describe :排序
 */
public class Sort {
	
	public static void main(String[] args) {
		
		int[] temp = {9,8,1,7,2,3,5,4,6,0};
		System.out.println("测试数组："+Arrays.toString(temp));
		
		int[] arr = new int[9000000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random()*90000);
		}
		/*
		int[] arr1 = arr.clone();
		Long before1 = System.currentTimeMillis();
	 	bubbleSort(arr1);
	 	Long after1 = System.currentTimeMillis();
	 	System.out.println("冒泡排序用时：" + (after1-before1) + "ms"); //4383ms
//	 	System.out.println(Arrays.toString(bubble));
	 	
	 	int[] arr2 = arr.clone();
	 	Long before2 = System.currentTimeMillis();
	 	selectSort(arr2);
	 	Long after2 = System.currentTimeMillis();
	 	System.out.println("选择排序用时：" + (after2-before2) + "ms"); //467ms
//	 	System.out.println(Arrays.toString(select));
	 	
	 	int[] arr3 = arr.clone();
	 	Long before3 = System.currentTimeMillis();
	 	insertSort(arr3);
	 	Long after3 = System.currentTimeMillis();
	 	System.out.println("插入排序用时：" + (after3-before3) + "ms"); //905ms
//	 	System.out.println(Arrays.toString(insert)); 
	 	
		int[] arr4 = arr.clone();
	 	Long before4 = System.currentTimeMillis();
	 	shellSort(arr4);
	 	Long after4 = System.currentTimeMillis();
	 	System.out.println("希尔排序用时：" + (after4-before4) + "ms"); //1842ms
//	 	System.out.println(Arrays.toString(shell));
*/
	 	int[] arr5 = arr.clone();
	 	Long before5 = System.currentTimeMillis();
	 	shellSort2(arr5);
	 	Long after5 = System.currentTimeMillis();
	 	System.out.println("希尔排序用时：" + (after5-before5) + "ms"); //1842ms
//	 	System.out.println(Arrays.toString(arr5));
	 	
	 	int[] arr6 = arr.clone();
	 	Long before6 = System.currentTimeMillis();
	 	quickSort(arr6, 0, arr6.length-1);
	 	Long after6 = System.currentTimeMillis();
	 	System.out.println("快速排序用时：" + (after6-before6) + "ms"); //1842ms
//	 	System.out.println(Arrays.toString(temp));

		int[] arr7 = arr.clone();
	 	Long before7 = System.currentTimeMillis();
	 	quickSort(arr7, 0, arr7.length-1);
	 	Long after7 = System.currentTimeMillis();
	 	System.out.println("归并排序用时：" + (after7-before7) + "ms"); //1842ms
//	 	System.out.println(Arrays.toString(temp));
 
 
		int[] arr8 = arr.clone();
	 	Long before8 = System.currentTimeMillis();
	 	radixSort(arr8);
	 	Long after8 = System.currentTimeMillis();
	 	System.out.println("基数排序用时：" + (after8-before8) + "ms"); //1842ms
//	 	for (int i = 0; i < 20; i++) {
//	 		System.out.println(arr8[i]);
//		}
	 	
		
	}
	
	//冒泡排序
	public static void bubbleSort(int arr[]) {
		int len = arr.length;
		for (int i = 0; i < len-1; i++) {
			
			int temp;
			for (int j = 0; j < len-i-1; j++) {
				if (arr[j] > arr[j+1]) {
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}
	
	//选择排序
	public static void selectSort(int arr[]) {
		int len = arr.length;
		int minIndex = 0;
		int minValue = 0;
		for (int i = 0; i < len-1; i++) {
			minIndex = i;
			minValue = arr[i];
			for (int j = i+1; j < len; j++) {
				if(minValue > arr[j]) {
					minValue = arr[j];
					minIndex = j;
				}	
			}
			if(minIndex != i) {
				arr[minIndex] = arr[i];
				arr[i] = minValue;
			}
		}
	}

	//插入排序
	public static void insertSort(int arr[]) {
		
		int len = arr.length;
		if(len<=1) {
			return ;
		}
		
		int insertValue = 0;
		int insertIndex = 0;
		
		for (int i = 1; i < len; i++) {
			//初始化值
			insertValue = arr[i];
			insertIndex = i-1;
			while(insertIndex >= 0 && insertValue < arr[insertIndex]) {
				arr[insertIndex+1] = arr[insertIndex];
				insertIndex--;
			}
			if(insertIndex+1 != i) {
				arr[insertIndex+1] = insertValue;
			}
		}
	}
	
	//希尔排序(组内比较后交换位置，这个太慢了)
	public static void shellSort(int[] arr) {
		int temp = 0;
		for (int step = arr.length/2; step >0; step /=2) {
			for (int i = step; i < arr.length; i++) {
				for (int j = i-step; j >= 0; j -= step) {
					if(arr[j] > arr[j+step]) {
						temp = arr[j];
						arr[j] = arr[j+step];
						arr[j+step] = temp;
					}
				}
			}
		}
	}
	
	public static void shellSort2(int[] arr) {
		//依次折半减少每次步长
		for (int step = arr.length/2; step >0; step /=2) {
			//遍历数组
			for (int i = step; i < arr.length; i++) {
				int j = i;
				int temp = arr[j];
				if(arr[j] < arr[j-step]) {  
					while(j-step >= 0 && temp < arr[j-step]) {
						arr[j] = arr[j-step];
						j -= step;
					}
					arr[j] = temp;
				}
			}
		}
	}
	
	//快速排序-选取中值，左边数和右变数分别遍历，比中值大的放在右边，比中值小的放在左边，
	//分别递归左右两边，直到排序结束
	public static void quickSort(int[] arr,int left, int right) {
		int l = left; //初始化左下标
		int r = right; //初始化右下标
		int temp;
		int pivot = arr[(left+right)/2]; //中值
		//左右下标值比对
		while(l < r) {
			//中值左边遍历(从左到右遇到比pivot大的数就跳出循环)
			while(arr[l] < pivot) {
				l +=1;
			}
			//中值右边遍历(从右到左遇到比pivot小的数就跳出循环)
			while(arr[r] > pivot) {
				r -= 1;
			}
			//左边值都小于中值，右边都大于中值
			if(l >= r) {
				break;
			}
			//交换
			temp = arr[l];
			arr[l] = arr[r];
			arr[r] = temp;
			//交换结束,
			if(arr[l] == pivot) {
				r -= 1;
			}
			if(arr[r] == pivot) {
				l += 1;
			}
		}
		if(l == r) {
			l += 1;
			r -= 1;
		}
		//向左递归
		if(left < r) {
			quickSort(arr,left,r);
		}
		//右递归
		if(right > l) {
			quickSort(arr,l,right);
		}
	}
	
	//归并排序（分治）
	public static void mergeSort(int[] arr, int left, int right, int temp[]) {
		//数组的拆分
		if(left < right) {
			int mid = (left + right)/2;
			//数组拆分成左右两数组
			mergeSort(arr, left, mid, temp);
			mergeSort(arr, mid+1, right, temp);
			//归并
			int l = left; //左边的数组
			int r = mid+1;  //右边的数组
			int t = 0;    //临时数组下标
			//当左边或右边的有一个全部拷贝到temp中为止
			while(l<=mid && r<=right) {
				//比较左右数组值
				if(arr[l] <= arr[r]) {
					temp[t] = arr[l];
					l += 1;
					t += 1;
				}else {
					temp[t] = arr[r];
					r += 1;
					t += 1;
				}
			}
			//当左数组还有剩余时
			while(l <= mid) {
				temp[t] = arr[l];
				l += 1;
				t += 1;
			}
			//当右数组还有剩余时
			while(r <= right) {
				temp[t] = arr[r];
				r += 1;
				t += 1;
			}
			//将temp数组拷贝回原数组
			t = 0;
			int leftIndex = left;
			while(leftIndex <= right) {
				arr[leftIndex] = temp[t];
				t += 1;
				leftIndex += 1;
			}
		}
	}
	
	//基数排序
	public static void radixSort(int[] arr) {
		
		//得到数组中最大的数的长度
		int max = arr[0];
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] > max) {
				max = arr[i];
			}
		}
		int maxLen = (max+"").length();
		//定义存放排序的桶
		int[][] bucket = new int[10][arr.length];
		//定义每个桶放入元素的个数
		int[] bucketNum = new int[10];
		//按最大数的个位开始到最大位结束
		for (int i = 0, n = 1; i < maxLen; i++,n *= 10) {
			for (int j = 0; j < arr.length; j++) {
				//得到i位上的数，并存入对应的桶中
				int num = arr[j]/n%10;
				bucket[num][bucketNum[num]] = arr[j];
				bucketNum[num]++;
			}
			//将i位桶中的元素放回到数组中
			int index = 0;
			for (int j = 0; j < bucketNum.length; j++) {
				if(bucketNum[j] != 0) {
					for (int k = 0; k < bucketNum[j]; k++) {
						arr[index] = bucket[j][k];
						index++;
					}
				}
				bucketNum[j] = 0;
			}
		}
	}
}
