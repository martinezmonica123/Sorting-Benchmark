package project2;

/**
 * Driver class for the Generic Array Tools Class.
 * Tests the various sorting algorithm implementations found in GenericArrayTools 
 * and outputs the results. 
 * 
 * @author Monica Martinez
 * @version November 1, 2014
 *
 */
public class TestDriver {

	public static void main(String[] args) {
		
		//System.out.printf(" %s %23s %22s %22s" + "\n","Input Size","Blank", "MergeSort", "MergeSortInPlace");	
		
		System.out.printf(" %s %23s %22s %22s" + "\n","Input Size","SelectionSort", "MergeSort", "QuickSort");
		System.out.println("______________________________________________________________________________________");
	
		//Create arrays with various array-size test options
		Integer [] valList1 = new Integer[] {10000,20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000};
		Integer [] valList2 = new Integer[] {50000,100000,150000,200000,250000,300000,350000,400000,450000,500000,
											550000,600000,650000,700000,750000,800000,850000,900000,950000,1000000};
		
		//Test selection, merge, and quick sort using the first array of array-sizes
		for (int i = 0; i < valList1.length; i++){
			final int VAL = valList1[i];
			Integer[] test;
			test = GenericArrayTools.createArr(VAL);
			
			Integer[] copy1 = new Integer[VAL];
			Integer[] copy2 = new Integer[VAL]; 
			Integer[] copy3 = new Integer[VAL];
			
			System.arraycopy(test, 0, copy1, 0, VAL);
			System.arraycopy(test, 0, copy2, 0, VAL);
			System.arraycopy(test, 0, copy3, 0, VAL);
			

			long startT3 = System.nanoTime();
			GenericArrayTools.quickSort(copy1);
			long endT3 = System.nanoTime();
			long dur3 = (endT3 - startT3)/1000000;
		
			long startT2 = System.nanoTime();
			GenericArrayTools.mergeSort(copy2);
			long endT2 = System.nanoTime();
			long dur2 = (endT2 - startT2)/1000000;
			
			long startT1 = System.nanoTime();
			GenericArrayTools.selectionSort(copy3);
			long endT1 = System.nanoTime();
			long dur1 = (endT1 - startT1)/1000000;
			 
			
			//hardcoded values for formatted printing
			if (VAL != 100000)
				System.out.printf("  " + VAL +" %15d milliseconds %10d milliseconds %10d milliseconds \n", dur1,dur2,dur3);
			else 
				System.out.printf(" " + VAL +" %15d milliseconds %10d milliseconds %10d milliseconds \n", dur1,dur2,dur3);
		}
		
		System.out.println("______________________________________________________________________________________");
		
		//Test merge and quick sort using the second array of array-sizes
		for (int i = 0; i < valList2.length; i++){
			final int VAL = valList2[i];
			Integer[] test;
			test = GenericArrayTools.createArr(VAL);
			
			Integer[] copy1 = new Integer[VAL];
			Integer[] copy2 = new Integer[VAL]; 
			Integer[] copy3 = new Integer[VAL];
			
			System.arraycopy(test, 0, copy1, 0, VAL);
			System.arraycopy(test, 0, copy2, 0, VAL);
			System.arraycopy(test, 0, copy3, 0, VAL);
			

			long startT3 = System.nanoTime();
			GenericArrayTools.quickSort(copy1);
			long endT3 = System.nanoTime();
			long dur3 = (endT3 - startT3)/1000000;
		
			long startT2 = System.nanoTime();
			GenericArrayTools.mergeSort(copy2);
			long endT2 = System.nanoTime();
			long dur2 = (endT2 - startT2)/1000000;

			
			//hardcoded values for formatted printing
			if (VAL == 1000000)
				System.out.printf("" + VAL +" %39d milliseconds %10d milliseconds \n", dur2, dur3);
			else if (VAL == 50000)
				System.out.printf("  " + VAL +" %39d milliseconds %10d milliseconds \n", dur2, dur3);
			else 
				System.out.printf(" " + VAL +" %39d milliseconds %10d milliseconds \n", dur2, dur3);
		}
	}
}
