package org.about.zhaozt.algorithm;
/**
 * @author zhaozt
 * @date 2019年12月2日 上午7:15:25
 * describe :
 */
public class Search {

	public static void main(String[] args) {
		int temp[] = {1,3,3,7,13,16,21,35};
		
		int index1 = binarySearch(temp, 0, 7, 16);
		System.out.println(index1);
		int index2 = insertSearch(temp, 0, 7, 16);
		System.out.println(index2);
	}

	//二分查找，数组有序
	public static int binarySearch(int[] arr, int left, int right, int value) {
		
		if(left > right) {
			return -1;
		}
		int mid = (left + right) / 2;
		int midValue = arr[mid];
		
		if(midValue > value) {
			return binarySearch(arr, left, mid-1, value);
		}else if(midValue < value) {
			return binarySearch(arr, mid+1, right, value);
		}else {
			return mid;
		}
	}
	
	//插值查找，数组有序
	public static int insertSearch(int[] arr, int left, int right, int value) {
		
		if(left > right || value < arr[0] || value > arr[arr.length-1]) {
			return -1;
		}
		//
		int mid = left + (right - left)*(value - arr[left])/(arr[right]-arr[left]);
		int midValue = arr[mid];
		
		if(midValue > value) {
			return insertSearch(arr, left, mid-1, value);
		}else if(midValue < value) {
			return insertSearch(arr, mid+1, right, value);
		}else {
			return mid;
		}
	}

	//斐波那契查找
//	public static int fibonacciSearch(int[] arr) {
//		
//		
//		
//	}
}

