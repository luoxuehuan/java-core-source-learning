package com.java.sort;


/**
 * 排序
 * @author lxh
 * 
 *
 */
public class Sort {

	public static void main(String[] args) {
		int[] data = new int[]{3,6,7,9,2,1,5,8,4};
		// TODO Auto-generated method stub
		SelectSort(data);
		bubblesort(data);
	}
	
	
	
	
	/**
	 * 1.冒泡排序
	 * @param arr
	 * 冒泡排序的时间复杂度为O(n^2)。
	 */
	public static void bubblesort(int[] arr){
		if(arr==null||arr.length==0){
			return ;
		}else{
			int len  =arr.length;
			for(int i=0;i<len-1;i++){
				/**
				 * 冒泡排序是相邻元素比较把小的"交换"到最前面.
				 */
				for(int j=len-1;j>i;j--){
					if(arr[j]<arr[j-1]){
						swap(arr,j,j-1);
					}
				}
			}
			System.out.println("冒泡排序：");
			result(arr);
			System.out.println("");
		}
	}
	
	
	/**
	 * 2.选择排序
	 * 冒泡排序是通过相邻的比较和交换
	 * 而选择排序是通过对整体的选择。
	 * 选择排序的时间复杂度为O(n^2)
	 * @param arr
	 * @return
	 */
	public static void SelectSort(int[] arr){
		if(arr==null||arr.length==0){
			return ;
		}else{
			int len  =arr.length;
			int minIndex;
			for(int i=0;i<len-1;i++){
				
				 minIndex = i;
				/**
				 * 一定要分清第二个for循环的条件,j每次都是i+1开始,而不是从1开始
				 */
				for(int j=i+1;j<len;j++){
					/**
					 * 只是选择排序只有在确定了最小数的前提下才进行交换，大大减少了交换的次数.
					 */
					if(arr[j]<arr[i]){
						//但是下面的方式还是有缺陷,进行了多次的交换。更好的方式引入 minIndex = j;
						/*swap(arr,i,j);*/
						minIndex = j;
					}
				}
				
				if(minIndex != i) { //如果minIndex不为i，说明找到了更小的值，交换之。
	                swap(arr, i, minIndex);
	            }
			}
			
			System.out.println("选择排序：");
			result(arr);
			System.out.println("");
		}
	}
	
	/**
	 * 插入排序
	 * @param arr
	 */
	public static void InsertSort(int[] arr){
		int arrlen = arr.length;
		int[] newarr = new int[arrlen];
		for(int i = 0;i<arrlen;i++){
			if(i == 0){
				newarr[i] = arr[i];
			}else{
				
			}
		}
	}
	
	
	/**
	 * 数组里两个下标的数据交换
	 * @param arr 数组
	 * @param i	下标i
	 * @param j 下标j
	 */
	public static void swap(int[] arr,int i,int j){
		int temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;
	}
	
	/**
	 * 打印结果
	 * @param data
	 */
	public static void result(int[] data){
		for(int i=0,len=data.length;i<len;i++){
			System.out.print(data[i]+" ");
		}
	}

}
