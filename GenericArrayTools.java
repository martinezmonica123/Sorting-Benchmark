package project2;

import java.lang.reflect.Array;
import java.util.Random;

/**
 * Class contains various generic sorting implementations of selection, merge, and quicksort.
 * Also contains methods for instantiating and printing an array.
 * 
 * @author Monica Martinez
 * @version November 1, 2014
 *
 */
public class GenericArrayTools {

	/**
	 * Instantiates a generic array of a specified value and fills it with random values.
	 * @param val, value that specifies the array size
	 * @return list, returns an instantiate, generic array
	 */
	@SuppressWarnings("unchecked")
	public static <E> E[] createArr(int val){
		Integer [] list = new Integer[val];
		Random rand = new Random();
		for (int i=0; i < val; i++){
			Integer n = rand.nextInt(Integer.MAX_VALUE);
			list[i] = n;
		}
		return (E[]) list;
	}
		
	/**
	 * Prints the contents of an array, mainly for testing purposes.
	 * @param list, list to be printed
	 */
	public static <E> void printArray ( E [] list ) {
		if ( null != list ) {
			for (int i = 0; i < list.length; i++ ) 
				System.out.print(list[i] + ", ");
			System.out.print("\n");
		}		
	}

	
//-----------------------------------SELECTION SORT--------------------------------
	
	/**
	 * Sorts the given array using the selection sort algorithm. 
	 * @param list, array to sort.
	 */
	public static <E extends Comparable<E>> void selectionSort(E [] list) {
		for (int i = 0; i < list.length - 1; i++) {
			// Find the minimum in the list[i..list.length-1]
			E currentMin = list[i];
			int currentMinIndex = i;

			for (int j = i + 1; j < list.length; j++) {
				if (currentMin.compareTo(list[j]) > 0) {
					currentMin = list[j];
					currentMinIndex = j;
				}
			}
			// Swap list[i] with list[currentMinIndex] if necessary;
			if (currentMinIndex != i) {
				list[currentMinIndex] = list[i];
				list[i] = currentMin;
			}
		}
	}

	
//------------------------------------MERGE SORT------------------------------------
	
	/**
	 * Wrapper method for merge sort algorithm.
	 * @param list, list to be sorted.
	 */
	public static <E extends Comparable <E>> void mergeSort(E [] list){
		@SuppressWarnings("unchecked")
		E temp[] = (E[]) Array.newInstance(list.getClass().getComponentType(), list.length);
		
		mergeSort(list, temp, 0, list.length-1);
	}
	
	/**
	 * Implementation of the merge sort algorithm as detailed in the openDSA web-site. 
	 * (makes a temporary array for merging)
	 * @param list, list to be sorted.
	 * @param temp, temporary list to store sub-array.
	 * @param left, leftmost index of the list
	 * @param right, rightmost index of the list
	 */
	private static <E extends Comparable <E>> void mergeSort(E [] list, E [] temp, int left, int right){
		if (left < right) {
			int mid = (left+right)/2;          // Select midpoint
		
			mergeSort(list, temp, left, mid);     // Mergesort first half
			mergeSort(list, temp, mid+1, right);  // Mergesort second half

			for (int i=left; i<=right; i++)    // Copy subarray to temp
				temp[i] = list[i];
		
			// Do the merge operation back to A
			int i1 = left;
			int i2 = mid + 1;
		
			for (int curr = left; curr <= right; curr++) {
				if (i1 == mid+1)                 // Left sublist exhausted
					list[curr] = temp[i2++];
				else if (i2 > right)             // Right sublist exhausted
					list[curr] = temp[i1++];
				else if (temp[i1].compareTo(temp[i2]) <= 0)  // Get smaller value
					list[curr] = (E) temp[i1++];
				else
					list[curr] = temp[i2++];				
			}
		}
	}

/*	/**
	 * Wrapper method for the merge sort (in-place) implementation
	 * @param list, list to be sorted
	 */
/*	public static <E extends Comparable <E>> void mergeSortInPlace(E [] list){
		mergeSortInPlace(list, 0, list.length-1);
	}
*/
	
/*	
	/**
	 * Implementation of the merge sort algorithm without using a temporary array.
	 * In-place implementation of merge sort as detailed in http://penguin.ewu.edu/
	 * @param list, list to be sorted.
	 * @param left, leftmost index of the list
	 * @param right, rightmost index of the list
	 */
/*	private static <E extends Comparable <E>> void mergeSortInPlace(E [] list, int first, int last){
		
		if (first < last) {
			int mid = (last+first)/2;          // Select midpoint
		
			mergeSortInPlace(list, first, mid);     // Mergesort first half
			mergeSortInPlace(list, mid+1, last);  // Mergesort second half
		    
			int left, right;
			left = first;  
			right = mid+1;
			
		      if ( list[mid].compareTo(list[right]) <= 0 )
		         return;

		      while (left <= mid && right <= last) {  
		         if ( list[left].compareTo(list[right]) <= 0 )
		            left++;

		         else { 
		        	E tmp = list[right];     // Will move to [left]
		            System.arraycopy(list, left, list, left+1, right-left);
		            list[left] = tmp;
		            left++;  mid++;  right++;
		         }
		      }
		}
	}

*/
	
	
//------------------------------------QUICK SORT------------------------------------
	
	/**
	 * Wrapper method for the quick sort implementation.
	 * @param list, list to be sorted
	 */
	public static <E extends Comparable <E>> void quickSort(E [] list){
		quickSort(list, 0, list.length-1);
	}
	
	/**
	 * Implementation of the quick sort algorithm as detailed in the openDSA web-site.
	 * @param list, list to be sorted
	 * @param left, leftmost index of the list
	 * @param right, rightmost index of the list
	 */
	private static <E extends Comparable <E>> void quickSort(E[] list, int left, int right) { // Quicksort
		 
		//int pivotindex = medianOfThree(list, left, right);  // Pick a pivot
		
		int pivotindex = findPivot(list, left, right);  // Pick a pivot
		swap(list, pivotindex, right);               // Stick pivot at end
		
		// k will be the first position in the right subarray
		int k = partition(list, left, right-1, list[right]);
		
		swap(list, k, right);                        // Put pivot in place
		
		if ((k-left) > 1) 
			quickSort(list, left, k-1);  // Sort left partition	
		if ((right-k) > 1) 
			quickSort(list, k+1, right);  // Sort right partition
		
		}
	
	/**
	 * Method that determines the pivot position of an array to be used in the 
	 * implementation of the quick sort algorithm as detailed in the openDSA web-site.
	 * @param list, list to be sorted
	 * @param left, leftmost index of the list
	 * @param right, rightmost index of the list
	 * @return int, pivot position
	 */
	private static <E extends Comparable <E>> int findPivot(E[] list, int left, int right){
		return ( left + right ) / 2; 
		//return right;
	   // return left;
	}
	
/*
	/**
	 * Method determines the pivot by using the median value of the first, last, and middle elements.
	 * @param list, list to be sorted
	 * @param left, leftmost index in the array
	 * @param right, rightmost index of the array
	 * @return median value as the pivot
	 */
/*	
	public static <E extends Comparable <E>> int medianOfThree(E [] list, int left, int right){
	      int center = (left+right)/2;
	         
	      if((list[left]).compareTo(list[center]) > 0)  	
	    	  swap(list,left,center);
	      if((list[left]).compareTo(list[right]) > 0)
	    	  swap(list,left, right);
	      if((list[center]).compareTo(list[right]) > 0)
	    	  swap(list,center, right);
	      
	      swap(list,center, right);
	      return (Integer) list[right];
	          
	}
	
*/
	/**
	 * Method that partitions an array as part of the quick sort algorithm 
	 * implementation detailed in the openDSA web-site.
	 * @param list, list to be sorted
	 * @param left, leftmost index of the array
	 * @param right, rightmost index of the array
	 * @param pivot, index of the pivot position of the array
	 * @return int, first position in the right partition.
	 */
	private static <E extends Comparable <E>> int partition(E[] list, int left, int right, E pivot) {
		while (left <= right) { // Move bounds inward until they meet
			while (list[left].compareTo(pivot) < 0) 
				left++;
			while ((right >= left) && (list[right].compareTo(pivot) >= 0)) 
				right--;
			if (right > left) 
				swap(list, left, right); // Swap out-of-place values
		}
		return left;            // Return first position in right partition
	}
	
	/**
	 * Method that swaps indices as part of the implementation of the quick sort 
	 * algorithm as detailed in the openDSA web-site.
	 * @param list, list with indices to be swapped  
	 * @param i,  index to be swapped
	 * @param j, index to be swapped into
	 */
	private static <E> void swap(E [] list, int i, int j){
		E temp = list[i];
		list[i] = list[j];
		list[j] = temp;
	}
}